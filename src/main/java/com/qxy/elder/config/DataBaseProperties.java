package com.qxy.elder.config;

import lombok.Data;

@Data
public class DataBaseProperties {
    private String driverClass;
    private String jdbcUrl;
    private String username;
    private String password;
    private String maxPoolSize;
}
