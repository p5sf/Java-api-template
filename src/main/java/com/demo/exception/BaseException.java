package com.demo.exception;

import com.demo.constant.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yan
 * 基本异常类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(StatusCode status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
