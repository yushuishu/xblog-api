package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.AuthService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.domain.user.entity.dto.UserLoginDto;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ：谁书-ss
 * @date ：2023-01-01 15:44
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：认证/授权
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenUtils tokenUtils;

    @PostMapping("logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        System.out.println("退出登录----------- logout");
        tokenUtils.logout(request);
        return ApiResponse.success("退出登录", null);
    }

    @PostMapping("local")
    public ApiResponse<UserInfoVo> local(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- local");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.LOCAL, userLoginDTO.getIsRememberMe(), response));
    }

    @PostMapping("email")
    public ApiResponse<UserInfoVo> email(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- email");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.EMAIL, userLoginDTO.getIsRememberMe(), response));
    }

    @PostMapping("phone")
    public ApiResponse<UserInfoVo> phone(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- phone");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.PHONE, userLoginDTO.getIsRememberMe(), response));
    }


}
