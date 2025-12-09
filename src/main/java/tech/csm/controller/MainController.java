package tech.csm.controller;

import java.sql.SQLException;
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
	//Spring will inject CountryDaoImpl cz it implements this interface	

	@GetMapping("/test")
	public String getForm() {
		List<Country> countries = countryDao.getAllCountries();
		System.out.println("**countries** " + countries);
		return "demo";  //demo.jsp
		
	}
}

		
		
		

	





		
	


