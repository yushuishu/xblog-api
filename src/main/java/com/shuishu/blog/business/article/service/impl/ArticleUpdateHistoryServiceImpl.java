package com.shuishu.blog.business.article.service.impl;


/**
 * @author wuZhenFeng
 * @date 2024/4/15 11:51
 */


import com.shuishu.blog.business.article.service.ArticleUpdateHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/15 11:51
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章修改历史
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ArticleUpdateHistoryServiceImpl implements ArticleUpdateHistoryService {

}
