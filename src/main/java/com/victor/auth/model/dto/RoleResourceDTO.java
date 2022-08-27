package com.victor.auth.model.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 * RoleResourceDTO
 *
 * @Author victor
 * @Date 2022/8/27 21:35
 */
@Data
@ToString
public class RoleResourceDTO {

    @NotNull
    private Long roleId;

    @NotNull
    @Size(min = 1)
    private List<Long> resourceIdList;
}
