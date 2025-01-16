package com.jingdianjichi.subject.appliction.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 用户点赞消息监听
 * @author jay
 * @since 2025/1/17 下午8:47
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "jc-club-topic", consumerGroup = "jc-club-consume-group")
public class TestListener implements RocketMQListener<String> {

    /**
     * @param msg
     */
    @Override
    public void onMessage(String msg) {
        log.info("msg:{}", msg);
    }
}
