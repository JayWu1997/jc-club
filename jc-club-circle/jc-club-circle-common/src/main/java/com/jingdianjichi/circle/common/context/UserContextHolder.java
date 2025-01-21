package com.jingdianjichi.circle.common.context;

/**
 * 用户上下文 holder
 * @author jay
 * @since 2024/12/26 下午3:05
 */
public class UserContextHolder {

    private static final InheritableThreadLocal<UserContext> userContextThreadLocal = new InheritableThreadLocal<>();

    public static UserContext getUserContext() {
        return userContextThreadLocal.get();
    }

    public static void setUserContext(UserContext userContext) {
        userContextThreadLocal.set(userContext);
    }

    public static void removeUserContext() {
        userContextThreadLocal.remove();
    }
}
