package com.roboter5123.samplerest.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Value("#{environment.database_url}")
    private String datasourceURL;

    @Value("#{environment.database_user}")
    private String datasourceUsername;

    @Value("#{environment.database_password}")
    private String datasourcePassword;

    @Bean
    public DataSource getDataSource(){

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(datasourceURL);
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);
        return dataSourceBuilder.build();
    }

}
