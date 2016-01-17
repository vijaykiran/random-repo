package org.lunatech.airports.console.step;

import org.apache.commons.lang3.StringUtils;
import org.lunatech.airports.model.Airport;
import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;

import java.util.*;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class StepReport extends AbstractStep {

    @Override
    protected void doExecute() {
        System.out.println("Here's the report");
        List<Country> countries = serviceRequest.getReport();
        printTop10(countries);
        printBottom10(countries);
        printRunwaysPerCountry(countries);
        printTop10Latitude();
    }

    private void printRunwaysPerCountry(List<Country> countries) {
        System.out.println("Type of runways per country");
        for (Country country : countries) {
            Set<String> runways = new HashSet();
            for (Airport airport : country.getAirports()) {
                if (airport.getRunways() != null) {
                    for (Runway runway : airport.getRunways()) {
                        if (!runway.getType().equals("")) {
                            runways.add(runway.getType());
                        }
                    }
                }
            }
            System.out.println(country.getName() + ": ");
            for (String type : runways) {
                System.out.print(type + ", ");
            }
            System.out.println();
        }
    }

    private void printTop10Latitude() {
        System.out.println("Top 10 most common runway latitude");
        List<Runway> runways = serviceRequest.getRunways();
        Map<String, Integer> latitudes = new HashMap<>();
        for (Runway runway : runways) {
            String latitude = runway.getLatitude();
            if (StringUtils.isNotEmpty(latitude)) {
                if (latitudes.containsKey(latitude)) {
                    latitudes.put(latitude, latitudes.get(latitude) + 1);
                } else {
                    latitudes.put(latitude, 1);
                }
            }
        }
        latitudes.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .forEachOrdered(System.out::println);
    }

    private void printBottom10(List<Country> countries) {
        countries.stream()
                .skip(countries.size() - 10)
                .forEach(country -> System.out.println(country.getName() + " "
                        + country.getAirports().size()));
    }

    private void printTop10(List<Country> countries) {
        countries.stream()
                .limit(10)
                .forEach(country -> System.out.println(country.getName() + " "
                        + country.getAirports().size()));
    }
}
