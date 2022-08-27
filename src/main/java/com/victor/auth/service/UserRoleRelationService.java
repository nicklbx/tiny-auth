package com.victor.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.victor.auth.dao.entity.UserRoleRelation;
import com.victor.auth.model.dto.UserRoleDTO;
import com.victor.auth.model.vo.UserRoleRelationVo;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
public interface UserRoleRelationService extends IService<UserRoleRelation> {

    /**
     * 保存或修改用户角色列表
     *
     * @param userRoleDTO
     */
    void saveOrUpdateUserRoleList(UserRoleDTO userRoleDTO);

    /**
     * 查询用户角色列表
     *
     * @param userId
     * @return
     */
    UserRoleRelationVo queryRoleList(Long userId);
}
