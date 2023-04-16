package com.shuishu.blog.business.user.service.impl;


import com.shuishu.blog.business.user.service.ResourceService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.user.dsl.PermissionDsl;
import com.shuishu.blog.common.domain.user.dsl.RoleDsl;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionAddDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionCacheDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionUpdateDto;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.domain.user.repository.PermissionRepository;
import com.shuishu.blog.common.domain.user.repository.RoleRepository;
import com.shuishu.blog.common.enums.RedisKeyEnum;
import com.shuishu.blog.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Permission tempPermission = permissionDsl.findByCodeOrUrl(permissionAddDto.getPermissionCode(), permissionAddDto.getPermissionUrl());
        if (tempPermission != null) {
            throw new BusinessException("权限code或url已存在");
        }
        Permission newPermission = permissionAddDto.toPo(Permission.class);
        newPermission.setCreateUserId(userInfoVo.getUserId());
        newPermission.setUpdateUserId(userInfoVo.getUserId());
        permissionRepository.save(newPermission);
    }

    @Override
    public void updatePermission(PermissionUpdateDto permissionUpdateDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        Permission tempPermission = permissionDsl.findByCodeOrUrlAndNeId(permissionUpdateDto.getPermissionCode(), permissionUpdateDto.getPermissionUrl(), permissionUpdateDto.getPermissionId());
        if (tempPermission != null) {
            if (tempPermission.getPermissionCode().equals(permissionUpdateDto.getPermissionCode())) {
                throw new BusinessException("code已存在");
            }
            if (tempPermission.getPermissionUrl().equals(permissionUpdateDto.getPermissionUrl())) {
                throw new BusinessException("url已存在");
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

}
