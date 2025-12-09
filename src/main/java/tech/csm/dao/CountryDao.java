package tech.csm.dao;

import java.util.List;

// Only method signatures here. No @Repository on the interface
import tech.csm.entity.Country;

public interface CountryDao {
	
	List<Country> getAllCountries();

}
