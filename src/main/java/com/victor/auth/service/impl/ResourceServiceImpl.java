package com.victor.auth.service.impl;

import com.victor.auth.dao.entity.Resource;
import com.victor.auth.dao.mapper.ResourceMapper;
import com.victor.auth.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
