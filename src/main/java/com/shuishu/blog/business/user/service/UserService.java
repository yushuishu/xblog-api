package com.shuishu.blog.business.user.service;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.user.entity.dto.UserAddDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdateDto;
import com.shuishu.blog.common.domain.user.entity.dto.UserUpdatePwdDto;
import com.shuishu.blog.common.domain.user.entity.vo.UserDetailsVo;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-29 22:39
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param userAddDTO -
     */
    void addUser(UserAddDto userAddDTO);

    /**
     * 更新用户信息
     *
     * @param userUpdateDto 更新的用户信息
     */
    void updateUser(UserUpdateDto userUpdateDto);

    /**
     * 修改密码
     *
     * @param userUpdatePwdDto 修改密码信息
     */
    void updateUserPassword(UserUpdatePwdDto userUpdatePwdDto);

    /**
     * 用户详情
     *
     * @param userId 用户id
     * @return 用户详情
     */
    UserDetailsVo findUserDetails(Long userId);

    /**
     * 分页查询用户信息
     *
     * @param userQueryDto 查询条件
     * @param pageDTO 分页参数
     * @return 分页用户信息
     */
    PageVO<UserDetailsVo> findUserPage(UserQueryDto userQueryDto, PageDTO pageDTO);


}
