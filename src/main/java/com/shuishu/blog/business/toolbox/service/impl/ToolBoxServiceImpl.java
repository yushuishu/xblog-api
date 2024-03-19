package com.shuishu.blog.business.toolbox.service.impl;


import com.shuishu.blog.business.toolbox.service.ToolBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuZhenFeng
 * @date 2024/3/14 10:31
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ToolBoxServiceImpl implements ToolBoxService {
}
