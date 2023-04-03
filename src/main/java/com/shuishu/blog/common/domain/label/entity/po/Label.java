package com.shuishu.blog.common.domain.label.entity.po;


import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_label")
@Comment(value = "用户表")
public class Label extends BasePO {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "标签id")
    private Long labelId;

    @Comment(value = "标签名称")
    private String labelName;

    @Comment(value = "标签描述，以|分隔")
    private String labelDesc;

    @Comment(value = "标签排序")
    private Integer labelSort;

}
