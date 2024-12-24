package com.jingdianjichi.wx.handler.impl;

import cn.hutool.core.date.DateTime;
import com.jingdianjichi.wx.handler.WxChatMsgHandler;
import com.jingdianjichi.wx.handler.WxChatMsgTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jay
 * @since 2024/12/24 上午6:55
 */
@Slf4j
@Component
public class SubscribeMsgHandler implements WxChatMsgHandler {

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.SUBSCRIBE;
    }

    /**
     * 处理消息
     *
     * @return 处理结果
     */
    @Override
    public String handleMsg(Map<String, Object> msgMap) {
        String fromUserName = (String) msgMap.get("FromUserName");
        String toUserName = (String) msgMap.get("ToUserName");
        String content = "欢迎关注，我是经典鸡翅，一起加油吧！";
        return "<xml><ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "<CreateTime>"+ DateTime.now().getTime() +"</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[" + content + "]]></Content>\n" +
                "</xml>";
    }
}
