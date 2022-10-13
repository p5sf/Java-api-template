package com.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author YanZhao
 * @description 自动填充策略
 * @date 2022年09月19日 10:30
 */
@Slf4j
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill time");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill time");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
