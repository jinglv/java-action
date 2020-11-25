package com.demo.action.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置
 *
 * @author jingLv
 * @date 2020/11/25
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 数据库类型配置
     *
     * @return
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 数据库类型配置
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁配置
     * 乐观锁使用的规则：
     * 1. 如果更新数据中不带有version字段：不使用乐观锁，并且version不会累加
     * 2. 如果更新字段中带有version字段，但与数据库中不一致，更新失败
     * 3. 如果带有version，并且与数据库中一致，更新成功，并且version增加
     *
     * @return
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }
}
