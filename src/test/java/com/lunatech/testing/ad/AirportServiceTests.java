package com.lunatech.testing.ad;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lunatech.testing.ad.dao.DataSource;
import com.lunatech.testing.ad.service.AirportService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AirportApplication.class)
@WebAppConfiguration
public class AirportServiceTests {

	@Autowired
	private AirportService airportService;
	
	@Before
	public void setUp() throws Exception {
		airportService.createAllAirports();
	}

	@Test
	public void createAllAirports() throws Exception {
		airportService.createAllAirports();
		
		Assert.assertEquals(DataSource.getAirports().size(), 46505);
	}
	
	@Test
	public void createAllRunways() throws Exception {
		airportService.createAllRunways();
		Assert.assertEquals(DataSource.getRunways().size(), 39536);
	}
	
	@Test
	public void getNbAirportsPerCountry() throws Exception {
		Map<String, Long> airports = airportService.getNbAirportsPerCountry();
		System.out.println(airports.entrySet());
		Assert.assertTrue(airports.get("Morocco")==36);
		Assert.assertTrue(airports.get("Romania")==28);
	}
	
	@Test
	public void getHighestNbAirportsCountries() throws Exception {
		Map<String, Long> airportsSorted = airportService.getHighestNbAirportsCountries();
		System.out.println(airportsSorted.entrySet());
		Assert.assertTrue(airportsSorted.entrySet().size()==10);
		Assert.assertTrue(airportsSorted.get("Brazil")==3839);
	}
	
	@Test
	public void getLowestNbAirportsCountries() throws Exception {
		Map<String, Long> airportsSorted = airportService.getLowestNbAirportsCountries();
		System.out.println(airportsSorted.entrySet());
		Assert.assertTrue(airportsSorted.entrySet().size()==10);
		Assert.assertTrue(airportsSorted.entrySet().iterator().next().getValue()==0);
	}
	
	
}
