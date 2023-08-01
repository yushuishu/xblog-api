package com.shuishu.blog.business.user.service.impl;


import com.shuishu.blog.business.user.service.AuthService;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.token.EmailAuthenticationToken;
import com.shuishu.blog.common.config.security.token.LocalAuthenticationToken;
import com.shuishu.blog.common.config.security.token.PhoneAuthenticationToken;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.TokenUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：谁书-ss
 * @Date ：2023/3/7 14:41
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;


    @Override
    public UserInfoVo login(String name, String pwd, UserEnum.AuthType authType, Boolean isRememberMe, HttpServletResponse response) {
        Authentication authentication = null;
        if (UserEnum.AuthType.LOCAL.equals(authType)) {
            authentication = authenticationManager.authenticate(new LocalAuthenticationToken(name, pwd));
        } else if (UserEnum.AuthType.EMAIL.equals(authType)) {
            authentication = authenticationManager.authenticate(new EmailAuthenticationToken(name, pwd));
        } else if (UserEnum.AuthType.PHONE.equals(authType)) {
            authentication = authenticationManager.authenticate(new PhoneAuthenticationToken(name, pwd));
        } else {
            throw new BusinessException("不支持的登录方式");
        }
        UserInfoVo userInfoVO = (UserInfoVo) authentication.getPrincipal();

        // 生成token，或者在provider中生成
        if (userInfoVO != null && !tokenUtils.setUserInfoVo(userInfoVO, isRememberMe, response)) {
            throw new BusinessException(401, "登录失败");
        }
        return userInfoVO;
    }

}
