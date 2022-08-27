package com.victor.auth.service.impl;

import com.victor.auth.dao.entity.UserRoleRelation;
import com.victor.auth.dao.mapper.UserRoleRelationMapper;
import com.victor.auth.service.UserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements UserRoleRelationService {

}
