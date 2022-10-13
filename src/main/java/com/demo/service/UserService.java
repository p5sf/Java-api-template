package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 22:35
 */

@Service
public interface UserService {


    public User findUserByName(String name);

    List<User> findAllUser();

    IPage<User> findUserByPageAndSort();

   User getUserByName(String name);

}
