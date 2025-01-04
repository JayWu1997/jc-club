package com.jingdianjichi.subject.infra.config;

import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.subject.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 为插入和更新操作中 createTime createBy updateTime updateBy 字段插入值
 *
 * @author jay
 * @since 2025/1/3 下午3:22
 */
@Component
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class CreateAndUpdateInterceptor implements Interceptor {
    /**
     * 为插入和更新操作中 createTime createBy updateTime updateBy 字段插入值
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object param = invocation.getArgs()[1];
        // 参数为空则直接放行
        if (ObjectUtil.isNull(param)) {
            return invocation.proceed();
        }
        // 用户信息为空直接放行
        String loginId = UserContextHolder.getUserContext().getUserName();
        if (ObjectUtil.isNull(loginId)) {
            return invocation.proceed();
        }
        // 数据填充
        if (param instanceof Map) {
            for (Object value : ((Map) param).values()) {
                replaceInsertAndUpdateParamProperties(value, sqlCommandType);
            }
        } else {
            replaceInsertAndUpdateParamProperties(param, sqlCommandType);
        }
        return invocation.proceed();
    }


    private void replaceInsertAndUpdateParamProperties(Object param, SqlCommandType sqlCommandType) {
        String loginId = UserContextHolder.getUserContext().getUserName();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            dealInsertParamProperties(param);
        } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            dealUpdateParamProperties(param);
        }
    }

    private void dealUpdateParamProperties(Object param) {
        getAllFields(param).forEach(field -> {
            try {
                field.setAccessible(true);
                if ((field.getName().equals("updateBy") || (field.getName().equals("updatedBy")))
                        && field.getType() == String.class) {
                    field.set(param, UserContextHolder.getUserContext().getUserName());
                } else if ((field.getName().equals("updateTime") || field.getName().equals("updatedTime"))
                        && field.getType() == Date.class) {
                    field.set(param, new Date());
                }
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                if (log.isErrorEnabled()) {
                    log.error("SQL更新语句默认参数设置失败 error:{}", e.getMessage(), e);
                }
            }
        });
    }

    private void dealInsertParamProperties(Object param) {
        // 获取所有属性(包括所有父类)
        getAllFields(param).forEach(field -> {
            try {
                field.setAccessible(true);
                if ((field.getName().equals("createBy") || field.getName().equals("createdBy"))
                        && field.getType() == String.class) {
                    field.set(param, UserContextHolder.getUserContext().getUserName());
                } else if ((field.getName().equals("createTime") || field.getName().equals("createdTime"))
                        && field.getType() == Date.class) {
                    field.set(param, new Date());
                } else if ((field.getName().equals("isDeleted") || field.getName().equals("deleted"))
                        && field.getType() == Integer.class) {
                    field.set(param, 0);
                }
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                if (log.isErrorEnabled()) {
                    log.error("SQL插入语句默认参数设置失败 error:{}", e.getMessage(), e);
                }
            }
        });
    }

    private List<Field> getAllFields(Object obj) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null) {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }

        return fields;
    }
}
