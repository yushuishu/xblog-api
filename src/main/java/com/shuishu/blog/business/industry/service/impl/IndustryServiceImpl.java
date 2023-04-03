package com.shuishu.blog.business.industry.service.impl;


import com.shuishu.blog.business.industry.service.IndustryService;
import com.shuishu.blog.common.domain.industry.dsl.IndustryDsl;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：行业
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final IndustryDsl industryDsl;


}
