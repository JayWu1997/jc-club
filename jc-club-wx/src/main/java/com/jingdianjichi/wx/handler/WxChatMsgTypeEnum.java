package com.jingdianjichi.wx.handler;

/**
 * @author jay
 * @since 2024/12/24 上午6:50
 */
public enum WxChatMsgTypeEnum {

    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT_MSG("text", "文本消息");

    private final String msgType;

    private final String desc;

    WxChatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    public static WxChatMsgTypeEnum getByMsgType(String msgType) {
        for (WxChatMsgTypeEnum typeEnum : WxChatMsgTypeEnum.values()) {
            if (typeEnum.getMsgType().equals(msgType)) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getDesc() {
        return desc;
    }
}
