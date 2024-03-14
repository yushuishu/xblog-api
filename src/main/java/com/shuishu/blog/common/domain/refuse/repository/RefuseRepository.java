package com.shuishu.blog.common.domain.refuse.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.refuse.entity.po.Refuse;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-08-23 13:03
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：垃圾桶repository
 * <p></p>
 */
@Repository
public interface RefuseRepository extends BaseRepository<Refuse, Long> {
}
