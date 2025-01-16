package com.jingdianjichi.subject.appliction.mq;

import com.google.gson.Gson;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import com.jingdianjichi.subject.domain.service.SubjectLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户点赞消息监听
 * @author jay
 * @since 2025/1/17 下午8:47
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "subject-like-topic", consumerGroup = "jc-club-group")
public class SubjectLikeListener implements RocketMQListener<String> {

    @Resource
    private SubjectLikedDomainService likeDomainService;

    private static final Gson GSON = new Gson();

    /**
     * @param msg
     */
    @Override
    public void onMessage(String msg) {
        SubjectLikedBO likedBO = GSON.fromJson(msg, SubjectLikedBO.class);
        likedBO.setIsDeleted(0);
        likeDomainService.add(likedBO);
    }
}
