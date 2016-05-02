package com.lunatech.testing.ad.dao;

import java.util.List;

import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Country;
import com.lunatech.testing.ad.entities.Runway;

public class DataSource {
	public static List<Country> countries;
	public static List<Airport> airports;
	public static List<Runway> runways;
	
	public static List<Country> getCountries() {
		return countries;
	}
	public static void setCountries(List<Country> countries) {
		DataSource.countries = countries;
	}
	public static List<Airport> getAirports() {
		return airports;
	}
	public static void setAirports(List<Airport> airports) {
		DataSource.airports = airports;
	}
	public static List<Runway> getRunways() {
		return runways;
	}
	public static void setRunways(List<Runway> runways) {
		DataSource.runways = runways;
	}
	
}
