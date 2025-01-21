package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.model.SensitiveWords;
import com.jingdianjichi.circle.infra.mybatis.dao.SensitiveWordsMapper;
import com.jingdianjichi.circle.infra.mybatis.service.ISensitiveWordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 敏感词表 服务实现类
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements ISensitiveWordsService {

}
