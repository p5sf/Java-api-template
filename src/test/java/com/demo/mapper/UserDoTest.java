package com.demo.mapper;

import com.demo.model.Profile;
import com.demo.model.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月19日 17:57
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserDoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insertUser(){
        UserDo userDo = new UserDo();
        userDo.setUsername("小米");
        userDo.setId(1);
        userDo.setCreateTime(new Date());
        Profile profile = new Profile();
        profile.setNickName("xiao");
        profile.setGender(1);
        profile.setAddress("上海");
        userDo.setProfile(profile);
        mongoTemplate.insert(userDo);
    }

    @Test
    public void remove() {
        Integer id = 1;
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,UserDo.class);
    }

    @Test
    public void findAllBy(){
        UserDo userDo = mongoTemplate.findOne(new Query(Criteria.where("username").is("小米")), UserDo.class);
        System.out.println(userDo);
    }
}
