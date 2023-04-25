package com.shuishu.blog.common.domain.user.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.user.entity.po.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-06 20:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：添加用户
 * <p></p>
 */
@Schema(description = "添加用户dto")
@Setter
@Getter
@ToString
public class UserAddDto extends BaseDTO<User> {
    @NotBlank(message = "昵称不能为空")
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "地址")
    private String userAddress;

    @Schema(description = "行业id")
    private Long industryId;

    @NotBlank(message = "授权类型不能为空")
    @Schema(description = "授权类型")
    private String userAuthType;

    @NotBlank(message = "账号不能为空")
    @Schema(description = "注册号：唯一识别码")
    private String userAuthIdentifier;

    @Schema(description = "凭证信息")
    private String userAuthCredential;


}
