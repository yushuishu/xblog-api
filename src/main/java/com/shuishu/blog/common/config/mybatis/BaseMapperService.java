package com.shuishu.blog.common.config.mybatis;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author ：谁书-ss
 * @Date ：2024-09-22 19:43
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 */
public interface BaseMapperService<M, T> extends IService<T> {
    M getMapper();
}
