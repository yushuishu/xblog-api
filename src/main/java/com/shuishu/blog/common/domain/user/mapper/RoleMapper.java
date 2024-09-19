package com.shuishu.blog.common.domain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.user.entity.dto.RoleQueryDto;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:13
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：角色dto
 * <p></p>
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    RoleVo findRoleDetails(@Param("roleId") Long roleId);

    List<RoleVo> findRolePage(@Param("roleQueryDto") RoleQueryDto roleQueryDto, @Param("offset") long offset, @Param("pageSize") long pageSize);

    long findRolePageTotal(@Param("roleQueryDto") RoleQueryDto roleQueryDto, @Param("offset") long offset, @Param("pageSize") long pageSize);

    RoleVo findDefaultRole();

}
