package com.shuishu.blog.common.config.swagger;


import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-25 19:14
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("谁书Blog系统API")
                        .version("1.0")
                        .description("个人Blog")
                        .termsOfService("http://127.0.0.1/blog")
                        .license(new License().name("Apache 2.0")
                                .url("http://127.0.0.1/blog")));
    }


}
