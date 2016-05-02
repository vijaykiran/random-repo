package com.lunatech.testing.ad.dao;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Country;

@Repository
public class AirportDao {

	public Set<Airport> getAirportsForCountry(Country country) {
		
		return DataSource.airports
				.stream()
				.filter(air -> air.getIso_country()!=null && air.getIso_country().equals(country.getCode()))
				.collect(Collectors.toSet());
	}
	
	public Map<String, Long> getNbAirportsPerCountry(){
		return DataSource.airports.stream()
				.collect(Collectors.groupingBy(Airport::getIso_country, 
												Collectors.counting()));
	}

}
