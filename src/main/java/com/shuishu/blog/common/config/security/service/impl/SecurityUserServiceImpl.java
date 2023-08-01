package com.shuishu.blog.common.config.security.service.impl;


import com.shuishu.blog.common.config.security.service.SecurityUserService;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import com.shuishu.blog.common.domain.user.dsl.PermissionDsl;
import com.shuishu.blog.common.domain.user.dsl.RoleDsl;
import com.shuishu.blog.common.domain.user.dsl.UserAuthDsl;
import com.shuishu.blog.common.domain.user.dsl.UserDsl;
import com.shuishu.blog.common.domain.user.entity.po.User;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionInfoVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleInfoVo;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.domain.user.repository.RoleRepository;
import com.shuishu.blog.common.domain.user.repository.UserAuthRepository;
import com.shuishu.blog.common.domain.user.repository.UserRepository;
import com.shuishu.blog.common.domain.user.repository.UserRoleRepository;
import com.shuishu.blog.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 18:16
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：Security用户service
 * <p></p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final RoleDsl roleDsl;
    private final PermissionDsl permissionDsl;

    @Override
    public UserInfoVo findByUserAuthIdentifier(String userAuthIdentifier, String authType) {
        UserAuth userAuth = userAuthRepository.findByUserAuthIdentifierAndAndUserAuthType(userAuthIdentifier, authType);
        if (userAuth == null) {
            return null;
        }
        User user = userRepository.findByUserId(userAuth.getUserId());
        if (user == null) {
            return null;
        }

        UserInfoVo userInfoVO = new UserInfoVo();
        BeanUtils.copyProperties(userAuth, userInfoVO);
        BeanUtils.copyProperties(user, userInfoVO);

        // 角色
        List<RoleInfoVo> roleInfoList = roleDsl.findRoleInfoByUserId(userInfoVO.getUserId());
        if (!ObjectUtils.isEmpty(roleInfoList)) {
            userInfoVO.setRoleInfoList(roleInfoList);
            // 权限
            List<PermissionInfoVo> permissionInfoList = permissionDsl.findPermissionInfoByRoleIdList(roleInfoList.stream().map(RoleInfoVo::getRoleId).collect(Collectors.toList()));
            if (!ObjectUtils.isEmpty(permissionInfoList)) {
                userInfoVO.setPermissionInfoList(permissionInfoList);
            }
        }

        return userInfoVO;
    }

}
