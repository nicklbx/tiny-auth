package com.victor.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.victor.auth.common.enums.CodeEnum;
import com.victor.auth.dao.entity.RoleResourceRelation;
import com.victor.auth.dao.mapper.RoleResourceRelationMapper;
import com.victor.auth.exceptions.ApiCustomException;
import com.victor.auth.model.dto.RoleResourceDTO;
import com.victor.auth.model.vo.ResourceVo;
import com.victor.auth.model.vo.RoleResourceRelationVo;
import com.victor.auth.service.ResourceService;
import com.victor.auth.service.RoleResourceRelationService;
import com.victor.auth.utils.BeanCopyUtil;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色资源关联表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class RoleResourceRelationServiceImpl extends
        ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements RoleResourceRelationService {

    @Resource
    private ResourceService resourceService;

    /**
     * 保存或修改角色资源列表
     *
     * @param resourceDTO
     */
    @Transactional
    @Override
    public void saveOrUpdateRoleResourceList(RoleResourceDTO resourceDTO) {
        if (resourceDTO == null) {
            log.error("resourceDTO is null");
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "resourceDTO is null");
        }
        List<Long> resourceIdList = resourceDTO.getResourceIdList();
        if (resourceIdList == null) {
            log.error("resourceIdList is null");
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "resourceIdList is null");
        }
        //清除角色原有的资源列表
        removeById(resourceDTO.getRoleId());
        //保存roleResource关系
        List<RoleResourceRelation> resourceRelationList = resourceIdList.stream().map(resourceId -> {
            RoleResourceRelation roleResource = new RoleResourceRelation();
            roleResource.setRoleId(resourceDTO.getRoleId());
            roleResource.setResourceId(resourceId);
            return roleResource;
        }).collect(Collectors.toList());
        saveOrUpdateBatch(resourceRelationList);
    }

    /**
     * 查询角色资源列表
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleResourceRelationVo queryResourceList(Long roleId) {
        if (roleId == null) {
            log.error("roleId is null");
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "roleId is null");
        }
        //查询角色对应资源关系列表
        List<RoleResourceRelation> resourceRelationList = list(
                new QueryWrapper<RoleResourceRelation>().eq("role_id", roleId));
        //获取角色资源id列表
        if (resourceRelationList == null) {
            log.error("resourceRelationList is null");
            throw new ApiCustomException(CodeEnum.PARAM_ILLEGAL, "resourceRelationList is null");
        }
        Set<Long> resourceSet = resourceRelationList.stream().map(relation -> {
            return relation.getResourceId();
        }).collect(Collectors.toSet());
        //根据资源id获取资源详情列表
        List<com.victor.auth.dao.entity.Resource> resourceList = resourceService.list();
        List<ResourceVo> resourceVoList = resourceList.stream()
                .filter(resource -> resourceSet.contains(resource.getResourceId()))
                .map(resource -> {
                    ResourceVo resourceVo = new ResourceVo();
                    BeanCopyUtil.copyProperties(resource, resourceVo);
                    return resourceVo;
                }).collect(Collectors.toList());
        //结果vo封装
        RoleResourceRelationVo resourceRelationVo = new RoleResourceRelationVo();
        resourceRelationVo.setRoleId(roleId);
        resourceRelationVo.setResourceList(resourceVoList);
        return resourceRelationVo;
    }
}
