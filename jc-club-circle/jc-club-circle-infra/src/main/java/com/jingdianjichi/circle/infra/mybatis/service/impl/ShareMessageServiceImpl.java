package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.model.ShareMessage;
import com.jingdianjichi.circle.infra.mybatis.dao.ShareMessageMapper;
import com.jingdianjichi.circle.infra.mybatis.service.IShareMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Service
public class ShareMessageServiceImpl extends ServiceImpl<ShareMessageMapper, ShareMessage> implements IShareMessageService {

}
