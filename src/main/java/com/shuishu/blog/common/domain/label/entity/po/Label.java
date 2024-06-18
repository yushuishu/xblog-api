package com.shuishu.blog.common.domain.label.entity.po;


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
 * @Date ：2023-04-03 22:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_label")
@Comment(value = "标签表")
public class Label extends BasePO {
    @TableId(value = "label_id", type = IdType.ASSIGN_ID)
    @Comment(value = "标签id")
    private Long labelId;

    @TableField("label_name")
    @Column(nullable = false, unique = true)
    @Comment(value = "标签名称")
    private String labelName;

    @TableField("label_desc")
    @Comment(value = "标签描述，以|分隔")
    private String labelDesc;

    @TableField("label_sort")
    @Comment(value = "标签排序")
    private Integer labelSort;

}
