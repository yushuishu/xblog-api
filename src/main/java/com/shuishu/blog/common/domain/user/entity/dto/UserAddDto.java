package com.shuishu.blog.common.domain.user.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.user.entity.po.User;
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
@Setter
@Getter
@ToString
public class UserAddDto extends BaseDTO<User> {
    private String nickname;

    private String userAbout;

    private String userPhoto;

    private String userAddress;

    private Long industryId;

    private String userAuthType;

    private String userAuthIdentifier;

    private String userAuthCredential;


}
