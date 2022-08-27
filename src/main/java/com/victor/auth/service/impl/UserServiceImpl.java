package com.victor.auth.service.impl;

import com.victor.auth.dao.entity.User;
import com.victor.auth.dao.mapper.UserMapper;
import com.victor.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
