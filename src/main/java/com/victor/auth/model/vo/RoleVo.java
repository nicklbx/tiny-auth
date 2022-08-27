package com.victor.auth.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
