package com.shuishu.blog.business.user.service.impl;


import com.shuishu.blog.business.user.service.ResourceService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.user.dsl.PermissionDsl;
import com.shuishu.blog.common.domain.user.dsl.RoleDsl;
import com.shuishu.blog.common.domain.user.entity.dto.*;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.po.RolePermission;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.domain.user.repository.PermissionRepository;
import com.shuishu.blog.common.domain.user.repository.RolePermissionRepository;
import com.shuishu.blog.common.domain.user.repository.RoleRepository;
import com.shuishu.blog.common.domain.user.repository.UserRoleRepository;
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
 * @author ：谁书-ss
 * @date ：2023-03-10 22:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：资源管理：角色、权限
 * <p></p>
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final RoleRepository roleRepository;
    private final RoleDsl roleDsl;
    private final PermissionRepository permissionRepository;
    private final PermissionDsl permissionDsl;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRoleRepository userRoleRepository;

    private final RedisUtils redisUtils;


    @Override
    public List<PermissionCacheDto> findCachePermissionList() {
        Object object = redisUtils.strGet(RedisKeyEnum.KEY_PERMISSION_URL_LIST.getKey());
        if (object == null) {
            List<PermissionCacheDto> permissionCacheDtoList = permissionDsl.findCachePermissionList();
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
        Permission tempPermission = permissionDsl.findByNameOrCodeOrUrl(permissionAddDto.getPermissionName(), permissionAddDto.getPermissionCode(), permissionAddDto.getPermissionUrl());
        if (tempPermission != null) {
            throw new BusinessException("权限名、权限code或url已存在");
        }
        Permission newPermission = permissionAddDto.toPo(Permission.class);
        newPermission.setCreateUserId(userInfoVo.getUserId());
        newPermission.setUpdateUserId(userInfoVo.getUserId());
        permissionRepository.save(newPermission);
    }

    @Override
    public void updatePermission(PermissionUpdateDto permissionUpdateDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        Permission tempPermission = permissionDsl.findByNameOrCodeOrUrlAndNeId(permissionUpdateDto.getPermissionName(), permissionUpdateDto.getPermissionCode(), permissionUpdateDto.getPermissionUrl(), permissionUpdateDto.getPermissionId());
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
        permissionRepository.saveAndFlush(updatePermission);
    }

    @Override
    public PermissionVo findPermissionDetails(Long permissionId) {
        return permissionDsl.findPermissionDetails(permissionId);
    }

    @Override
    public PageVO<PermissionVo> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO) {
        return permissionDsl.findPermissionPage(permissionQueryDto, pageDTO);
    }

    @Override
    public void deletePermission(Long permissionId) {
        // 查询此权限关联了的角色
        List<RolePermission> rolePermissionList = rolePermissionRepository.findAllByPermissionId(permissionId);
        if (!ObjectUtils.isEmpty(rolePermissionList)) {
            throw new BusinessException("当前权限有角色关联，请先解除角色关联");
        }
        permissionRepository.deleteById(permissionId);
    }

    @Override
    public void addRole(RoleAddDto roleAddDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        Role tempRole = roleDsl.findNameOrCode(roleAddDto.getRoleName(), roleAddDto.getRoleCode());
        if (tempRole != null) {
            throw new BusinessException("角色名或code已存在");
        }
        Role newRole = roleAddDto.toPo(Role.class);
        newRole.setCreateUserId(userInfoVo.getUserId());
        newRole.setUpdateUserId(userInfoVo.getUserId());
        roleRepository.saveAndFlush(newRole);
    }

    @Override
    public void updateRole(RoleUpdateDto roleUpdateDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        Role tempRole = roleDsl.findNameOrCodeAndNeId(roleUpdateDto.getRoleName(), roleUpdateDto.getRoleCode(), roleUpdateDto.getRoleId());
        if (tempRole != null) {
            if (tempRole.getRoleName().equals(roleUpdateDto.getRoleName())) {
                throw new BusinessException("角色名称已存在");
            }
            if (tempRole.getRoleCode().equals(roleUpdateDto.getRoleCode())) {
                throw new BusinessException("角色code已存在");
            }
        }
        Role updateRole = roleUpdateDto.toPo(Role.class);
        updateRole.setUpdateUserId(userInfoVo.getUserId());
        updateRole.setUpdateDate(new Date());
        roleRepository.saveAndFlush(updateRole);
    }

    @Override
    public RoleVo findRoleDetails(Long roleId) {
        return roleDsl.findRoleDetails(roleId);
    }

    @Override
    public PageVO<RoleVo> findRolePage(RoleQueryDto roleQueryDto, PageDTO pageDTO) {
        return roleDsl.findRolePage(roleQueryDto, pageDTO);
    }

    @Override
    public void deleteRole(Long roleId, Boolean ackDelete) {
        long relUserCount = userRoleRepository.countByRoleId(roleId);
        if (Boolean.TRUE.equals(ackDelete)) {
            userRoleRepository.deleteByRoleId(roleId);
        }else {
            if (relUserCount > 0) {
                throw new BusinessException("当前角色已被【"+ relUserCount +"】位用户关联使用，请再次确认是否删除此角色，并解除用户所属角色");
            }
        }
        rolePermissionRepository.deleteByRoleId(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public RoleVo findDefaultRole() {
        return roleDsl.findDefaultRole();
    }

    @Override
    public void updateDefaultRole(Long roleId) {
        Long defaultRoleId = roleDsl.findDefaultRoleId();
        if (defaultRoleId != null) {
            roleDsl.updateDefaultRole(defaultRoleId, false);
        }
        roleDsl.updateDefaultRole(roleId, true);
    }

}
