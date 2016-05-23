package org.diveintojee.poc.standardstack.dbmigration;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbMigrationConfigurationTest extends DbMigrationConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }
}
