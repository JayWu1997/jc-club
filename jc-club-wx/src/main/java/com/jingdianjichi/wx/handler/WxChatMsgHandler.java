package com.jingdianjichi.wx.handler;

import java.util.Map;

/**
 * @author jay
 * @since 2024/12/24 上午6:54
 */
public interface WxChatMsgHandler {

    /**
     * 获取消息类型
     * @return 消息类型
     */
    WxChatMsgTypeEnum getMsgType();

    /**
     * 处理消息
     *
     * @return 处理结果
     */
    String handleMsg(Map<String, Object> msgMap);
}
