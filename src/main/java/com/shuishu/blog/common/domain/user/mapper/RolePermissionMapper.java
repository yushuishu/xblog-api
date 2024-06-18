package com.shuishu.blog.common.domain.user.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:14
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.user.entity.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:14
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：角色权限关联dto
 * <p></p>
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
