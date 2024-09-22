package com.shuishu.blog.common.domain.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.user.entity.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:15
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户角色关联dto
 * <p></p>
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
