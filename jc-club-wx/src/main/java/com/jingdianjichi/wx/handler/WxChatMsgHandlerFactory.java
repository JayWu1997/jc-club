package com.jingdianjichi.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jay
 * @since 2024/12/24 上午6:58
 */
@Slf4j
@Component
public class WxChatMsgHandlerFactory implements InitializingBean {

    @Resource
    private List<WxChatMsgHandler> wxChatMsgHandlerList;

    private Map<WxChatMsgTypeEnum, WxChatMsgHandler> handlerMap;

    /**
     * 获取处理器
     * @param msgType
     * @return
     */
    public WxChatMsgHandler getHandler(String msgType) {
        return handlerMap.get(WxChatMsgTypeEnum.getByMsgType(msgType));
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        handlerMap = new HashMap<>();
        wxChatMsgHandlerList.forEach(handler -> handlerMap.put(handler.getMsgType(), handler));
    }
}
