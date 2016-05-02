package com.lunatech.testing.ad.mvc;

import java.util.Set;

import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Country;
import com.lunatech.testing.ad.entities.Runway;

public class AirportRunwaysDto {

	private Country country;
	private Airport airport;
	private Set<Runway> runways;
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Airport getAirport() {
		return airport;
	}
	public void setAirport(Airport airport) {
		this.airport = airport;
	}
	public Set<Runway> getRunways() {
		return runways;
	}
	public void setRunways(Set<Runway> runways) {
		this.runways = runways;
	}
	
}
