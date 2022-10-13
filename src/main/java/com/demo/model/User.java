package com.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 21:00
 */

@Data
@ApiModel("用户实体")
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "User")
@Builder
@Accessors(chain = true)
public class User implements Serializable {

    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
