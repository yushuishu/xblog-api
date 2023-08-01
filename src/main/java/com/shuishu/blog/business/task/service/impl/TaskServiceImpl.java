package com.shuishu.blog.business.task.service.impl;


import com.shuishu.blog.business.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:46
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：定时任务
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TaskServiceImpl implements TaskService {

}
