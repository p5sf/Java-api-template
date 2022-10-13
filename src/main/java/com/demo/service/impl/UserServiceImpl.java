package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 22:36
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUserByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);

        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> findAllUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    @Override
    public IPage<User> findUserByPageAndSort() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(1,5);
        page.setDesc("id");
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
