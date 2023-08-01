package com.shuishu.blog.business.industry.service.impl;


import com.shuishu.blog.business.industry.service.IndustryService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.industry.dsl.IndustryDsl;
import com.shuishu.blog.common.domain.industry.entity.dto.IndustryDTO;
import com.shuishu.blog.common.domain.industry.entity.vo.IndustryVO;
import com.shuishu.blog.common.domain.industry.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final IndustryDsl industryDsl;


    @Override
    public void addIndustry(IndustryDTO industryDTO) {

    }

    @Override
    public Page<IndustryVO> findIndustryPage(IndustryDTO industryDTO, PageDTO pageDTO) {
        return null;
    }

    @Override
    public IndustryVO findIndustryDetails(Long industryId) {
        return null;
    }

    @Override
    public void updateIndustry(IndustryDTO industryDTO) {

    }

}
