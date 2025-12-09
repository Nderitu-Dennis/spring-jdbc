package tech.csm.dao;

import java.util.List;

/* Only method signatures here. No @Repository on the interface
 * read operation returns the entity.(SELECT)
 * write operation returns the number of modified rows.(INSERT, UPDATE, DELETE)
 * */

import tech.csm.entity.Country;

public interface CountryDao {

	List<Country> getAllCountries();

	int countCountries();

	Country getCountryById(Integer id);

	int addCountry(String name);

	int deleteCountryById(Integer id);

	int updateCountry(Integer id, String name);

}
