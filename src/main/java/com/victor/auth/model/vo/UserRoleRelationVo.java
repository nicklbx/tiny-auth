package com.victor.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Data
@ToString
public class UserRoleRelationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 角色
     */
    private List<RoleVo> roleList;


}
