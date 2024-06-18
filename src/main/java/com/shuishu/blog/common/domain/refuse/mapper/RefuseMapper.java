package com.shuishu.blog.common.domain.refuse.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:12
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.refuse.entity.po.Refuse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:12
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：垃圾桶dto
 * <p></p>
 */
@Mapper
public interface RefuseMapper extends BaseMapper<Refuse> {
}
