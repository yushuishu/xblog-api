package com.shuishu.blog.business.base;


import com.shuishu.blog.common.constant.UserConstant;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import com.shuishu.blog.common.domain.user.entity.po.User;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import com.shuishu.blog.common.domain.user.repository.UserAuthRepository;
import com.shuishu.blog.common.domain.user.repository.UserRepository;
import com.shuishu.blog.common.enums.UserEnum;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

                // 创建授权信息
                userAuth = new UserAuth();
                userAuth.setUserId(user.getUserId());
                userAuth.setUserAuthIdentifier(userAuthIdentifier);
                userAuth.setUserAuthCredential(bCryptPasswordEncoder.encode(userAuthCredential));
                userAuth.setUserAuthType(UserEnum.AuthType.LOCAL.getType());
                userAuthRepository.saveAndFlush(userAuth);
            }
        }

    }

}
