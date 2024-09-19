package com.shuishu.blog.business.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuishu.blog.business.user.service.ResourceService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.user.entity.dto.*;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.po.RolePermission;
import com.shuishu.blog.common.domain.user.entity.po.UserRole;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.domain.user.mapper.*;
import com.shuishu.blog.common.enums.RedisKeyEnum;
import com.shuishu.blog.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-10 22:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：资源管理：角色、权限
 * <p></p>
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    private final RedisUtils redisUtils;


    @Override
    public List<PermissionCacheDto> findCachePermissionList() {
        Object object = redisUtils.strGet(RedisKeyEnum.KEY_PERMISSION_URL_LIST.getKey());
        if (object == null) {
            List<PermissionCacheDto> permissionCacheDtoList = permissionMapper.findCachePermissionList();
            redisUtils.strSet(RedisKeyEnum.KEY_PERMISSION_URL_LIST.getKey(), permissionCacheDtoList);
            return permissionCacheDtoList;
        }
        List<PermissionCacheDto> result = new ArrayList<>();
        if (object instanceof ArrayList<?> permissionCacheDtoList) {
            for (Object obj : permissionCacheDtoList) {
                if (obj instanceof PermissionCacheDto permissionCacheDto) {
                    result.add(permissionCacheDto);
                }
            }
        }
        return result;
    }

    @Override
    public void addPermission(PermissionAddDto permissionAddDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(w -> w.eq(Permission::getPermissionName, permissionAddDto.getPermissionName())
                .eq(Permission::getPermissionCode, permissionAddDto.getPermissionCode())
                .eq(Permission::getPermissionUrl, permissionAddDto.getPermissionUrl()
                )
        );
        List<Permission> tempPermissionList = permissionMapper.selectList(queryWrapper);
        if (tempPermissionList != null && !tempPermissionList.isEmpty()) {
            throw new BusinessException("权限名、权限code或url已存在");
        }
        Permission newPermission = permissionAddDto.toPo(Permission.class);
        newPermission.setCreateUserId(userInfoVo.getUserId());
        newPermission.setUpdateUserId(userInfoVo.getUserId());
        permissionMapper.insert(newPermission);
    }

    @Override
    public void updatePermission(PermissionUpdateDto permissionUpdateDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        Permission tempPermission = permissionMapper.findByNameOrCodeOrUrlAndNeId(permissionUpdateDto.getPermissionName(), permissionUpdateDto.getPermissionCode(), permissionUpdateDto.getPermissionUrl(), permissionUpdateDto.getPermissionId());
        if (tempPermission != null) {
            if (tempPermission.getPermissionName().equals(permissionUpdateDto.getPermissionName())) {
                throw new BusinessException("权限名称已存在");
            }
            if (tempPermission.getPermissionCode().equals(permissionUpdateDto.getPermissionCode())) {
                throw new BusinessException("权限code已存在");
            }
            if (tempPermission.getPermissionUrl().equals(permissionUpdateDto.getPermissionUrl())) {
                throw new BusinessException("权限url已存在");
            }
        }
        Permission updatePermission = permissionUpdateDto.toPo(Permission.class);
        updatePermission.setUpdateUserId(userInfoVo.getUserId());
        updatePermission.setUpdateDate(new Date());
        permissionMapper.updateById(updatePermission);
    }

    @Override
    public PermissionVo findPermissionDetails(Long permissionId) {
        return permissionMapper.findPermissionDetails(permissionId);
    }

    @Override
    public PageVO<PermissionVo> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO) {
        PageVO<PermissionVo> pageVO = pageDTO.toPageVO(PermissionVo.class);
        List<PermissionVo> permissionVoList = permissionMapper.findPermissionPage(permissionQueryDto, pageVO.getOffset(), pageDTO.getPageSize());
        pageVO.setDataList(permissionVoList);
        long total = permissionMapper.findPermissionPageTotal(permissionQueryDto);
        pageVO.setTotalElements(total);
        return pageVO;
    }

    @Override
    public void deletePermission(Long permissionId) {
        // 查询此权限关联了的角色
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getPermissionId, permissionId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(queryWrapper);
        if (!ObjectUtils.isEmpty(rolePermissionList)) {
            throw new BusinessException("当前权限有角色关联，请先解除角色关联");
        }
        permissionMapper.deleteById(permissionId);
    }

    @Override
    public void addRole(RoleAddDto roleAddDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(w -> w.eq(Role::getRoleName, roleAddDto.getRoleName()).eq(Role::getRoleCode, roleAddDto.getRoleCode()));
        List<Role> roles = roleMapper.selectList(queryWrapper);
        if (roles != null && !roles.isEmpty()) {
            throw new BusinessException("角色名或code已存在");
        }
        Role newRole = roleAddDto.toPo(Role.class);
        newRole.setCreateUserId(userInfoVo.getUserId());
        newRole.setUpdateUserId(userInfoVo.getUserId());
        roleMapper.insert(newRole);
    }

    @Override
    public void updateRole(RoleUpdateDto roleUpdateDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(w -> w.eq(Role::getRoleName, roleUpdateDto.getRoleName()).eq(Role::getRoleCode, roleUpdateDto.getRoleCode()))
                .or(w -> w.ne(Role::getRoleId, roleUpdateDto.getRoleId()));
        List<Role> roles = roleMapper.selectList(queryWrapper);
        if (roles != null && !roles.isEmpty()) {
            for (Role tempRole : roles) {
                if (tempRole.getRoleName().equals(roleUpdateDto.getRoleName())) {
                    throw new BusinessException("角色名称已存在");
                }
                if (tempRole.getRoleCode().equals(roleUpdateDto.getRoleCode())) {
                    throw new BusinessException("角色code已存在");
                }
            }
        }

        Role role = roleMapper.selectById(roleUpdateDto.getRoleId());
        if (SpringSecurityUtils.DEFAULT_ROLE_SUPER_CODE.equals(role.getRoleCode()) ||
                SpringSecurityUtils.DEFAULT_ROLE_GENERAL_CODE.equals(role.getRoleCode())) {
            throw new BusinessException("系统默认角色不可修改");
        }
        role.setRoleName(roleUpdateDto.getRoleName());
        role.setRoleCode(roleUpdateDto.getRoleCode());
        role.setUpdateUserId(userInfoVo.getUserId());
        role.setUpdateDate(new Date());
        roleMapper.updateById(role);
    }

    @Override
    public RoleVo findRoleDetails(Long roleId) {
        if (roleId == null) {
            return null;
        }
        return roleMapper.findRoleDetails(roleId);
    }

    @Override
    public PageVO<RoleVo> findRolePage(RoleQueryDto roleQueryDto, PageDTO pageDTO) {
        PageVO<RoleVo> pageVO = pageDTO.toPageVO(RoleVo.class);
        List<RoleVo> roleVoList = roleMapper.findRolePage(roleQueryDto, pageVO.getOffset(), pageVO.getPageSize());
        pageVO.setDataList(roleVoList);
        long total = roleMapper.findRolePageTotal(roleQueryDto, pageVO.getOffset(), pageVO.getPageSize());
        pageVO.setTotalElements(total);
        return pageVO;
    }

    @Override
    public void deleteRole(Long roleId, Boolean ackDelete) {
        Role role = roleMapper.selectById(roleId);
        if (SpringSecurityUtils.DEFAULT_ROLE_SUPER_CODE.equals(role.getRoleCode()) ||
                SpringSecurityUtils.DEFAULT_ROLE_GENERAL_CODE.equals(role.getRoleCode())) {
            throw new BusinessException("系统默认角色不可删除");
        }

        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, roleId);
        if (Boolean.TRUE.equals(ackDelete)) {
            userRoleMapper.delete(queryWrapper);
        }else {
            long relUserCount = userRoleMapper.selectCount(queryWrapper);
            if (relUserCount > 0) {
                throw new BusinessException("当前角色已被【"+ relUserCount +"】位用户关联使用，请再次确认是否删除此角色，并解除用户所属角色");
            }
        }
        LambdaQueryWrapper<RolePermission> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(RolePermission::getRoleId, roleId);
        rolePermissionMapper.delete(queryWrapper2);
        roleMapper.deleteById(roleId);
    }

    @Override
    public RoleVo findDefaultRole() {
        return roleMapper.findDefaultRole();
    }

}
