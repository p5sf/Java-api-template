package com.demo.exception;

import com.demo.constant.StatusCode;
import lombok.Getter;

/**
 * @author Yan
 * Json 异常
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(StatusCode status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
