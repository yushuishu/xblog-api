package com.shuishu.blog.common.domain.monitor.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:11
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.monitor.entity.po.ApiLogRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:11
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：系统接口访问日志
 * <p></p>
 */
@Mapper
public interface ApiLogRecordMapper extends BaseMapper<ApiLogRecord> {
}
