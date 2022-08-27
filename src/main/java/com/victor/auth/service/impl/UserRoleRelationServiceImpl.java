package com.victor.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.victor.auth.common.enums.CodeEnum;
import com.victor.auth.dao.entity.Role;
import com.victor.auth.dao.entity.UserRoleRelation;
import com.victor.auth.dao.mapper.UserRoleRelationMapper;
import com.victor.auth.exceptions.ApiCustomException;
import com.victor.auth.model.dto.UserRoleDTO;
import com.victor.auth.model.vo.RoleVo;
import com.victor.auth.model.vo.UserRoleRelationVo;
import com.victor.auth.service.RoleService;
import com.victor.auth.service.UserRoleRelationService;
import com.victor.auth.utils.BeanCopyUtil;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Slf4j
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements
        UserRoleRelationService {

    @Resource
    private RoleService roleService;

    /**
     * 保存或修改用户角色列表
     *
     * @param userRoleDTO
     */
    @Transactional
    @Override
    public void saveOrUpdateUserRoleList(UserRoleDTO userRoleDTO) {
        if (userRoleDTO == null) {
            log.warn("userRoleDTO is null, dto={}", userRoleDTO);
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "dto is null");
        }
        //删除原有用户角色列表
        remove(new QueryWrapper<UserRoleRelation>().eq("user_id", userRoleDTO.getUserId()));

        //组装UserRoleRelation
        List<UserRoleRelation> roleRelationList = userRoleDTO.getRoleIdList().stream().map(roleId -> {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userRoleDTO.getUserId());
            userRoleRelation.setRoleId(roleId);
            return userRoleRelation;
        }).collect(Collectors.toList());
        //调用IService方法保存结果
        saveOrUpdateBatch(roleRelationList);
    }

    /**
     * 查询用户角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public UserRoleRelationVo queryRoleList(Long userId) {
        if (userId == null) {
            log.error("userId is null");
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "userId is null");
        }
        //调用IService方法，查询用户角色关系列表
        List<UserRoleRelation> relationList = list(
                new QueryWrapper<UserRoleRelation>().eq("user_id", userId));
        if (relationList == null || relationList.size() == 0) {
            log.error("relationList is null");
            throw new ApiCustomException(CodeEnum.RESULT_NOT_FOUND, "relationList is null");
        }
        //获取获取角色id
        Set<Long> roleIdSet = relationList.stream().map(x -> x.getRoleId()).collect(Collectors.toSet());
        //根据角色id查询角色详情vo列表
        List<Role> roleList = roleService.list();
        List<RoleVo> filterRoleList = roleList.stream().filter(role -> {
            return roleIdSet.contains(role.getRoleId());
        }).map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanCopyUtil.copyProperties(role, roleVo);
            return roleVo;
        }).collect(Collectors.toList());

        //用户角色关系vo封装
        UserRoleRelationVo relationVo = new UserRoleRelationVo();
        relationVo.setUserId(userId);
        relationVo.setRoleList(filterRoleList);

        return relationVo;
    }
}
