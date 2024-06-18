package com.shuishu.blog.common.domain.label.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:11
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:11
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签dto
 * <p></p>
 */
@Mapper
public interface LabelMapper extends BaseMapper<Label> {
}
