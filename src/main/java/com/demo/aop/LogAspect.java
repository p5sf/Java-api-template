package com.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月27日 15:11
 */

@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.demo.controller.*.*(..))")
    public void logs(){

    }

    @Before("logs()")
    public void doBefore(){
        log.info("--doBefore");
    }

    @After("logs()")
    public void doAfter(){
        log.info("--doAfter");

    }

    @AfterReturning(pointcut = "logs()", returning = "result")
    public void doAfterReturn(Object result) {

        log.info("--afterReturn" + result);

    }
}
