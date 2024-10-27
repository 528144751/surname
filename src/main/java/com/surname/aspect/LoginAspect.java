package com.surname.aspect;

import com.surname.responseResult.ResponseResult;
import com.surname.dto.User;
import com.surname.service.LoginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录登出切面类
 */
@Component
@Aspect
public class LoginAspect {

    @Autowired
    private LoginService loginService;

    // 定义切入点
    @Pointcut("execution(* com.surname.controller.LoginController.login(..)) " +
            "|| execution(* com.surname.controller.LoginController.outLogin(..))")
    public void methodsToBeMonitored() {
    }

    // 在方法执行完切入
    @AfterReturning(value = "methodsToBeMonitored()", returning = "result")
    public void afterServiceMethod(JoinPoint joinPoint, Object result) throws Exception{
        //获取user对象
        User user  = (User) joinPoint.getArgs()[1];
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //如果成功保存日志信息
        ResponseResult responseResult = (ResponseResult) result;
        if (200 == responseResult.getCode()){
            loginService.insertUserLogs(user, methodName,result);
        }

    }
}
