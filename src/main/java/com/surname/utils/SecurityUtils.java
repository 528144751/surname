package com.surname.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.surname.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class SecurityUtils {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    //设置日期格式
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //密钥
    private static final String salt = "ygq666";

    /**
     * 密码加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String getToken(User user ,  String tokenSecret,Integer tokenExpireTime) {
        Date expireAt = new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000);
        String sign = JWT.create()
                //发行人
                .withIssuer("YGQ")
                //存放数据
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUserName())
                //过期时间
                .withExpiresAt(expireAt)
                .sign(Algorithm.HMAC256(tokenSecret));
        return sign;
    }

    /**
     * 对token进行验证
     *
     * @param token
     * @param tokenSecret
     * @return
     */
    public static Boolean verifyToken(String token, String tokenSecret) {
        DecodedJWT decodedJWT = null;
        try {
            //创建token验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(tokenSecret)).withIssuer("YGQ").build();
            decodedJWT = jwtVerifier.verify(token);
            log.info("token认证通过，用户：{}，过期时间：{}", getUserName(token), sdf.format(decodedJWT.getExpiresAt()));
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            log.error("token认证失败，用户：{}", getUserName(token));
            return false;
        }
        return true;
    }

    public static String getUserName(String token) {
        return JWT.decode(token).getClaims().get("username").asString();
    }

}
