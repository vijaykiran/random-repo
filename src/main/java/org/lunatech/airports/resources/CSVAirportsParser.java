package org.lunatech.airports.resources;

import org.lunatech.airports.model.Airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 16.01.2016.
 */
public class CSVAirportsParser {

    private static String resourse = Config.getAirports();
    private static final String CVS_SPLIT_BY = ",";

    protected List<Airport> parse() {
        String line = null;
        List<Airport> airports = new ArrayList<Airport>();

        try(BufferedReader br = new BufferedReader(new FileReader(resourse))){
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] row = line.split(CVS_SPLIT_BY);
                Airport airport = new Airport();
                airport.setName(row[3]);
                airport.setIdent(row[1]);
                airport.setIso_country(row[8]);
                airports.add(airport);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return airports;
    }
}
