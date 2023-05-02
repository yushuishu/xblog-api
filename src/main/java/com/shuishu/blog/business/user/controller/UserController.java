package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.UserService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.user.entity.dto.UserAddDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdateDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdatePwdDto;
import com.shuishu.blog.common.domain.user.entity.vo.UserDetailsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：谁书-ss
 * @date ：2023-04-06 20:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：用户管理
 * <p></p>
 */
@Tag(name = "用户管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    @Operation(summary = "添加", description = "添加用户")
    @PostMapping("add")
    public ApiResponse<String> addUser(@Validated @RequestParam("userAddDTO") UserAddDto userAddDTO) {
        userService.addUser(userAddDTO);
        return ApiResponse.success();
    }

    @Operation(summary = "更新用户信息", description = "更新用户信息")
    @PostMapping("update")
    public ApiResponse<String> updateUser(UserUpdateDto userUpdateDto) {
        userService.updateUser(userUpdateDto);
        return ApiResponse.success();
    }

    @Operation(summary = "更新密码", description = "更新密码")
    @PostMapping("pwd/update")
    public ApiResponse<String> updateUserPassword(UserUpdatePwdDto userUpdatePwdDto) {
        userService.updateUserPassword(userUpdatePwdDto);
        return ApiResponse.success();
    }

    @Operation(summary = "详情", description = "查询用户详情")
    @GetMapping("details")
    public ApiResponse<UserDetailsVo> findUserDetails(Long userId) {
        return ApiResponse.of(userService.findUserDetails(userId));
    }

    @Operation(summary = "用户page", description = "查询用户page")
    @GetMapping("page")
    public ApiResponse<PageVO<UserDetailsVo>> findUserPage(UserQueryDto userQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(userService.findUserPage(userQueryDto, pageDTO));
    }



}
