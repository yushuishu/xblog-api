package com.shuishu.blog.common.domain.label.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:42
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@Repository
public interface LabelRepository extends BaseRepository<Label, Long> {

}
