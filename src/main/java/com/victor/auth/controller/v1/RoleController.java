package com.victor.auth.controller.v1;

import com.victor.auth.common.constraints.Update;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.dao.entity.Role;
import com.victor.auth.model.dto.RoleDTO;
import com.victor.auth.model.dto.RoleResourceDTO;
import com.victor.auth.model.vo.RoleVo;
import com.victor.auth.service.RoleResourceRelationService;
import com.victor.auth.service.RoleService;
import com.victor.auth.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleResourceRelationService roleResourceRelationService;

    /**
     * 查看所有角色
     *
     * @return
     */
    @ApiOperation(value = "查看所有角色")
    @GetMapping("/listAllProduct")
    public ApiRespVo listProduct() {
        List<Role> list = roleService.list();
        List<RoleVo> roleVoList = BeanCopyUtil.copyListProperties(list, RoleVo::new);
        return R.ok(roleVoList);
    }

    /**
     * 添加角色
     *
     * @param roleDTO
     * @return
     */
    @ApiOperation("添加角色")
    @PostMapping("/add")
    public ApiRespVo createProduct(@Valid @RequestBody RoleDTO roleDTO) {
        Role role = new Role();
        BeanCopyUtil.copyProperties(roleDTO, role);
        role.setRoleId(null);
        roleService.save(role);
        return R.ok(role);
    }


    /**
     * 修改角色
     *
     * @param roleDTO
     * @return
     */
    @ApiOperation("修改角色")
    @PostMapping("/update")
    public ApiRespVo updateProduct(@Validated(Update.class) @RequestBody RoleDTO roleDTO) {
        Role role = new Role();
        BeanCopyUtil.copyProperties(roleDTO, role);
        roleService.updateById(role);
        return R.ok(role);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @ApiOperation("删除角色")
    @PostMapping("/delete/{roleId}")
    public ApiRespVo deleteProduct(@NotNull @PathVariable Long roleId) {
        roleService.removeById(roleId);
        return R.ok();
    }


    /**
     * 添加资源列表到角色
     *
     * @return
     */
    @ApiOperation("添加资源列表到角色")
    @PostMapping("/saveOrUpdateResource")
    public ApiRespVo saveOrUpdateResource(@Valid @RequestBody RoleResourceDTO roleResourceDTO) {
        roleResourceRelationService.saveOrUpdateRoleResourceList(roleResourceDTO);
        return R.ok();
    }

    /**
     * 查看角色已有资源列表
     *
     * @return
     */
    @ApiOperation("查看角色已有资源列表")
    @GetMapping("/queryResourceList")
    public ApiRespVo queryResourceList(@NotNull @RequestParam Long roleId) {
        return R.ok(roleResourceRelationService.queryResourceList(roleId));
    }
}
