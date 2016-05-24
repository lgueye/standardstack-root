package org.diveintojee.poc.standardstack.dbmigration;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.diveintojee.poc.standardstack.domain.Registration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author louis.gueye@gmail.com
 */
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class InitialDataSetApplication implements CommandLineRunner {

    @Value("${lines_count}")
    private Integer linesCount;

    @Value("${destination}")
    private String destination;

    @Override
    public void run(String... args) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < linesCount; i++) {
            String line = newInsertStatement();
            lines.add(line);
        }
        Path dest = Paths.get(destination);
        Files.write(dest, lines, cs, TRUNCATE_EXISTING, CREATE);
    }

    private static String newInsertStatement() {
        final Registration registration = Registration.validRegistration();
        final String pattern =
                "insert into registrations (token, first_name, last_name, email, expires, archived) values ('%s', '%s', '%s', '%s', '%s', NULL);";
        return String.format(pattern, registration.getToken(), registration.getFirstName(), registration.getLastName(), registration.getEmail(),
                registration.getExpires().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(InitialDataSetApplication.class, args);
    }

}
