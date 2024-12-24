package com.jingdianjichi.wx.handler.impl;

import com.jingdianjichi.wx.handler.WxChatMsgHandler;
import com.jingdianjichi.wx.handler.WxChatMsgTypeEnum;
import com.jingdianjichi.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jay
 * @since 2024/12/24 上午6:58
 */
@Slf4j
@Component
public class ReceiveTextMsgHandler implements WxChatMsgHandler {

    private static final String VERIFICATION_CODE = "验证码";

    private static final String VERIFICATION_CODE_REDIS_KEY_PREFIX = "VERIFICATION_CODE";

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
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
        String msgReceived = (String) msgMap.get("Content");
        if (!VERIFICATION_CODE.equals(msgReceived)) {
            return "";
        }

        int num = 0;

        do {
            num = (int) (Math.random() * 1000);
        } while (num < 100);
        String redisKey = redisUtil.buildKey(VERIFICATION_CODE_REDIS_KEY_PREFIX, String.valueOf(num));
        redisUtil.setNx(redisKey, fromUserName, 5L, TimeUnit.MINUTES);
        String replyContent = "验证码为：" + num;

        return "<xml>\n" +
                "<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "<CreateTime>"+ System.currentTimeMillis() / 1000 +"</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[" + replyContent + "]]></Content>\n" +
                "</xml>";
    }
}
