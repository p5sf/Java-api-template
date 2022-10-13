package com.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月19日 17:50
 */


@Document("userDo")
@Data
public class UserDo {

    @Id
    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    private Profile profile;
}
