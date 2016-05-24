package org.diveintojee.poc.standardstack.dbmigration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.PreparedStatement;

import org.diveintojee.poc.standardstack.domain.LocalDateTimeAttributeConverter;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DbMigrationApplication.class)
public class MigrationTest {

    @Autowired
    private Flyway underTest;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Test
	public void migrationShouldSucceed() {
		underTest.migrate();

		final Registration registration = Registration.validRegistration();
		// Create
		String token = createRegistration(registration);
		assertNotNull(token);
		// Read
		Registration saved = retrieveRegistration(token);
		assertNotNull(saved);
		assertEquals(token, saved.getToken());
		// Update
		String newEmail = Registration.validEmail();
		saved.setEmail(newEmail);
		updateRegistration(saved);
		Registration updated = retrieveRegistration(token);
		assertEquals(newEmail, updated.getEmail());
		// Delete
		deleteRegistration(token);
		try {
			retrieveRegistration(token);
		} catch (EmptyResultDataAccessException e) {
		} catch (Throwable t) {
			Assert.fail("EmptyResultDataAccessException expected, got " + t);
		}

	}


	private void deleteRegistration(String token) {
		String query = "delete from registrations where token = ?";
		Object[] args = new Object[] {token};
		int out = jdbcTemplate.update(query, args);
		assertEquals(1, out);
	}

	private void updateRegistration(Registration registration) {
		String query = "update registrations set first_name = ?, last_name = ?, email = ? where token = ?";
		Object[] args = new Object[] {registration.getFirstName(), registration.getLastName(), registration.getEmail(), registration.getToken()};
		int out = jdbcTemplate.update(query, args);
		assertEquals(1, out);
	}

	private Registration retrieveRegistration(String token) {
		String query = "select token, first_name, last_name, email, expires, archived from registrations where token = ?";
		final LocalDateTimeAttributeConverter localDateTimeAttributeConverter = new LocalDateTimeAttributeConverter();

		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		return jdbcTemplate.queryForObject(query, new Object[]{token}, (rs, rowNum) -> {
			Registration registration = new Registration();
			registration.setToken(rs.getString("token"));
			registration.setFirstName(rs.getString("first_name"));
			registration.setLastName(rs.getString("last_name"));
			registration.setEmail(rs.getString("email"));
			registration.setExpires(localDateTimeAttributeConverter.convertToEntityAttribute(rs.getTimestamp("expires")));
			registration.setArchived(localDateTimeAttributeConverter.convertToEntityAttribute(rs.getTimestamp("archived")));
			return registration;
		});
	}

	private String createRegistration(final Registration registration) {
		String token = registration.generateToken();
		registration.setToken(token);
		final String INSERT_QUERY = "insert into registrations (token, first_name, last_name, email, expires) values (?, ?, ?, ?, ?)";
		final LocalDateTimeAttributeConverter localDateTimeAttributeConverter = new LocalDateTimeAttributeConverter();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ps.setString(1, token);
			ps.setString(2, registration.getFirstName());
			ps.setString(3, registration.getLastName());
			ps.setString(4, registration.getEmail());
			ps.setTimestamp(5, localDateTimeAttributeConverter.convertToDatabaseColumn(registration.getExpires()));
			return ps;
		});

		return token;
	}

}
