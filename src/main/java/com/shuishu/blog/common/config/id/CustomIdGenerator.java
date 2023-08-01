package com.shuishu.blog.common.config.id;


import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.Serializable;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-25 11:51
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
public class CustomIdGenerator extends JpaIdGenerator{
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return super.generate(session, 1);
    }
}
