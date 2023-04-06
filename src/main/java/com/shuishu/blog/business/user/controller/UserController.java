package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.UserService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.domain.user.entity.dto.UserAddDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-06 20:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：用户管理
 * <p></p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    @PostMapping("add")
    public ApiResponse<String> addUser(UserAddDTO userAddDTO) {
        //userService.addUser(userAddDTO);
        return ApiResponse.success();
    }



}
