package com.shuishu.blog.common.config.mybatis;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;

/**
 * @Author ：谁书-ss
 * @Date ：2024-09-22 19:44
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 */
public class BaseMapperServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseMapperService<M, T> {

    @Resource
    protected M baseMapper;

    @Override
    public M getMapper() {
        return baseMapper;
    }

}
