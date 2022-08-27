package com.victor.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.victor.auth.common.constraints.Update;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
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
@TableName("sys_resource")
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品ID不为空", groups = {Update.class})
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


}
