package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.UserService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.domain.user.entity.dto.UserAddDto;
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

    @Operation(summary = "更新", description = "更新用户")
    @PostMapping("update")
    public ApiResponse<String> updateUser(UserAddDto userAddDTO) {
        //userService.addUser(userAddDTO);
        return ApiResponse.success();
    }

    @Operation(summary = "详情", description = "查询用户详情")
    @GetMapping("details")
    public ApiResponse<String> findUserDetails(UserAddDto userAddDTO) {
        //userService.addUser(userAddDTO);
        return ApiResponse.success();
    }

    @Operation(summary = "page", description = "查询用户page")
    @GetMapping("page")
    public ApiResponse<String> findUserPage(UserAddDto userAddDTO) {
        //userService.addUser(userAddDTO);
        return ApiResponse.success();
    }



}
