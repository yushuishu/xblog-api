package com.shuishu.blog.business.monitor.controller;


import com.shuishu.blog.business.monitor.service.MonitorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-06 20:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：监控
 * <p></p>
 */
@Tag(name = "监控")
@RequiredArgsConstructor
@RestController
@RequestMapping("monitor")
public class MonitorController {
    private final MonitorService monitorService;

}
