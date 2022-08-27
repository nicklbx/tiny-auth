package com.victor.auth.exceptions;

import com.victor.auth.common.enums.CodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * ApiCustomException
 *
 * @Author victor
 * @Date 2022/8/27 16:40
 */
@Data
@ToString
public class ApiCustomException extends RuntimeException {

    private int code;
    private CodeEnum codeEnum;
    private String message;

    public ApiCustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiCustomException(CodeEnum codeEnum, String message) {
        super(message);
        this.codeEnum = codeEnum;
        this.message = message;
    }


}
