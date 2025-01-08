package com.jingdianjichi.subject.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.subject.common.context.UserContextHolder;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.SubjectLikedStatusEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.convert.SubjectLikedBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import com.jingdianjichi.subject.domain.service.SubjectLikedDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLiked;
import com.jingdianjichi.subject.infra.basic.service.SubjectLikedService;
import com.jingdianjichi.subject.infra.basic.service.impl.SubjectInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目点赞表(SubjectLiked)领域服务实现类
 *
 * @author jay
 * @since 2025-01-06 15:48:28
 */
@Service
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    private static final String SUBJECT_LIKED_USER_KEY = "subject.liked.users.set";
    private static final String USER_SUBJECT_LIKED_KEY = "user.liked.subjects.set";
    private static final String USER_SUBJECT_LIKED_MAP_KEY = "user.subject.like.mapping";

    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SubjectInfoServiceImpl subjectInfoService;

    /**
     * 新增
     *
     * @param likedBO 新增的对象
     * @return 结果
     */
    public Boolean add(SubjectLikedBO likedBO) {
        // 两个 set 一个 hash。
        // 一个 set 用于存储指定问题的点赞用户列表，subjectId 拼接在 key 中 (eg. key: subject.liked.users:100)
        // 一个 set 用于存储指定用户点赞了哪些问题，userName 拼接在 key 中 (eg. key: user.subject.liked:o8QTn6ztKo2dn_3NLOxr08IiURKI)
        // hash 用于存储每次点赞动作的 subjectId 和 userName 映射，(eg. 大key：user.subject.mapping，小key：o8QTn6ztKo2dn_3NLOxr08IiURKI:100，value：1）
        String subjectLikedUserKey = SUBJECT_LIKED_USER_KEY + ":" + likedBO.getSubjectId().toString();
        String userLikedSubjectKey = USER_SUBJECT_LIKED_KEY + ":" + likedBO.getLikeUserId();
        String hashKey = likedBO.getLikeUserId() + ":" + likedBO.getSubjectId();
        if (SubjectLikedStatusEnum.LIKED.getCode().equals(likedBO.getStatus())) {
            redisTemplate.opsForSet().add(subjectLikedUserKey, likedBO.getLikeUserId());
            redisTemplate.opsForSet().add(userLikedSubjectKey, likedBO.getSubjectId());
            redisTemplate.opsForHash().putIfAbsent(USER_SUBJECT_LIKED_MAP_KEY, hashKey, SubjectLikedStatusEnum.LIKED.getCode());
        } else if (SubjectLikedStatusEnum.UNLIKED.getCode().equals(likedBO.getStatus())) {
            redisTemplate.opsForSet().remove(subjectLikedUserKey, likedBO.getLikeUserId());
            redisTemplate.opsForSet().remove(userLikedSubjectKey, likedBO.getSubjectId());
            redisTemplate.opsForHash().delete(USER_SUBJECT_LIKED_MAP_KEY, hashKey);
        }
        return true;
    }


    /**
     * 更新
     *
     * @param subjectLikedBO 更新信息
     * @return 结果
     */
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        return 1 == subjectLikedService.update(SubjectLikedBOConverter.INSTANCE.convertBo2Entity(subjectLikedBO));
    }

    /**
     * 删除
     *
     * @param subjectLikedBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(SubjectLikedBO subjectLikedBO) {
        return 1 == subjectLikedService.deleteById(subjectLikedBO.getId());
    }

    /**
     * 分页查询
     *
     * @param subjectLikedBO bo
     * @return 分页结果
     */
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        subjectLikedBO.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        subjectLikedBO.setStatus(SubjectLikedStatusEnum.LIKED.getCode());

        int start = (subjectLikedBO.getPageNo() - 1) * subjectLikedBO.getPageSize();
        int pageSize = subjectLikedBO.getPageSize();
        int total = (int) subjectLikedService.count(SubjectLikedBOConverter.INSTANCE.convertBo2Entity(subjectLikedBO));
        if (total == 0) {
            return new PageResult<>(subjectLikedBO.getPageNo(), pageSize, total, Collections.emptyList());
        }
        List<SubjectLiked> entityList = subjectLikedService.queryByPage(SubjectLikedBOConverter.INSTANCE.convertBo2Entity(subjectLikedBO), start, pageSize);
        List<SubjectLikedBO> boList = SubjectLikedBOConverter.INSTANCE.convertEntity2Bo(entityList);
        boList.forEach(bo -> {
            SubjectInfo subjectInfo = subjectInfoService.queryById(bo.getSubjectId());
            bo.setSubjectName(subjectInfo.getSubjectName());
        });
        return new PageResult<>(subjectLikedBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param subjectLikedBO bo
     * @return 结果
     */
    public SubjectLikedBO queryById(SubjectLikedBO subjectLikedBO) {
        SubjectLiked entity = subjectLikedService.queryById(subjectLikedBO.getId());
        return SubjectLikedBOConverter.INSTANCE.convertEntity2Bo(entity);
    }

    /**
     * 查询当前用户是否点赞过
     *
     * @param subjectLikedBO bo
     * @return 点赞结果
     */
    @Override
    public Boolean isLiked(SubjectLikedBO subjectLikedBO) {
        // 查询是否被当前用户点赞过
        String subjectLikedUserKey = SUBJECT_LIKED_USER_KEY + ":" + subjectLikedBO.getSubjectId().toString();
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(subjectLikedUserKey, UserContextHolder.getUserContext().getUserName()));
    }

    /**
     * 查询题目点赞数量
     *
     * @param subjectLikedBO bo
     * @return 点赞数量
     */
    @Override
    public Long likedNum(SubjectLikedBO subjectLikedBO) {
        // 查询是否被当前用户点赞过
        String subjectLikedUserKey = SUBJECT_LIKED_USER_KEY + ":" + subjectLikedBO.getSubjectId().toString();
        Long size = redisTemplate.opsForSet().size(subjectLikedUserKey);
        if (ObjectUtil.isNull(size)) {
            size = 0L;
        }
        return size;
    }

    /**
     * 同步点赞信息
     */
    @Override
    public void syncLiked(int size){
        int num = 0;

        HashOperations<String, String, Integer> hashOperations = redisTemplate.opsForHash();
        Cursor<Map.Entry<String, Integer>> cursor = hashOperations.scan(USER_SUBJECT_LIKED_MAP_KEY, ScanOptions.NONE);
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>();
        while (cursor.hasNext() && num < size) {
            entryList.add(cursor.next());
            num++;
        }
        try {
            cursor.close();
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "redis cursor close error", e);
        }

        List<SubjectLiked> likedList = entryList.stream().map(entry -> {
            String hashKey = entry.getKey();
            String[] split = hashKey.split(":");
            String userName = split[0];
            Long subjectId = Long.valueOf(split[1]);
            Integer status = entry.getValue();

            SubjectLiked subjectLiked = new SubjectLiked();
            subjectLiked.setLikeUserId(userName);
            subjectLiked.setSubjectId(subjectId);
            subjectLiked.setStatus(status);
            return subjectLiked;
        }).collect(Collectors.toList());

        subjectLikedService.insertBatch(likedList);
    }
}

