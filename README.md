# shuishu-blog-backend

<p>
  <a href="https://www.oracle.com/java/technologies/javase/17u-relnotes.html"><img src="https://img.shields.io/badge/jdk-%3E=17.0.0-blue.svg" alt="jdk compatility"></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/springboot-%3E=3.0.0-green.svg" alt="springboot compatility"></a>
  <a href="http://querydsl.com/"><img src="https://img.shields.io/badge/querydsl-%3E=5.0-orange.svg" alt="querydsl compatility"></a>
</p>

## 介绍

项目基于 [springboot@3.0.5](https://spring.io/projects/spring-boot)，[QueryDsl@5.0](http://querydsl.com/) 系列开发，开发环境使用[jdk@17.x](https://www.oracle.com/java/technologies/downloads/#java17)，[PostgreSQL-Server@14.5](https://www.postgresql.org/)。

个人博客系统，后台服务。

**其它源码**

后台服务server：https://github.com/yushuishu/shuishu-blog-backend

后台管理web：https://github.com/yushuishu/shuishu-blog-front-admin

门户网站portal：https://github.com/yushuishu/shuishu-blog-front-web


## 预览

## 项目结构说明

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

## 接口文档

http://localhost:8080/doc.html

## 使用


## 引用

