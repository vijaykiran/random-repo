package org.lunatech.airports.console.step;

import org.lunatech.airports.model.Airport;
import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;

import java.util.*;
import java.util.stream.Collectors;

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

    private void printBottom10(List<Country> countries) {
        System.out.println("10 countries with the lowest number of airports.");
        for (int i = countries.size() - 1; i > countries.size() - 11; i--) {
            System.out.println(countries.get(i).getName() + " " + countries.get(i).getAirports().size());
        }
    }

    private void printTop10(List<Country> countries) {
        System.out.println("10 countries with highest number of airports.");
        for (int i = 0; i < 10; i++) {
            System.out.println(countries.get(i).getName() + " " + countries.get(i).getAirports().size());
        }
    }
}
