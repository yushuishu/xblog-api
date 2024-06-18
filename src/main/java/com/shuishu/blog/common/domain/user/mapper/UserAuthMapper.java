package com.shuishu.blog.common.domain.user.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:15
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:15
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户授权dto
 * <p></p>
 */
@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth> {
}
