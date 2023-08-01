package com.shuishu.blog.business.industry.service;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.industry.entity.dto.IndustryDTO;
import com.shuishu.blog.common.domain.industry.entity.vo.IndustryVO;
import org.springframework.data.domain.Page;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业
 * <p></p>
 */
public interface IndustryService {
    /**
     * 添加行业
     *
     * @param industryDTO 行业信息
     */
    void addIndustry(IndustryDTO industryDTO);

    /**
     * 分页查询
     *
     * @param industryDTO 查询条件
     * @param pageDTO 分页信息
     * @return 分页数据
     */
    Page<IndustryVO> findIndustryPage(IndustryDTO industryDTO, PageDTO pageDTO);

    /**
     * 详情
     *
     * @param industryId 行业id
     * @return 行业数据
     */
    IndustryVO findIndustryDetails(Long industryId);

    /**
     * 修改行业数据
     *
     * @param industryDTO 行业数据
     */
    void updateIndustry(IndustryDTO industryDTO);
}
