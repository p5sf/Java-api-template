package com.demo.constant;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 22:42
 */

import lombok.Getter;


@Getter
public enum StatusCode {

    /**
     * 操作成功
     */
    OK(200, "操作成功"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
