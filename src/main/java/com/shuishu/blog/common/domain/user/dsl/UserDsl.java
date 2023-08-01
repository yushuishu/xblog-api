package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.industry.entity.po.QIndustry;
import com.shuishu.blog.common.domain.user.entity.dto.UserQueryDto;
import com.shuishu.blog.common.domain.user.entity.po.QUser;
import com.shuishu.blog.common.domain.user.entity.po.QUserAuth;
import com.shuishu.blog.common.domain.user.entity.po.User;
import com.shuishu.blog.common.domain.user.entity.vo.UserDetailsVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 0:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Component
public class UserDsl extends BaseDsl {
    private final QUser qUser = QUser.user;
    private final QUserAuth qUserAuth = QUserAuth.userAuth;
    private final QIndustry qIndustry = QIndustry.industry;
    private final QUser qUser2 = new QUser("qUser2");

    public PageVO<UserDetailsVo> findUserPage(UserQueryDto userQueryDto, PageDTO pageDTO) {
        PageVO<UserDetailsVo> page = pageDTO.toPageVO(UserDetailsVo.class);

        BooleanBuilder builder = new BooleanBuilder();
        if (userQueryDto.getUserIsAccountNonExpired() != null) {
            builder.and(qUser.userIsAccountNonExpired.eq(userQueryDto.getUserIsAccountNonExpired()));
        }
        if (userQueryDto.getUserIsAccountNonLocked() != null) {
            builder.and(qUser.userIsAccountNonLocked.eq(userQueryDto.getUserIsAccountNonLocked()));
        }
        if (userQueryDto.getUserMaxLoginClientNumber() != null) {
            builder.and(qUser.userMaxLoginClientNumber.eq(userQueryDto.getUserMaxLoginClientNumber()));
        }
        if (userQueryDto.getUserLastLoginDateStartRange() != null) {
            builder.and(qUser.userLastLoginDate.goe(userQueryDto.getUserLastLoginDateStartRange()));
        }
        if (userQueryDto.getUserLastLoginDateEndRange() != null) {
            builder.and(qUser.userLastLoginDate.loe(userQueryDto.getUserLastLoginDateEndRange()));
        }
        if (StringUtils.hasText(userQueryDto.getKeyword())) {
            builder.and(qUser.nickname.like("%" + userQueryDto.getKeyword() + "%")
                    .or(qUser.userAbout.like("%" + userQueryDto.getKeyword() + "%"))
                    .or(qUserAuth.userAuthIdentifier.like("%" + userQueryDto.getKeyword() + "%")));
        }

        List<Long> userIdList = jpaQueryFactory.select(qUser.userId).from(qUser).leftJoin(qUserAuth).on(qUser.userId.eq(qUserAuth.userId))
                .where(builder).distinct().fetch();
        page.setTotalElements(userIdList.size());

        List<UserDetailsVo> fetch = jpaQueryFactory.select(Projections.fields(UserDetailsVo.class,
                        qUser.userId,
                        qUser.nickname,
                        qUser.userAbout,
                        qUser.userPhoto,
                        qUser.userAddress,
                        qIndustry.industryName,
                        qUser.userIsAccountNonExpired,
                        qUser.userIsAccountNonLocked,
                        qUser.userLastLoginDate.as("userAuthLastLoginDate"),
                        qUser2.nickname.as("updateNickname"),
                        qUser.updateDate
                ))
                .from(qUser)
                .leftJoin(qUserAuth).on(qUser.userId.eq(qUserAuth.userId))
                .leftJoin(qIndustry).on(qUser.industryId.eq(qIndustry.industryId))
                .leftJoin(qUser2).on(qUser.updateUserId.eq(qUser2.userId))
                .where(builder)
                .distinct()
                .orderBy(qUser.updateDate.desc())
                .offset(page.getOffset()).limit(page.getPageSize())
                .fetch();

        page.setDataList(fetch);

        return page;
    }
}
