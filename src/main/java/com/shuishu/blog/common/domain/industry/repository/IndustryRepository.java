package com.shuishu.blog.common.domain.industry.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-30 12:50
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业
 * <p></p>
 */
@Repository
public interface IndustryRepository extends BaseRepository<Industry, Long> {
    /**
     * 行业名称 查询行业信息
     *
     * @param industryName -行业名称
     * @return 行业
     */
    Industry findByIndustryName(String industryName);

    /**
     * 通过id 查询行业
     *
     * @param industryId 行业id
     * @return 行业
     */
    Industry findIndustryByIndustryId(Long industryId);
}
