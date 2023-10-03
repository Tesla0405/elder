package com.qxy.elder.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfig {



    @Bean
    @ConfigurationProperties(prefix = "querydsl.jdbc")
    public DataBaseProperties dataBaseProperties() {
        return new DataBaseProperties();
    }

    @Bean
    public DataSource dataSource(DataBaseProperties dataBaseProperties) {
        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl(dataBaseProperties.getJdbcUrl());
        conf.setDriverClassName(dataBaseProperties.getDriverClass());
        conf.setUsername(dataBaseProperties.getUsername());
        conf.setPassword(dataBaseProperties.getPassword());
        return new HikariDataSource(conf);
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlQueryFactory")
    public SQLQueryFactory mysqlQueryFactory( DataSource dataSource) {
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(MySQLTemplates.builder().quote().build());
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        SpringConnectionProvider provider = new SpringConnectionProvider(dataSource);
        return new SQLQueryFactory(configuration, provider);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource, true);
    }

}
