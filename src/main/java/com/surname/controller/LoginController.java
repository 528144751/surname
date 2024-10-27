package com.surname.controller;

import com.surname.responseResult.ResponseResult;
import com.surname.dto.User;
import com.surname.enumEntity.ResultStatus;
import com.surname.service.LoginService;
import com.surname.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录注册
 */
@Slf4j
@RestController
@RequestMapping("/surname")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //签名
    @Value("${token.secret}")
    private  String tokenSecret;

    //过期时间
    @Value("${token.expireTime}")
    private  Integer expireTime;

    /**
     * 用户登录接口
     * @param user 用户对象
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(HttpServletRequest request, @RequestBody User user) {
        log.info("LoginController.login方法： 参数信息： " + user.toString());
        //参数校验
        ResponseResult responseResult = paramsCheck(user);
        if (200 == responseResult.getCode()){
            //查询数据库中用户名是否存在
            User userEntity = loginService.findUserName(user.getUserName());
            if (null == userEntity){
                return ResponseResult.error(ResultStatus.USER_NOT_FIND.getCode(),ResultStatus.USER_NOT_FIND.getMessage());
            }
            //校验输入密码是否正确
            String password = SecurityUtils.inputPassToFormPass(user.getUserPassword());
            if (!password.equals(userEntity.getUserPassword())){
                return ResponseResult.error(ResultStatus.USER_PASSWORD_ERROR.getCode(),ResultStatus.USER_PASSWORD_ERROR.getMessage());
            }
            //生成token
            String token = SecurityUtils.getToken(user,tokenSecret,expireTime);
            //将token保存在session中
            request.getSession().setAttribute("user", token);
            return ResponseResult.success(token);
        }
        return responseResult;
    }


    /**
     * 用户退出登录接口
     * @param user 用户对象
     * @return
     */
    @PostMapping("/outLogin")
    public ResponseResult outLogin(HttpServletRequest request,@RequestBody User user) {
        log.info("LoginController.outLogin方法： 参数信息： " + user.toString());
        //参数校验
        ResponseResult responseResult = paramsCheck(user);
        if (200 == responseResult.getCode()){
            request.getSession().removeAttribute("user");
            return ResponseResult.success(null);
        }
        return responseResult;
    }


    /**
     * 测试API
     * @return
     */
    @GetMapping("/test")
    public ResponseResult test(){
        //抛异常测试全局捕获
        Integer[] arr = null;
        //System.out.println(arr[1]);
        return ResponseResult.success("test");
    }

    /**
     * 用户注册接口
     * @param user 用户对象
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        log.info("LoginController.register方法： 参数信息： " + user.toString());
        //参数校验
        ResponseResult responseResult = paramsCheck(user);
        if (200 == responseResult.getCode()){
            //校验用户是否存在
            User userEntity = loginService.findUserName(user.getUserName());
            if (userEntity != null){
                return ResponseResult.error(ResultStatus.USER_EXIST.getCode(),ResultStatus.USER_EXIST.getMessage());
            }
            //保存用户信息
            loginService.register(user);
            return ResponseResult.success(null);
        }
        return responseResult;
    }


    private ResponseResult paramsCheck(User user) {
        //校验传递的user对象是否为空
        if (null == user){
            return ResponseResult.error(ResultStatus.BAD_REQUEST.getCode(),ResultStatus.BAD_REQUEST.getMessage());
        }
        //校验用户名是否为空
        if (null == user.getUserName() || "".equals(user.getUserName())){
            return ResponseResult.error(ResultStatus.USER_NAME_NULL.getCode(),ResultStatus.USER_NAME_NULL.getMessage());
        }
        //校验密码是否为空
        if (null == user.getUserPassword() || "".equals(user.getUserPassword())){
            return ResponseResult.error(ResultStatus.USER_PASSWORD_NULL.getCode(),ResultStatus.USER_PASSWORD_NULL.getMessage());
        }
        return ResponseResult.success(null);
    }

}
