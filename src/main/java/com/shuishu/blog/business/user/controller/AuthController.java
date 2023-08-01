package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.AuthService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.domain.user.entity.dto.UserLoginDto;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 15:44
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：认证/授权
 */
@Tag(name = "认证授权")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenUtils tokenUtils;


    @Operation(summary = "退出登录", description = "退出登录")
    @PostMapping("logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        System.out.println("退出登录----------- logout");
        tokenUtils.logout(request);
        return ApiResponse.success("退出登录", null);
    }

    @Operation(summary = "账号登录", description = "本地账号登录（账号密码）")
    @PostMapping("local")
    public ApiResponse<UserInfoVo> local(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- local");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.LOCAL, userLoginDTO.getIsRememberMe(), response));
    }

    @Operation(summary = "邮箱登录", description = "邮箱登录（邮箱验证码）")
    @PostMapping("email")
    public ApiResponse<UserInfoVo> email(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- email");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.EMAIL, userLoginDTO.getIsRememberMe(), response));
    }

    @Operation(summary = "手机号登录", description = "手机号登录（短信验证码）")
    @PostMapping("phone")
    public ApiResponse<UserInfoVo> phone(@RequestBody UserLoginDto userLoginDTO, HttpServletResponse response) {
        System.out.println("登录----------- phone");
        return ApiResponse.of(authService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword(), UserEnum.AuthType.PHONE, userLoginDTO.getIsRememberMe(), response));
    }


}
