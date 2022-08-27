package com.victor.auth.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.victor.auth.common.enums.CodeEnum;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.model.vo.PageVo;
import org.springframework.beans.BeanUtils;

/**
 * ApiRespUtils
 *
 * @Author nicklbx
 * @Date 2022/8/27 16:09
 */
public class R {

    /**
     * 成功返回
     *
     * @return
     */
    public static ApiRespVo ok() {
        ApiRespVo<Object> apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(CodeEnum.SUCCESS.getCode());
        apiRespVo.setMessage(CodeEnum.SUCCESS.getMessage());
        return apiRespVo;
    }

    /**
     * 返回数据
     *
     * @param data
     * @return
     */
    public static <T> ApiRespVo ok(T data) {
        ApiRespVo<Object> apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(CodeEnum.SUCCESS.getCode());
        apiRespVo.setMessage(CodeEnum.SUCCESS.getMessage());
        apiRespVo.setData(data);
        return apiRespVo;
    }

    /**
     * 返回分页数据
     *
     * @param page
     * @return
     */
    public static <T> ApiRespVo ok(Page<T> page) {
        ApiRespVo apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(CodeEnum.SUCCESS.getCode());
        apiRespVo.setMessage(CodeEnum.SUCCESS.getMessage());
        if (page != null) {
            PageVo<T> pageVo = new PageVo<>();
            BeanUtils.copyProperties(page, pageVo);
            apiRespVo.setData(pageVo);
        }
        return apiRespVo;
    }

    /**
     * 根据codeeNum失败返回
     *
     * @param codeEnum
     * @return
     */
    public static ApiRespVo failed(CodeEnum codeEnum) {
        CodeEnum resEnum = CodeEnum.getCodeEnum(codeEnum.getCode());
        ApiRespVo apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(resEnum.getCode());
        apiRespVo.setMessage(resEnum.getMessage());
        return apiRespVo;
    }

    /**
     * 根据codeeNum失败返回
     *
     * @param codeEnum
     * @return
     */
    public static <T> ApiRespVo failed(CodeEnum codeEnum, T data) {
        CodeEnum resEnum = CodeEnum.getCodeEnum(codeEnum.getCode());
        ApiRespVo apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(resEnum.getCode());
        apiRespVo.setMessage(resEnum.getMessage());
        apiRespVo.setData(data);
        return apiRespVo;
    }

    /**
     * 根据code失败返回
     *
     * @param code
     * @return
     */
    public static ApiRespVo failed(int code) {
        CodeEnum resEnum = CodeEnum.getCodeEnum(code);
        ApiRespVo apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(resEnum.getCode());
        apiRespVo.setMessage(resEnum.getMessage());
        return apiRespVo;
    }

    /**
     * 根据code和message自定义返回
     *
     * @param code
     * @param message
     * @return
     */
    public static ApiRespVo failed(int code, String message) {
        CodeEnum resEnum = CodeEnum.getCodeEnum(code);
        ApiRespVo apiRespVo = new ApiRespVo<>();
        apiRespVo.setCode(resEnum.getCode());
        apiRespVo.setMessage(message);
        return apiRespVo;
    }


}
