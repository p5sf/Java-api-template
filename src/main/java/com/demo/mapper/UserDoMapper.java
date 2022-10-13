package com.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Yan
 */

@Repository
public class UserDoMapper {

    @Autowired
    private MongoTemplate mongoTemplate;
}
