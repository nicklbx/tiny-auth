package com.victor.auth.exceptions.advice;

import com.victor.auth.common.enums.CodeEnum;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.exceptions.ApiCustomException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * controller统一异常处理逻辑实现
 *
 * @author liubx
 */

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    private String getBindResultMessage(BindingResult bindingResult) {
        if (Objects.isNull(bindingResult)) {
            return null;
        }
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        return allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                FieldError error = (FieldError) e;
                return e.getObjectName() + " " + error.getField() + " - " + e.getDefaultMessage();
            }
            return e.getObjectName() + " - " + e.getDefaultMessage();
        }).collect(Collectors.joining("|"));
    }

    /**
     * 处理参数效验异常 BindException
     */
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(BindException exception) {
        LOGGER.error("BindException error", exception);
        BindingResult bindingResult = exception.getBindingResult();
        return R.failed(CodeEnum.PARAM_ILLEGAL, getBindResultMessage(bindingResult));
    }

    /**
     * 处理参数效验异常 MethodArgumentNotValidException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(MethodArgumentNotValidException exception) {
        LOGGER.error("MethodArgumentNotValidException error", exception);
        BindingResult bindingResult = exception.getBindingResult();
        return R.failed(CodeEnum.PARAM_ILLEGAL, getBindResultMessage(bindingResult));
    }

    /**
     * 处理参数效验异常 ConstraintViolationException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(ConstraintViolationException exception) {
        LOGGER.error("ConstraintViolationException error", exception);
        return R.failed(CodeEnum.PARAM_ILLEGAL, exception.getMessage());
    }


    /**
     * 处理参数效验异常 MethodArgumentTypeMismatchException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(MethodArgumentTypeMismatchException exception) {
        LOGGER.error("MethodArgumentTypeMismatchException error", exception);
        return R.failed(CodeEnum.PARAM_ILLEGAL, exception.getMessage());
    }


    /**
     * ServletRequestBindingException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(ServletRequestBindingException exception) {
        LOGGER.error("ServletRequestBindingException error", exception);
        return R.failed(CodeEnum.PARAM_ILLEGAL);
    }

    /**
     * 处理 ApiCustomizedException
     */
    @ExceptionHandler(ApiCustomException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(ApiCustomException exception) {
        LOGGER.error("ApiCustomizedException error", exception);
        return R.failed(exception.getCodeEnum());
    }

    /**
     * 处理 HttpRequestMethodNotSupportedException
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(HttpRequestMethodNotSupportedException exception) {
        LOGGER.error("HttpRequestMethodNotSupportedException error", exception);

        return R.failed(CodeEnum.PARAM_ILLEGAL.getCode(), exception.getMessage());
    }


    /**
     * 处理其它异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiRespVo handleException(Exception exception) {
        LOGGER.error("handleException error", exception);
        return R.failed(CodeEnum.SYSTEM_ERROR);
    }

}
