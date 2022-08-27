package com.victor.auth.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Data
@ToString
public class ResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 层级0 1 2
     */
    private Integer level;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 资源类型 0-菜单 1-按钮
     */
    private Integer typeId;

    /**
     * 资源类型名称
     */
    private String typeName;

    /**
     * 接口地址 请求方法+path
     */
    private String url;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 图标
     */
    private String icon;

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
