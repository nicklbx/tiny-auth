package com.victor.auth.controller.web;

import com.victor.auth.common.constraints.Insert;
import com.victor.auth.common.enums.CodeEnum;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.model.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController
 *
 * @Author victor
 * @Date 2022/8/27 22:15
 */
@RestController
@RequestMapping("/web")
public class LoginController {

    /**
     * 登录接口
     *
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public ApiRespVo login(@Validated(Insert.class) @RequestBody UserDTO userDTO) {
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        if (userName.equals("admin")
                && password.equals("123456")) {
            HashMap<String, String> map = new HashMap<>();
            map.put("token", "jwtToken");
            return R.ok(map);
        }
        return R.failed(CodeEnum.AUTH_FAILED.getCode(), "登录失败");
    }


    /**
     * 登出接口
     *
     * @return
     */
    @ApiOperation(value = "登出接口")
    @PostMapping("/logout")
    public ApiRespVo logout() {
        //todo 清除jwt token
        return R.ok();
    }


}
