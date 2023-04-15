package com.shuishu.blog.business.base;


import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.constant.UserConstant;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import com.shuishu.blog.common.domain.user.dsl.RolePermissionDsl;
import com.shuishu.blog.common.domain.user.entity.po.*;
import com.shuishu.blog.common.domain.user.repository.*;
import com.shuishu.blog.common.enums.UserEnum;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ：谁书-ss
 * @date ：2023-03-30 8:26
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：初始化操作
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class InitService {
    @Value("${shuishu.init.user.nickname}")
    private String nickname;
    @Value("${shuishu.init.user.user-auth-identifier}")
    private String userAuthIdentifier;
    @Value("${shuishu.init.user.user-auth-credential}")
    private String userAuthCredential;
    @Value("${shuishu.init.user.industry}")
    private String industryName;
    @Value("${shuishu.init.user.user-photo}")
    private String userPhoto;

    private final UserAuthRepository userAuthRepository;
    private final UserRepository userRepository;
    private final IndustryRepository industryRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionDsl rolePermissionDsl;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostConstruct
    public void initUserAdmin() {
        if (StringUtils.hasText(nickname) &&
                StringUtils.hasText(userAuthIdentifier) &&
                StringUtils.hasText(userAuthCredential) &&
                StringUtils.hasText(industryName)) {

            UserAuth userAuth = userAuthRepository.findByUserAuthIdentifierAndAndUserAuthType(userAuthIdentifier, UserEnum.AuthType.LOCAL.getType());
            if (userAuth == null) {
                // 职业
                Industry industry = industryRepository.findByIndustryName(industryName);
                if (industry == null) {
                    industry = new Industry();
                    industry.setIndustryName(industryName);
                    industry = industryRepository.saveAndFlush(industry);
                }

                // 用户
                User user = userRepository.findByNickname(nickname);
                if (user == null) {
                    user = new User();
                    user.setNickname(nickname);
                    user.setUserAbout(UserConstant.USER_ABOUT);
                    user.setUserPhoto(StringUtils.hasText(userPhoto) ? userPhoto : "");
                    user.setIndustryId(industry.getIndustryId());
                    user.setUserIsAccountNonExpired(true);
                    user.setUserIsAccountNonLocked(true);
                    user.setUserMaxLoginClientNumber(UserConstant.USER_MAX_LOGIN_CLIENT_NUMBER);
                    userRepository.saveAndFlush(user);
                }

                // 授权信息
                userAuth = new UserAuth();
                userAuth.setUserId(user.getUserId());
                userAuth.setUserAuthIdentifier(userAuthIdentifier);
                userAuth.setUserAuthCredential(bCryptPasswordEncoder.encode(userAuthCredential));
                userAuth.setUserAuthType(UserEnum.AuthType.LOCAL.getType());
                userAuthRepository.saveAndFlush(userAuth);

                // 角色
                Role role = roleRepository.findRoleByRoleCode(SpringSecurityUtils.DEFAULT_ROLE_CODE);
                if (role == null) {
                    role = new Role();
                    role.setRoleName("系统超级管理员");
                    role.setRoleCode(SpringSecurityUtils.DEFAULT_ROLE_CODE);
                    role.setRoleOperatePower(false);
                    role.setCreateUserId(user.getUserId());
                    role.setUpdateUserId(user.getUserId());
                    roleRepository.saveAndFlush(role);
                }

                // 权限
                List<Permission> permissionList = initPermission(user.getUserId());
                List<Long> permissionIdList = permissionList.stream().map(Permission::getPermissionId).collect(Collectors.toList());
                // 角色权限关联信息
                List<Long> existPermissionList = rolePermissionDsl.findPermissionIdsByRoleIdAndPermissionIds(role.getRoleId(), permissionIdList);
                if (existPermissionList.size() < permissionIdList.size()) {
                    List<RolePermission> newRolePermissionList = new ArrayList<>();
                    List<Long> notExistPermissionList = permissionIdList.stream().filter(t -> !existPermissionList.contains(t)).toList();
                    for (Long permissionId : notExistPermissionList) {
                        RolePermission rolePermission = new RolePermission();
                        rolePermission.setRoleId(role.getRoleId());
                        rolePermission.setPermissionId(permissionId);
                        rolePermission.setCreateUserId(user.getUserId());
                        rolePermission.setUpdateUserId(user.getUserId());
                        newRolePermissionList.add(rolePermission);
                    }
                    rolePermissionRepository.saveAll(newRolePermissionList);
                }

                // 用户角色
                UserRole userRole = userRoleRepository.findUserRoleByUserIdAndRoleId(user.getUserId(), role.getRoleId());
                if (userRole == null) {
                    userRole = new UserRole();
                    userRole.setUserId(user.getUserId());
                    userRole.setRoleId(role.getRoleId());
                    userRoleRepository.save(userRole);
                }

            }
        }

    }


    private List<Permission> initPermission(Long userId) {
        Map<String, String> initPermissionMap = SpringSecurityUtils.getInitPermission();
        Set<Map.Entry<String, String>> entries = initPermissionMap.entrySet();
        Set<String> initPermissionCodeSet = initPermissionMap.keySet();

        List<Permission> permissionList = permissionRepository.findAllByPermissionCodeIn(initPermissionCodeSet.stream().toList());
        List<String> existPermissionCodeList = permissionList.stream().map(Permission::getPermissionCode).toList();
        List<String> notExistPermissionCodeList = initPermissionCodeSet.stream().filter(t -> !existPermissionCodeList.contains(t)).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(notExistPermissionCodeList)) {
            List<Permission> incrementPermissionList = new ArrayList<>();
            for (Map.Entry<String, String> entry : entries) {
                if (notExistPermissionCodeList.contains(entry.getKey())) {
                    Permission permission = new Permission();
                    permission.setPermissionCode(entry.getKey());
                    permission.setPermissionUrl(entry.getValue());
                    permission.setPermissionDescription("");
                    permission.setIsNeedAuthorization(true);
                    permission.setPermissionOperatePower(false);
                    permission.setCreateUserId(userId);
                    permission.setUpdateUserId(userId);
                    incrementPermissionList.add(permission);
                }
            }
            List<Permission> addPermissionSuccessList = permissionRepository.saveAll(incrementPermissionList);
            permissionList.addAll(addPermissionSuccessList);
        }
        return permissionList;
    }

}
