package com.shuishu.blog.business.user.service.impl;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import com.shuishu.blog.common.domain.user.dsl.PermissionDsl;
import com.shuishu.blog.common.domain.user.dsl.RoleDsl;
import com.shuishu.blog.common.domain.user.dsl.UserAuthDsl;
import com.shuishu.blog.common.domain.user.dsl.UserDsl;
import com.shuishu.blog.common.domain.user.entity.dto.UserAddDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdateDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdatePwdDto;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.po.User;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import com.shuishu.blog.common.domain.user.entity.po.UserRole;
import com.shuishu.blog.common.domain.user.entity.vo.*;
import com.shuishu.blog.common.domain.user.repository.RoleRepository;
import com.shuishu.blog.common.domain.user.repository.UserAuthRepository;
import com.shuishu.blog.common.domain.user.repository.UserRepository;
import com.shuishu.blog.business.user.service.UserService;
import com.shuishu.blog.common.domain.user.repository.UserRoleRepository;
import com.shuishu.blog.common.enums.RedisKeyEnum;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.CodingUtils;
import com.shuishu.blog.common.utils.GenerateMosaicHeadImgUtils;
import com.shuishu.blog.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ：谁书-ss
 * @date ：2022-12-29 22:40
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDsl userDsl;
    private final UserAuthRepository userAuthRepository;
    private final UserAuthDsl userAuthDsl;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final RoleDsl roleDsl;
    private final PermissionDsl permissionDsl;
    private final IndustryRepository industryRepository;
    private final PasswordEncoder passwordEncoder;

    private final RedisUtils redisUtils;

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

    @Override
    public void addUser(UserAddDto userAddDTO) {
        verifyUserInfo(userAddDTO.getNickname());
        // 行业
        if (userAddDTO.getIndustryId() != null) {
            Industry industry = industryRepository.findIndustryByIndustryId(userAddDTO.getIndustryId());
            if (industry == null) {
                throw new BusinessException("所选行业不正确");
            }
        }

        /*
         * 注册账号只能通过 邮箱、手机号注册
         */
        if (UserEnum.AuthType.EMAIL.getType().equals(userAddDTO.getUserAuthType())) {
            if (!StringUtils.hasText(userAddDTO.getUserAuthCredential())) {
                throw new BusinessException("邮箱验证码错误");
            }
            Object emailCodeObject = redisUtils.strGet(RedisKeyEnum.KEY_EMAIL_CODE.getKey() + userAddDTO.getUserAuthIdentifier());
            String emailCode = emailCodeObject == null ? "" : emailCodeObject.toString();
            if (!emailCode.equals(userAddDTO.getUserAuthCredential())) {
                throw new BusinessException("邮箱验证码错误");
            }
            // 验证邮箱是否已经注册
            UserAuth tempUserAuth = userAuthRepository.findByUserAuthIdentifierAndAndUserAuthType(userAddDTO.getUserAuthIdentifier(), UserEnum.AuthType.EMAIL.getType());
            if (tempUserAuth != null) {
                throw new BusinessException("该邮箱号已注册");
            }

            // 头像
            String photoBase64 = CodingUtils.convertByteToBase64(GenerateMosaicHeadImgUtils.getGenerateMosaicHeadImg());

            // 用户信息
            User user = new User();
            user.setNickname(userAddDTO.getNickname());
            user.setUserAbout("这里还没有介绍哦~");
            user.setUserPhoto(photoBase64);
            user.setUserAddress(userAddDTO.getUserAddress());
            user.setIndustryId(userAddDTO.getIndustryId());
            user.setUserIsAccountNonLocked(true);
            user.setUserIsAccountNonLocked(true);
            User saveUser = userRepository.save(user);
            // 账号
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(saveUser.getUserId());
            userAuth.setUserAuthType(UserEnum.AuthType.EMAIL.getType());
            userAuth.setUserAuthIdentifier(userAddDTO.getUserAuthIdentifier());
            userAuth.setUserAuthNickname(userAddDTO.getNickname());
            userAuth.setUserAuthPhoto(photoBase64);
            userAuthRepository.saveAndFlush(userAuth);
            // 角色（获取默认角色）
            Role defaultRole = roleRepository.findRoleByDefaultRole();
            if (defaultRole == null) {
                log.info("系统默认角色为空");
                throw new BusinessException("注册失败，请联系管理员");
            }
            UserRole userRole = new UserRole();
            userRole.setUserId(saveUser.getUserId());
            userRole.setRoleId(defaultRole.getRoleId());
            userRoleRepository.save(userRole);
        }else if (UserEnum.AuthType.PHONE.getType().equals(userAddDTO.getUserAuthType())) {
            throw new BusinessException("手机号注册功能，正在开发中");
        }
    }

    @Override
    public void updateUser(UserUpdateDto userUpdateDto) {
        verifyUserInfo(userUpdateDto.getNickname());
        User user = userRepository.findByUserId(userUpdateDto.getUserId());
        Objects.requireNonNull(user, "用户不存在");
        // 行业
        if (userUpdateDto.getIndustryId() != null) {
            Industry industry = industryRepository.findIndustryByIndustryId(userUpdateDto.getIndustryId());
            if (industry == null) {
                throw new BusinessException("所选行业不正确");
            }
        }
        String userPhotoBase64 = null;
        String userPhoto = userUpdateDto.getUserPhoto();
        if (StringUtils.hasText(userPhoto)) {
            File fileUserPhoto = new File(userPhoto);
            if (fileUserPhoto.exists()) {
                userPhotoBase64 = CodingUtils.convertFileToBase64(fileUserPhoto);
            }
        }
        userPhotoBase64 = userPhotoBase64 == null ? user.getUserPhoto() : userPhotoBase64;

        // 更新用户信息
        user.setNickname(userUpdateDto.getNickname());
        user.setUserAbout(userUpdateDto.getUserAbout());
        user.setUserPhoto(userPhotoBase64);
        user.setUserAddress(userUpdateDto.getUserAddress());
        user.setIndustryId(userUpdateDto.getIndustryId());
        user.setUpdateDate(new Date());
        user.setUpdateUserId(SpringSecurityUtils.getUserInfoVo().getUserId());
        userRepository.saveAndFlush(user);

    }

    @Override
    public void updateUserPassword(UserUpdatePwdDto userUpdatePwdDto) {
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        if (userUpdatePwdDto.getPassword().equals(userUpdatePwdDto.getAckPassword())) {
            throw new BusinessException("确认密码不正确");
        }
        // 查找用户绑定的邮箱
        UserAuth userAuth = userAuthRepository.findByUserAuthIdentifierAndAndUserAuthType(userUpdatePwdDto.getUserEmail(), UserEnum.AuthType.EMAIL.getType());
        Objects.requireNonNull(userAuth, "邮箱不存在");
        if (!userAuth.getUserId().equals(userInfoVo.getUserId())) {
            throw new BusinessException("当前登录用户和修改的用户账号不一致");
        }
        User user = userRepository.findByUserId(userAuth.getUserId());
        Objects.requireNonNull(user, "用户信息不存在");
        // 邮箱验证码
        Object codeObj = redisUtils.strGet(RedisKeyEnum.KEY_EMAIL_CODE.getKey() + userUpdatePwdDto.getUserEmail());
        String emailCode = codeObj == null ? null : codeObj.toString();
        if (!userUpdatePwdDto.getCaptcha().equals(emailCode)) {
            throw new BusinessException("证码不正确");
        }
        redisUtils.del(RedisKeyEnum.KEY_EMAIL_CODE.getKey() + userUpdatePwdDto.getUserEmail());

        UserAuth userAuthForLocal = userAuthRepository.findByUserIdAndUserAuthType(userAuth.getUserId(), UserEnum.AuthType.LOCAL.getType());
        userAuthForLocal.setUserAuthCredential(passwordEncoder.encode(userUpdatePwdDto.getPassword()));
        userAuthForLocal.setUpdateDate(new Date());
        userAuthForLocal.setUpdateUserId(userAuthForLocal.getUserId());
        userAuthRepository.saveAndFlush(userAuthForLocal);

    }

    @Override
    public UserDetailsVo findUserDetails(Long userId) {
        Objects.requireNonNull(userId, "用户id不能为空");
        User user = userRepository.findByUserId(userId);
        Objects.requireNonNull(user, "用户不存在");
        List<UserAuth> userAuthList = userAuthRepository.findByUserId(userId);
        if (ObjectUtils.isEmpty(userAuthList)) {
            throw new BusinessException("用户授权信息不存在");
        }

        UserDetailsVo userDetailsVo = new UserDetailsVo();
        BeanUtils.copyProperties(user, userDetailsVo);

        List<UserAuthDetailsVo> userAuthDetailsVoList = new ArrayList<>();
        for (UserAuth userAuth : userAuthList) {
            UserAuthDetailsVo userAuthDetailsVo = new UserAuthDetailsVo();
            userAuthDetailsVo.setUserAuthId(userAuth.getUserAuthId());
            userAuthDetailsVo.setUserAuthType(userAuth.getUserAuthType());
            userAuthDetailsVo.setUserAuthNickname(userAuth.getUserAuthNickname());
            userAuthDetailsVo.setUserAuthIdentifier(userAuth.getUserAuthIdentifier());
            userAuthDetailsVoList.add(userAuthDetailsVo);
        }
        userDetailsVo.setUserAuthDetailsVoList(userAuthDetailsVoList);

        return userDetailsVo;
    }

    @Override
    public PageVO<UserDetailsVo> findUserPage(UserQueryDto userQueryDto, PageDTO pageDTO) {
        return userDsl.findUserPage(userQueryDto, pageDTO);
    }

    private void verifyUserInfo(String nickname) {
        if (!StringUtils.hasText(nickname) || nickname.trim().length() < 2 || nickname.trim().length() > 20) {
            throw new BusinessException("昵称字符长度要求：大于2个字符，小于20个字符");
        }
    }

}
