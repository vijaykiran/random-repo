package com.lunatech.testing.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lunatech.testing.ad.dao.AirportDao;
import com.lunatech.testing.ad.dao.DataSource;
import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.service.AirportService;
import com.lunatech.testing.ad.service.CountryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AirportApplication.class)
@WebAppConfiguration
public class AirportDaoTests {

	@Autowired
	private AirportDao airportDao;
	
	@Before
	public void setUp() throws Exception {
		List<Airport> liste = new ArrayList<>();
		Airport airport = new Airport();
		airport.setId(1L);
		airport.setIso_country("MA");
		airport.setName("Grand Aeroport");
		liste.add(airport);
		
		airport = new Airport();
		airport.setId(2L);
		airport.setIso_country("MA");
		airport.setName("Petit Aeroport");
		liste.add(airport);
		
		airport = new Airport();
		airport.setId(3L);
		airport.setIso_country("CO");
		airport.setName("Biggie");
		liste.add(airport);
		
		DataSource.setAirports(liste);
	}

	@Test
	public void getNbAirportsPerCountry() throws Exception {
		Map<String, Long> map = airportDao.getNbAirportsPerCountry();
		
		Assert.assertTrue(map.get("MA")==2);
		Assert.assertNull(map.get("US"));
	}

}
