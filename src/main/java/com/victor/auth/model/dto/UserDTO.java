package com.victor.auth.model.dto;

import com.victor.auth.common.constraints.Update;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Data
@ToString
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户id不为空", groups = {Update.class})
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 图片
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 状态 0启用 1禁用
     */
    private Integer status;


}
