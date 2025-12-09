package tech.csm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tech.csm.dao.CountryDao;
import tech.csm.entity.Country;

@Controller
public class MainController {

	@Autowired
	private CountryDao countryDao;

	// Spring will inject CountryDaoImpl cz it implements this interface

	@GetMapping("/test")
	public String getForm() {
		
		List<Country> countries = countryDao.getAllCountries();
		for (Country c : countries) {
			System.out.println("**countries-- " + c);

		}
		System.out.println("**countries** " + countries);

		System.out.println("no of countries: " + countryDao.countCountries());

		Country country = countryDao.getCountryById(2);
		System.out.println("country of id 1: " + country);
		return "demo"; // demo.jsp

	}
	
	@GetMapping("/insert")
	public String addCountry() {
		countryDao.addCountry("Jamaica");
		System.out.println(countryDao.getAllCountries());
		return "demo";
	}
	
	@GetMapping("/delete")
	public String deleteCountryById() {
		countryDao.deleteCountryById(2);
		return "demo";
	}
	
	@GetMapping("/update")
	public String update() {
	    countryDao.updateCountry(2, "the american dream");
	    return "update success";
	}
}
