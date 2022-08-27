package com.victor.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.victor.auth.common.constraints.Update;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Data
@ToString
@TableName("sys_role")
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品ID不为空", groups = {Update.class})
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;


}
