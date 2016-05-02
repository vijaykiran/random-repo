package com.lunatech.testing.ad.dao;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.lunatech.testing.ad.entities.Country;

@Repository
public class CountryDao {

	public Set<Country> getCountriesByName(String name) {
		
		return DataSource.getCountries().stream()
				.filter(country -> country.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toSet());

	}

	public Set<Country> getCountriesByCode(String code) {
		return DataSource.getCountries().stream()
				.filter(country -> country.getCode().equalsIgnoreCase(code))
				.collect(Collectors.toSet());
	}

}
