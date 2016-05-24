package org.diveintojee.poc.standardstack.dbmigration;

import java.sql.Driver;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DbMigrationConfiguration {

    @Value("${datasource.driverClassName}")
    protected String driverClassName;
    @Value("${datasource.username}")
    protected String userName;
    @Value("${datasource.password}")
    protected String password;
    @Value("${datasource.url}")
    protected String url;

    @Bean
    public SimpleDriverDataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(driverClassName));
        dataSource.setUsername(userName);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Flyway flyway() throws ClassNotFoundException {
        Flyway flyway = new Flyway();
        flyway.setLocations("migrations");
        flyway.setDataSource(dataSource());
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

}
