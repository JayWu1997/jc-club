package com.jingdianjichi.wx.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.jingdianjichi.wx.exception.ResultCodeEnum;
import com.jingdianjichi.wx.handler.WxChatMsgHandler;
import com.jingdianjichi.wx.handler.WxChatMsgHandlerFactory;
import com.jingdianjichi.wx.util.ParamCheckUtil;
import com.jingdianjichi.wx.util.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信回调控制器
 *
 * @author jay
 * @since 2024/12/24 上午5:04
 */
@Slf4j
@RestController
public class CallbackController {

    /**
     * 微信回调token
     */
    public static final String TOKEN = "jingdianjichi";
    
    @Resource
    private WxChatMsgHandlerFactory handlerFactory;

    /**
     * 微信消息回调校验
     *
     * @return String
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("sig:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "fail";
    }

    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String postCallback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "echostr", required = false) String echostr,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("requestBody:{}\n,sig:{}\n,timestamp:{}\n,nonce:{}\n,echostr:{}\n,msg_signature:{}\n", requestBody, signature,
                timestamp, nonce, echostr, msgSignature);
        Map<String, Object> map = XmlUtil.xmlToMap(XmlUtil.getRootElement(XmlUtil.parseXml(requestBody)));
        log.info("map:{}", map);
        WxChatMsgHandler handler = getHandler(map);
        ParamCheckUtil.checkNotNull(handler, ResultCodeEnum.PARAM_ERROR, "未找到对应的微信消息类型处理器");
        return handler.handleMsg(map);
    }

    /**
     * 获取处理器
     * @param msgMap
     * @return
     */
    public WxChatMsgHandler getHandler(Map<String, Object> msgMap) {
        String msgTypeKey;
        String msgType = (String) msgMap.get("MsgType");
        String event = (String) msgMap.get("Event");
        if (StrUtil.isNotBlank(event)) {
            msgTypeKey = msgType + "." + event;
        } else {
            msgTypeKey = msgType;
        }
        return handlerFactory.getHandler(msgTypeKey);
    }
}