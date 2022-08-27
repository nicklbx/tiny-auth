package com.victor.auth.common.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * ResultCodeEnum
 *
 * @Author nicklbx
 * @Date 2022/8/27 16:00
 */
@Getter
@ToString
public enum CodeEnum {
    SUCCESS(200, "请求成功"),
    PARAM_ILLEGAL(10000, "参数异常"),
    AUTH_FAILED(10001, "鉴权失败"),
    RESULT_NOT_FOUND(10002, "结果不存在"),
    SYSTEM_ERROR(-99, "系统异常");

    private int code;
    private String message;
    private static Map<Integer, CodeEnum> codeEnumMap = new HashMap<>();

    static {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            codeEnumMap.put(codeEnum.getCode(), codeEnum);
        }
    }

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static CodeEnum getCodeEnum(int code) {
        if (!codeEnumMap.containsKey(code)) {
            return CodeEnum.SYSTEM_ERROR;
        } else {
            return codeEnumMap.get(code);
        }
    }


}
