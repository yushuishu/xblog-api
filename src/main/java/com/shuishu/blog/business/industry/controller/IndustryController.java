package com.shuishu.blog.business.industry.controller;


import com.shuishu.blog.business.industry.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：行业
 * <p></p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("industry")
public class IndustryController {
    private final IndustryService industryService;



}
