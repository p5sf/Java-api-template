package com.demo.mapper;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月19日 13:52
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByUser() {
        Integer id = 1;
        User user = userMapper.selectById(id);
        log.info("user = {}", JSONUtil.toJsonStr(user));
        System.out.println(user+"+++++++++++");
    }

    @Test
    public void insertUserByName() {
        User user = new User().setName("alice").setAge(20).setEmail("123457@qq.com");
        int l = userMapper.insert(user);
        log.info("l={}", l);
        System.out.println(user);
    }

    @Test
    public void findUserByPageAndSort(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(1,5);
        page.setDesc("id");
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        System.out.println(userIPage);
    }
}
