package com.victor.auth.service.impl;

import com.victor.auth.dao.entity.Role;
import com.victor.auth.dao.mapper.RoleMapper;
import com.victor.auth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
