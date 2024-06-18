package com.shuishu.blog.common.domain.industry.entity.po;


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

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-30 12:40
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_industry")
@Comment(value = "行业表")
public class Industry extends BasePO {

    @TableId(value = "industry_id", type = IdType.ASSIGN_ID)
    @Comment(value = "行业id")
    private Long industryId;

    @TableField("industry_name")
    @Comment("行业名称")
    @Column(nullable = false, unique = true)
    private String industryName;

    @TableField("industry_sort")
    @Comment(value = "行业排序")
    private Integer industrySort;

}
