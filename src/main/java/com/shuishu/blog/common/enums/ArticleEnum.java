package com.shuishu.blog.common.enums;


/**
 * @author wuZhenFeng
 * @date 2024/4/15 13:12
 */


import com.shuishu.blog.common.config.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/15 13:12
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章enum
 * <p></p>
 */
public interface ArticleEnum {

    @Getter
    @AllArgsConstructor
    enum Status{
        /**
         * 状态：0:待编辑 1:未发布 2:已发布 3:已删除
         */
        AWAIT_EDIT(0, "待编辑"),
        UB_PUBLISH(1, "未发布"),
        PUBLISH(2, "已发布"),
        DELETE(3, "已删除"),

        ;

        private final Integer code;
        private final String des;

        public static void verify(Integer code) {
            if (code != null) {
                for (Status status : Status.values()) {
                    if (status.getCode().equals(code)) {
                        return;
                    }
                }
            }
            throw new BusinessException("文章状态错误");
        }

    }

}
