package com.demo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月19日 17:52
 */

@ApiModel("用户基本信息")
@Data
public class Profile {

    private String nickName;

    private Integer gender;

    private String address;
}
