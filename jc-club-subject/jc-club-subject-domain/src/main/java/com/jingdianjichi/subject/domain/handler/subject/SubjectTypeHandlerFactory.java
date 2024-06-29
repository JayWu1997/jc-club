package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目处理器工厂
 * @author jay
 * @since 2024/6/20 下午12:30
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> subjectTypeHandlerMap;

    /**
     * 根据题目类型获取题目处理器
     * @param code 题目类型code
     * @return SubjectTypeHandler
     */
    public SubjectTypeHandler getSubjectTypeHandler(int code) {
        return subjectTypeHandlerMap.get(SubjectInfoTypeEnum.getByCode(code));
    }

    /**
     * 在 bean 生成后填充 subjectTypeHandlerMap
     */
    @Override
    public void afterPropertiesSet(){
        subjectTypeHandlerMap = new HashMap<>();
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            subjectTypeHandlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
