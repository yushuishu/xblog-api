package com.shuishu.blog.common.config.mybatis.mapper;


import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/20 17:42
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public interface BaseMapperService<T> extends IService<T> {

    /**
     * 批量添加
     *
     * @param entityList -
     * @return -
     * <br>
     * @author ：wuzhenfeng
     * @date ：2024/9/20 15:34
     * @since ：1.0.0
     */
    int insertBatch(@Param("entityList") List<T> entityList);

    /**
     * 批量更新
     *
     * @param entityList -
     * @return -
     * <br>
     * @author ：wuzhenfeng
     * @date ：2024/9/20 15:34
     * @since ：1.0.0
     */
    int updateBatch(@Param("entityList") List<T> entityList);

}
