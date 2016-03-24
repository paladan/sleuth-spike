package com.example.configuration.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name="oracleAdvancedQueueDataSource")
    @Primary
    public DataSource oracleAdvancedQueueDataSource() {
        DataSource dataSource = DataSourceBuilder
                .create()
                .url("")
                .username("")
                .password("")
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .type(DriverManagerDataSource.class)
                .build();

        return dataSource;
    }

}
