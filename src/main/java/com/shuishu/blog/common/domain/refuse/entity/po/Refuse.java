package com.shuishu.blog.common.domain.refuse.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-08-23 12:52
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：垃圾桶
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_refuse")
@Comment(value = "垃圾桶")
public class Refuse extends BasePO {
    @TableId(value = "refuse_id", type = IdType.ASSIGN_ID)
    @Comment(value = "垃圾桶id")
    private Long refuseId;

    @TableField("article_id")
    @Comment("文章ID")
    private Long articleId;

    @TableField("expire_date")
    @Comment("到期删除时间")
    private Date expireDate;

}
