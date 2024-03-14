package com.shuishu.blog.common.domain.refuse.entity.po;


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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_refuse")
@Comment(value = "垃圾桶")
public class Refuse extends BasePO {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "垃圾桶id")
    private Long refuseId;

    @Comment("文章ID")
    private Long articleId;

    @Comment("到期删除时间")
    private Date expireDate;

}
