package org.lunatech.airports.console.step;

import org.lunatech.airports.model.Airport;
import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;
import org.lunatech.airports.resources.Context;
import org.lunatech.airports.service.ServiceRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class StepQuery extends AbstractStep{

    @Override
    protected void doExecute() {
        System.out.println("Please write the country name or code:");
        String countryCodeOrName = readConsoleValue();
        Country country = serviceRequest.getQuery(countryCodeOrName);
        List<Airport> airports = country.getAirports();
        System.out.println(country.getName()+":");
        for (Airport airport : airports){
            System.out.println(airport.getName() + " :");
            if (airport.getRunways()!=null) {
                for (Runway runway : airport.getRunways()) {
                    System.out.println(runway.getType()+ " " + runway.getLatitude());
                }
            }
            else {
                System.out.println("this airport doesn't have any runways");
            }
        }

    }
}
