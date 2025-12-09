package tech.csm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.csm.entity.Country;

@Repository
public class CountryDaoImpl implements CountryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// JdbcTemplate is a spring utility that simplifies JDBC code 

	
	@Override
	public List<Country> getAllCountries(){		
		String sql = "SELECT id, name FROM country";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
	}
}

//	Country country= jdbcTemplate.queryForObject(
//	"select id, name from country where id=1", new RowMapper<Country>() {
//
//		@Override
//		public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Country c=new Country();
//			c.setId(rs.getInt(1));
//			c.setName(rs.getString(2));
//			return c;
//		}
//		
//	});

	

	


