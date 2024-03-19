package com.shuishu.blog.common.utils.lock;


import java.lang.annotation.*;

/**
 * @author wuZhenFeng
 * @date 2024/3/15 8:35
 */
/**
 * @Author ：谁书-ss
 * @Date ：2023-04-20 23:25
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：service并发，上锁注解
 * <p></p>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLock {
    String description()  default "";
}
