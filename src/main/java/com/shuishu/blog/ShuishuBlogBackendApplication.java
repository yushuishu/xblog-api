package com.shuishu.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;


/**
 * @Author ：谁书-ss
 * @Date   ： 2022-12-24 16:46
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：blog
 * <p>@Slf4j</p>：日志
 * <p>@EnableAsync</p>：开启异步
 * <p>@EntityScan</p>：发现定义的数据库映射实体类 @Entity
 * <p>@EnableJpaRepositories</p>：发现定义的实体对象 @Repository类
 * <p>@EnableJpaAuditing</p>：支持在字段或方法上进行注解 @CreateDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class ShuishuBlogBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ShuishuBlogBackendApplication.class, args);

        // 当前项目环境
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        log.info("Spring.Profiles.Active = {}", environment.getProperty("spring.profiles.active"));
        String contextPath = environment.getProperty("server.servlet.context-path");
        String port = environment.getProperty("server.port");
        log.info("server.servlet.context-path = {}", contextPath);
        log.info("server.port = {}", port);
        if(contextPath != null) {
            log.info("Swagger = http://localhost:" + port + contextPath + "/doc.html");
        }else {
            log.info("Swagger = http://localhost:" + port  + "/doc.html");
        }
    }

    /**
     * 开启方法参数值校验注解
     * @return -
     */
    @Bean
    public MethodValidationPostProcessor mvp(){
        return new MethodValidationPostProcessor();
    }

}
