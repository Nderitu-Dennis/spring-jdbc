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
	
	  public int countCountries() {
	        String sql = "SELECT count(*) FROM country";
	        return jdbcTemplate.queryForObject(sql, Integer.class);
	    }

	  @Override
	  public Country getCountryById(Integer id) {
	      String sql = "SELECT id, name FROM country WHERE id = ?";
	      return jdbcTemplate.queryForObject(
	              sql,
	              new BeanPropertyRowMapper<>(Country.class),
	              id
	      );
	  }

	  @Override
	  public int addCountry(String name) {
	        String sql = "INSERT INTO country (name) VALUES (?)";
	        return jdbcTemplate.update(sql, name);
	    }

	  @Override
	  public int deleteCountryById(Integer id) {
		  String sql = "DELETE FROM country WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	  }

	  @Override
	   public int updateCountry(Integer id, String name) {
	        String sql = "UPDATE country SET name = ? WHERE id = ?";
	        return jdbcTemplate.update(sql, name, id);
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

	

	


