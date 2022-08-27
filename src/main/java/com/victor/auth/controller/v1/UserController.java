package com.victor.auth.controller.v1;

import com.victor.auth.common.constraints.Update;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.dao.entity.User;
import com.victor.auth.model.dto.UserDTO;
import com.victor.auth.model.dto.UserRoleDTO;
import com.victor.auth.model.vo.UserVo;
import com.victor.auth.service.RoleService;
import com.victor.auth.service.UserRoleRelationService;
import com.victor.auth.service.UserService;
import com.victor.auth.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
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
 * 用户表 前端控制器
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleRelationService userRoleRelationService;
    @Resource
    private RoleService roleService;

    /**
     * 查看所有用户
     *
     * @return
     */
    @ApiOperation(value = "查看所有用户")
    @GetMapping("/listAllProduct")
    public ApiRespVo listProduct() {
        List<User> list = userService.list();
        List<UserVo> userVoList = BeanCopyUtil.copyListProperties(list, UserVo::new);
        return R.ok(userVoList);
    }

    /**
     * 添加用户
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public ApiRespVo createProduct(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        BeanCopyUtil.copyProperties(userDTO, user);
        //TODO 密码转为密文
        user.setUserId(null);
        userService.save(user);
        return R.ok(user);
    }


    /**
     * 修改用户
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("修改用户")
    @PostMapping("/update")
    public ApiRespVo updateProduct(@Validated(Update.class) @RequestBody UserDTO userDTO) {
        User user = new User();
        BeanCopyUtil.copyProperties(userDTO, user);
        userService.updateById(user);
        return R.ok(user);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete/{userId}")
    public ApiRespVo deleteProduct(@NotNull @PathVariable Long userId) {
        userService.removeById(userId);
        return R.ok();
    }

    /**
     * 添加角色列表到用户
     *
     * @return
     */
    @ApiOperation("用户添加角色列表")
    @PostMapping("/saveOrUpdateRole")
    public ApiRespVo saveOrUpdateRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        userRoleRelationService.saveOrUpdateUserRoleList(userRoleDTO);
        return R.ok();
    }

    /**
     * 查询用户角色列表
     *
     * @return
     */
    @ApiOperation("查询用户角色列表")
    @GetMapping("/queryRoleList")
    public ApiRespVo queryRoleList(@NotNull @RequestParam Long userId) {
        return R.ok(userRoleRelationService.queryRoleList(userId));
    }


}
