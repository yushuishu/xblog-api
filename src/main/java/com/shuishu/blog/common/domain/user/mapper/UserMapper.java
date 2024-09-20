package com.shuishu.blog.common.domain.user.mapper;


import com.shuishu.blog.common.config.mybatis.mapper.RootMapper;
import com.shuishu.blog.common.domain.user.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:15
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户信息dto
 * <p></p>
 */
@Mapper
public interface UserMapper extends RootMapper<User> {
    User findByIdAndNamePage(Long id, String name);
}
