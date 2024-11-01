package com.basicsourcecode.profiles;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    public DataSource devDataSource(){
        return DataSourceBuilder.create()
                .url("${}")
                .username("")
                .password("")
                .driverClassName("")
                .build();
    }

}
