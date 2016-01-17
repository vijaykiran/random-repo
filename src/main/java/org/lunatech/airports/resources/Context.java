package org.lunatech.airports.resources;

import org.lunatech.airports.model.Airport;
import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class Context {
    private CSVRunwayParser runwayParser = new CSVRunwayParser();
    private CSVAirportsParser airportsParser = new CSVAirportsParser();
    private CSVCountryParser countryParser = new CSVCountryParser();
    private List<Country> context;

    public Context() {
        List<Runway> runways = runwayParser.parse();
        List<Airport> airports = airportsParser.parse();
        List<Country> countries = countryParser.parse();
        Map<String, List<Runway>> runwaysByAirports = runways.stream().
                collect(Collectors.groupingBy(Runway::getAirport_ident));
        airports.stream()
                .forEach(airport -> airport.setRunways(runwaysByAirports.get(airport.getIdent())));
        countries.stream()
                .forEach(country -> country.setAirports(airports.stream()
                        .filter(airport -> airport.getIso_country().equals(country.getCode()))
                        .collect(Collectors.toList())));
        context = countries;
    }

    public List<Country> getCountries(){
        return context;
    }
}
