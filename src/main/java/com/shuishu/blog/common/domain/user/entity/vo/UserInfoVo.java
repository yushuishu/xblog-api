package com.shuishu.blog.common.domain.user.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.*;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 15:03
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：用户登录信息
 */
@Schema(description = "用户登录信息vo")
@Setter
@Getter
@ToString
public class UserInfoVo implements UserDetails {
    @Serial
    private static final long serialVersionUID = -7850778107226817897L;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;
    /**
     * 个人简介
     */
    @Schema(description = "个人简介")
    private String userAbout;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String userPhoto;
    /**
     * 地址
     */
    @Schema(description = "地址")
    private String userAddress;
    /**
     * 行业
     */
    @Schema(description = "行业")
    private String industryName;
    /**
     * 用户过期true：没有过期  false：过期
     */
    @Schema(description = "用户过期true：没有过期  false：过期")
    private Boolean userIsAccountNonExpired;
    /**
     * 用户锁定true：没有锁定  false：被锁定
     */
    @Schema(description = "")
    private Boolean userIsAccountNonLocked;
    /**
     * 最后一次登录时间
     */
    @Schema(description = "最后一次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userAuthLastLoginDate;
    /**
     * 用户授权id
     */
    @Schema(description = "用户授权id")
    private Long userAuthId;
    /**
     * 授权类型
     */
    @Schema(description = "授权类型")
    private String userAuthType;
    /**
     * 登录号：唯一识别码
     */
    @Schema(description = "登录号：唯一识别码")
    private String userAuthIdentifier;
    /**
     * 凭证信息
     */
    @Schema(description = "凭证信息")
    @JsonIgnore
    private String userAuthCredential;
    /**
     * 刷新token
     */
    @Schema(description = "刷新token")
    private String userAuthRefreshToken;
    /**
     * 授权昵称
     */
    @Schema(description = "授权昵称")
    private String userAuthNickname;
    /**
     * 授权头像
     */
    @Schema(description = "授权头像")
    private String userAuthPhoto;
    /**
     * 同时登录客户端的人数:最小1最大50（默认1）
     */
    @Schema(description = "同时登录客户端的人数:最小1最大50（默认1）")
    private Integer userMaxLoginClientNumber;
    /**
     * 角色集合
     */
    @Schema(description = "角色集合")
    private List<RoleInfoVo> roleInfoList;
    /**
     * 权限集合
     */
    @Schema(description = "权限集合")
    private List<PermissionInfoVo> permissionInfoList;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        this.permissionInfoList.forEach(t -> authorities.add(new SimpleGrantedAuthority(t.getPermissionUrl())));
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.userAuthCredential;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.userAuthIdentifier;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.userIsAccountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.userIsAccountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
