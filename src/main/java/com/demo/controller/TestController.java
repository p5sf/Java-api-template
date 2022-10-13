package com.demo.controller;

import com.demo.common.ApiResponse;
import com.demo.constant.StatusCode;
import com.demo.exception.JsonException;
import com.demo.exception.PageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yan
 */
@Controller
public class TestController {

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(StatusCode.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(StatusCode.UNKNOWN_ERROR);
    }
}
