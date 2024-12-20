package com.jingdianjichi.auth.common.exception;

import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
/**
 * 业务异常类，用于在业务逻辑中抛出异常，并携带结果码和相关数据。
 * 继承自RuntimeException，使得可以在不需要检查异常的地方抛出。
 */
public class BusinessException extends RuntimeException {

    private ResultCodeEnum resultCode; // 结果码枚举，用于标识异常的具体类型
    private Map<String, Object> data; // 可选的数据字段，用于传递额外信息，例如错误的输入值等

    /**
     * 构造函数，初始化异常结果码。
     * @param resultCode 异常结果码，标识异常的类型和处理方式。
     */
    public BusinessException(ResultCodeEnum resultCode) {
        this(resultCode, resultCode.getMessage());
    }

    /**
     * 构造函数，初始化异常结果码和详细信息。
     * @param resultCode 异常结果码，标识异常的类型和处理方式。
     * @param message 异常的详细信息，用于描述异常的具体情况。
     */
    public BusinessException(ResultCodeEnum resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.data = new HashMap<>();
    }

    /**
     * 构造函数，初始化异常结果码、详细信息和相关数据。
     * @param resultCode 异常结果码，标识异常的类型和处理方式。
     * @param message 异常的详细信息，用于描述异常的具体情况。
     * @param data 与异常相关联的数据，可选，用于传递额外的信息。
     */
    public BusinessException(ResultCodeEnum resultCode, String message, Map<String, Object> data) {
        super(message);
        this.resultCode = resultCode;
        this.data = data != null ? data : new HashMap<>();
    }

    /**
     * 重写toString方法，返回异常的字符串表示，包括结果码、消息和数据。
     * @return 异常的字符串表示。
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BusinessException [resultCode=").append(resultCode)
                .append(", message='").append(getMessage()).append('\'');
        if (!data.isEmpty()) {
            sb.append(", data=").append(data);
        }
        sb.append(']');
        return sb.toString();
    }
}


