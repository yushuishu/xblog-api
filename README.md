# xblog-api

<p>
  <a href="https://www.oracle.com/java/technologies/javase/17u-relnotes.html"><img src="https://img.shields.io/badge/jdk-%3E=17.0.0-blue.svg" alt="jdk compatility"></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/springboot-%3E=3.0.0-green.svg" alt="springboot compatility"></a>
  <a href="http://querydsl.com/"><img src="https://img.shields.io/badge/querydsl-%3E=5.0-orange.svg" alt="querydsl compatility"></a>
</p>

## 介绍

项目基于 [springboot@3.0.5](https://spring.io/projects/spring-boot)，[QueryDsl@5.0](http://querydsl.com/) 系列开发，开发环境使用[jdk@17.x](https://www.oracle.com/java/technologies/downloads/#java17)，[PostgreSQL-Server@14.5](https://www.postgresql.org/)。

个人博客系统，后台服务。

**其它源码**

后台服务server：https://github.com/yushuishu/xblog-api

后台管理web：https://github.com/yushuishu/xblog-web-admin

门户网站portal：https://github.com/yushuishu/xblog-web-website


## 预览

<img width="1022" alt="shuishu-blog-backend-01" src="https://github.com/yushuishu/shuishu-blog-backend/assets/50919172/bdbe5fe7-e097-436e-a964-a9bc8e82e39a">


## 项目结构

```text
project
├─src
│  └─main
│      ├─java
│      │  └─com
│      │      └─shuishu
│      │          └─blog
│      │              ├─business                # 业务接口和服务
│      │              └─common
│      │                  ├─config              # 项目基本配置：全局异常、jdbc、redis、认证和授权、swagger
│      │                  ├─constant            # 常量
│      │                  ├─domain              # 实体PO
│      │                  ├─enums               # 枚举
│      │                  └─utils               # 工具类
│      └─resources
│          └─swagger                            # swagger的Markdown文档
```

## 核心功能

|     功能     |                             描述                             |
| :----------: | :----------------------------------------------------------: |
| 账号注册登录 |                       资源权限访问控制                       |
|  角色、资源  |       分为超级管理员和普通角色，有着不同的资源访问权限       |
|     行业     |                  账号个人信息，用户所在行业                  |
|     文章     |                       文章的编写，发布                       |
|   文章标签   |                  文章内容的标签，内容关键字                  |
|   数据面板   |       文章数据统计、短信邮件数据统计、用户个人数据统计       |
|   定时任务   |               制定规则，定时的执行一些业务操作               |
|   系统监控   | 系统运行时，各项指标数据，例如：CPU使用率、内存使用率、jvm各项数据 |
|    工具箱    |      日常使用工具，例如：SON工具、日期工具、加解密工具       |



## 接口文档

http://localhost:8080/doc.html

## 部署



## 引用

