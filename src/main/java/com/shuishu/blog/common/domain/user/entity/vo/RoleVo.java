package com.shuishu.blog.common.domain.user.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2023-04-18 12:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：角色信息vo
 * <p></p>
 */
@Schema(description = "角色信息vo")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleVo extends BaseVO<Role> {
    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色code")
    private String roleCode;

    @Schema(description = "角色描述")
    private String roleDescription;

    @Schema(description = "操作编辑删除权限：true可以；false不可以")
    private Boolean roleOperatePower;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Schema(description = "创建人昵称")
    private String createNickname;

    @Schema(description = "更新人昵称")
    private String updateNickname;

}
