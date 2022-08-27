package com.victor.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.victor.auth.dao.entity.RoleResourceRelation;
import com.victor.auth.model.dto.RoleResourceDTO;
import com.victor.auth.model.vo.RoleResourceRelationVo;

/**
 * <p>
 * 角色资源关联表 服务类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
public interface RoleResourceRelationService extends IService<RoleResourceRelation> {

    /**
     * 保存或修改角色资源列表
     *
     * @param userRoleDTO
     */
    void saveOrUpdateRoleResourceList(RoleResourceDTO roleResourceDTO);

    /**
     * 查询角色资源列表
     *
     * @param roleId
     * @return
     */
    RoleResourceRelationVo queryResourceList(Long roleId);
}
