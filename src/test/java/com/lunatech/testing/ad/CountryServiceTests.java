package com.lunatech.testing.ad;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lunatech.testing.ad.dao.DataSource;
import com.lunatech.testing.ad.entities.Country;
import com.lunatech.testing.ad.service.CountryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AirportApplication.class)
@WebAppConfiguration
public class CountryServiceTests {

	@Autowired
	private CountryService countryService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void createAllCountries() throws Exception {
		countryService.createAllCountries();
		Assert.assertEquals(DataSource.getCountries().size(), 247);
	}
	
	@Test
	public void getCountriesByNameAndCode() throws Exception {

		Set<Country> countries = countryService.getCountriesByNameAndCode("mo");
		Assert.assertNotNull(countries);
		Assert.assertEquals(13, countries.size());
		
		countries = countryService.getCountriesByNameAndCode("Morocco");
		Assert.assertNotNull(countries);
		Assert.assertEquals(1, countries.size());
		
		countries = countryService.getCountriesByNameAndCode("ma");
		Assert.assertNotNull(countries);
		Assert.assertEquals(31, countries.size());
		
	}
}
