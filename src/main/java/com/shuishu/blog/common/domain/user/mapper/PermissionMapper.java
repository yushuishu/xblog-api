package com.shuishu.blog.common.domain.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionCacheDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionQueryDto;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:13
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：权限dto
 * <p></p>
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<PermissionCacheDto> findCachePermissionList();


    Permission findByNameOrCodeOrUrlAndNeId(@Param("permissionName") String permissionName, @Param("permissionCode") String permissionCode,
                                            @Param("permissionUrl") String permissionUrl, @Param("permissionId") Long permissionId);

    PermissionVo findPermissionDetails(@Param("permissionId") Long permissionId);

    List<PermissionVo> findPermissionPage(@Param("permissionQueryDto") PermissionQueryDto permissionQueryDto, @Param("offset") long offset, @Param("pageSize") long pageSize);

    long findPermissionPageTotal(@Param("permissionQueryDto") PermissionQueryDto permissionQueryDto);

}
