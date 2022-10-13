package com.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.model.User;
import com.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 21:28
 */

@Api(tags = "用户相关接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户名称" ,httpMethod = "POST",response = User.class,notes = "Users")
    @GetMapping("getName")
    public User getName(@ApiParam(required = true,name = "name",value = "用户名称") String name) {
         return userService.findUserByName("Tom");
    }

    @GetMapping(value = "findAll")
    public List<User> selectAll(){
        return userService.findAllUser();
    }

    @GetMapping("findAllPage")
    public IPage<User> userIPage(){
        return userService.findUserByPageAndSort();
    }


    @GetMapping("getUser")
    public User getUser(){
        return userService.getUserByName("Tom");
    }
}
