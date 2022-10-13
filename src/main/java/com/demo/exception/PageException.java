package com.demo.exception;

import com.demo.constant.StatusCode;
import lombok.Getter;

/**
 * @author Yan
 * 页面 异常
 */
@Getter
public class PageException extends BaseException {

    public PageException(StatusCode status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}
