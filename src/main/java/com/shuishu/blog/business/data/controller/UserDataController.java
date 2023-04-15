package com.shuishu.blog.business.data.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-15 11:14
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：用户数据
 * <p></p>
 */
@Tag(name = "用户数据")
@RequiredArgsConstructor
@RestController
@RequestMapping("data/user")
public class UserDataController {
}
