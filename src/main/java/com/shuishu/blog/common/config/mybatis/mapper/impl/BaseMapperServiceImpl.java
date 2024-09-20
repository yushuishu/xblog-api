package com.shuishu.blog.common.config.mybatis.mapper.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuishu.blog.common.config.mybatis.mapper.BaseMapperService;
import com.shuishu.blog.common.config.mybatis.mapper.RootMapper;

import java.util.List;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/20 17:44
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public abstract class BaseMapperServiceImpl<M extends RootMapper<T>, T> extends ServiceImpl<M, T> implements BaseMapperService<T> {

    @Override
    public int insertBatch(List<T> entityList) {
        return this.getBaseMapper().insertBatch(entityList);
    }

    @Override
    public int updateBatch(List<T> entityList) {
        return this.getBaseMapper().updateBatch(entityList);
    }

}
