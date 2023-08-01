package com.shuishu.blog.common.domain.label.dsl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.label.entity.dto.LabelQueryDto;
import com.shuishu.blog.common.domain.label.entity.po.QLabel;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVo;
import com.shuishu.blog.common.domain.user.entity.po.QUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:41
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签
 * <p></p>
 */
@Component
public class LabelDsl extends BaseDsl {
    private QLabel qLabel = QLabel.label;
    final QUser qUser = QUser.user;
    final QUser qUserUpdate = new QUser("qUserUpdate");

    public PageVO<LabelVo> findLabelPage(LabelQueryDto labelQueryDto, PageDTO pageDTO) {
        PageVO<LabelVo> pageVO = pageDTO.toPageVO(LabelVo.class);

        BooleanBuilder builder = new BooleanBuilder();
        if (labelQueryDto.getLabelSort() != null) {
            builder.and(qLabel.labelSort.loe(labelQueryDto.getLabelSort()));
        }
        if (StringUtils.hasText(labelQueryDto.getKeyword())) {
            builder.and(qLabel.labelName.like("%" + labelQueryDto.getKeyword() + "%")
                    .or(qLabel.labelDesc.like("%" + labelQueryDto.getKeyword() + "%")));
        }
        List<Long> labelIdList = jpaQueryFactory.select(qLabel.labelId).from(qLabel).where(builder).fetch();
        pageVO.setTotalElements(labelIdList.size());

        List<LabelVo> list = jpaQueryFactory.select(Projections.fields(LabelVo.class,
                        qLabel.labelId,
                        qLabel.labelName,
                        qLabel.labelDesc,
                        qLabel.labelSort,
                        qLabel.updateDate,
                        qUser.nickname.as("createNickname"),
                        qUserUpdate.nickname.as("updateNickname")

                ))
                .from(qLabel)
                .leftJoin(qUser).on(qLabel.createUserId.eq(qUser.userId))
                .leftJoin(qUserUpdate).on(qLabel.createUserId.eq(qUserUpdate.userId))
                .where(builder)
                .orderBy(qLabel.updateDate.desc())
                .fetch();

        pageVO.setDataList(list);

        return pageVO;
    }

    public LabelVo findLabelDetails(Long labelId) {
        if (labelId == null) {
            return null;
        }
        return jpaQueryFactory.select(Projections.fields(LabelVo.class,
                        qLabel.labelId,
                        qLabel.labelName,
                        qLabel.labelDesc,
                        qLabel.labelSort,
                        qLabel.updateDate,
                        qUser.nickname.as("createNickname"),
                        qUserUpdate.nickname.as("updateNickname")
                ))
                .from(qLabel)
                .leftJoin(qUser).on(qLabel.createUserId.eq(qUser.userId))
                .leftJoin(qUserUpdate).on(qLabel.createUserId.eq(qUserUpdate.userId))
                .where(qLabel.labelId.eq(labelId))
                .fetchOne();
    }
}
