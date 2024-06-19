package com.romaingk.quickstart.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
@RequiredArgsConstructor
public class DatasourceConfiguration {

    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplateConfig(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
