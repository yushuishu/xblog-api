package com.shuishu.blog.business.person.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-15 11:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：个人中心
 * <p></p>
 */
@Tag(name = "个人中心")
@RequiredArgsConstructor
@RestController
@RequestMapping("person")
public class PersonController {
}
