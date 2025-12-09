package tech.csm.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * You don’t even need to define a DataSource bean manually;
 *  Spring Boot auto-configures it from application.properties.
 *  Here we just create a JdbcTemplate bean that uses the auto-configured DataSource.

 */

@Configuration  // Tells Spring this class contains bean definitions
public class DataSourceConfiguration {
	@Bean  //This method returns an object that should be managed by the Spring IoC container
	
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	

}
