package com.jingdianjichi.subject.domain.job;

import com.jingdianjichi.subject.domain.service.SubjectLikedDomainService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 同步点赞数据
 */
@Slf4j
@Component
public class SubjectLikedXxlJob {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("syncLikedJobHandler")
    public void syncLikedJobHandler(){
        try {
            subjectLikedDomainService.syncLiked(1000);
            if (log.isInfoEnabled()) {
                log.info("同步点赞数据成功");
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("同步点赞数据失败", e);
            }
        }
    }

}
