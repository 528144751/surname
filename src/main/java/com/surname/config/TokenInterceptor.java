package com.surname.config;

import com.alibaba.fastjson.JSONObject;
import com.surname.enumEntity.ResultStatus;
import com.surname.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token校验类
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    //签名
    @Value("${token.secret}")
    private String tokenSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("token");
        //校验token和session是否为空，如果不为空就检查token
        if (authHeader != null && null != request.getSession().getAttribute("user")) {
            if (SecurityUtils.verifyToken(authHeader, tokenSecret)){
                return true;
            }
        }
        String requestURI = request.getRequestURI();
        JSONObject json = new JSONObject();
        json.put("message", "请求：" + requestURI + ResultStatus.UNAUTHORIZED.getMessage());
        json.put("code", ResultStatus.UNAUTHORIZED.getCode());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(json.toString());
        return false;
    }
}
