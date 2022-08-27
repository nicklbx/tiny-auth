package com.victor.auth.model.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 * UserRoleDTO
 *
 * @Author nicklbx
 * @Date 2022/8/27 20:43
 */
@Data
@ToString
public class UserRoleDTO {

    @NotNull
    private Long userId;
    @NotNull
    @Size(min = 1)
    private List<Long> roleIdList;

}
