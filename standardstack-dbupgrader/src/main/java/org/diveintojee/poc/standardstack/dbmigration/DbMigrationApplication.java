package org.diveintojee.poc.standardstack.dbmigration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

/**
 * @author louis.gueye@gmail.com
 */
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class})
public class DbMigrationApplication implements CommandLineRunner {

    @Autowired
    private Flyway flyway;

    @Override
    public void run(String... args) {
        this.flyway.migrate();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DbMigrationApplication.class, args);
    }

}
