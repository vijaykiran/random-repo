package org.lunatech.airports.service;

import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;
import org.lunatech.airports.resources.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class ServiceRequest {
    private Context context = new Context();

    public Country getQuery(String countryCodeOrName){
        List<Country> countries = context.getCountries();
        List<Country> result = countries.stream()
                .filter(country -> country.getName().toLowerCase().startsWith(countryCodeOrName.toLowerCase()) ||
                        country.getCode().equals(countryCodeOrName))
                .collect(Collectors.toList());
        if (result.size() != 1) {
            throw new IllegalStateException();
        }
        Country resultCountry = result.get(0);
        return resultCountry;
    }

    public List<Country> getReport(){
        List<Country> countries = context.getCountries();
        List<Country> sortedCountries = countries.stream()
                .sorted((country1, country2) -> Integer.compare(country2.getAirports().size(),
                        country1.getAirports().size()))
                .collect(Collectors.toList());
        return sortedCountries;
    }

    public List<Runway> getRunways(){
        return context.getRunways();
    }
}
