package tech.csm.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import tech.csm.entity.Country;

@Controller
public class MainController {

	@Autowired
	private DataSource dataSource;
	
	
	
	@GetMapping("/test")
	public String getForm() throws SQLException {
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		List<Country> cList=jdbcTemplate.query
				("select id, name from country", new BeanPropertyRowMapper(Country.class));
		
		
//		Country country= jdbcTemplate.queryForObject("select id, name from country where id=1", new RowMapper<Country>() {
//
//			@Override
//			public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Country c=new Country();
//				c.setId(rs.getInt(1));
//				c.setName(rs.getString(2));
//				return c;
//			}
//			
//		});
		
		//jdbcTemplate.up
		
		System.out.println(cList);
		
		return "demo";
	}
	
}
