package com.shuishu.blog.common.config.base;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-24 18:29
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：持久化对象(Persistent Object)
 */
@Setter
@Getter
@ToString
@Schema(description = "持久化对象BasePO")
public class BasePO implements Serializable {

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Comment("创建时间")
    @Schema(description = "创建时间", hidden = true)
    private Date createDate;

    @TableField("create_user_id")
    @Comment("创建人id")
    @Schema(description = "创建人id", hidden = true)
    private Long createUserId;

    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Comment("更新时间")
    @Schema(description = "更新时间", hidden = true)
    private Date updateDate;

    @TableField("update_user_id")
    @Schema(description = "修改人id", hidden = true)
    @Comment("修改人id")
    private Long updateUserId;


    public <T extends BaseVO<?>> T toVo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
