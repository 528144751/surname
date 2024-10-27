# 青岛讯和数聚项目

## 功能介绍
提供用户注册、登录、退出登陆、用户规则流程功能

## 项目架构
SpringBoot2.7.8、 Mysql8、 maven3.6.3

## 部署
1 拉取git分支 
    git clone -b dev https://github.com/528144751/surname.git
2 maven下载所需依赖
    mvn clean install

## 访问地址
项目接口：
登录：localhost:8080/surname/login
退出：localhost:8080/surname/outLogin
用户规则：localhost:8080/surname/swagger/action?action=action1

swagger: http://localhost:8080/swagger-ui.htm


## 项目目录结构
**aspect目录：**
    登录登出切面目录
**config目录：**
    拦截器、swagger配置文件、token校验文件
**controller目录：**
    交互入口文件
**dto目录：**
    数据库实体类
**enumEntity目录：**
    统一返回响应枚举类
**factory目录：**
    工厂类
**mapper目录:**
    mybatis文件
**responseResult目录:**
    统一返回实体类
**service目录：**
    业务接口类和实现类
**service\impl\action目录:**
    用户规则流程实现类
**utils目录:**
    token加密验证文件
**resources.com.surname.mapper目录**
    mybatis文件 
**resources.db目录**
    sql脚本文件
**resources.interface目录**
    postman接口测试文件

**UserLoginTime.txt文件：**
    记录Admin用户最近登录时间文件
    