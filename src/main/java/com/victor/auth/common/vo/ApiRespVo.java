package com.victor.auth.common.vo;

import lombok.Data;
import lombok.ToString;

/**
 * ApiResp
 *
 * @Author nicklbx
 * @Date 2022/8/27 16:09
 */
@Data
@ToString
public class ApiRespVo<T> {

    private int code;
    private String message;
    private T data;
}
