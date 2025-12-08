package tech.csm.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder db = DataSourceBuilder.create();
		db.driverClassName("com.mysql.cj.jdbc.Driver");
		db.url("jdbc:mysql://localhost:3306/csex_db");
		db.username("root");
		db.password("root");
		return db.build();
	}
	@Bean
	public String getStringName() {
		return "hi from Spring JDBC...:";
	}
	
	

}
