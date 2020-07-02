package com.mimu.springboot.dubbo.provider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * generate datasource properties bean
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * generate datasource bean
     *
     * @return
     */
    @Bean
    public DataSource userDataSource() {
        DataSourceProperties properties = userDataSourceProperties();
        logger.info("init fdbDataSource");
        return properties.initializeDataSourceBuilder().build();
    }

    /**
     * generate db transactionManager bean
     *
     * @param userDataSource
     * @return
     */
    @Bean
    @Resource
    public PlatformTransactionManager userTxManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }


    /**
     * generate jdbcTemplate bean
     *
     * @param userDataSource
     * @return
     */
    @Bean
    @Autowired
    public JdbcTemplate userJdbcTemplate(DataSource userDataSource) {
        return new JdbcTemplate(userDataSource);
    }
}
