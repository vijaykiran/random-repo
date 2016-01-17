package org.lunatech.airports.resources;

import org.lunatech.airports.model.Runway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class CSVRunwayParser {

    private static String resourse = Config.getRunways();
    private static final String CVS_SPLIT_BY = ",";

    protected List<Runway> parse() {
        String line = null;
        List<Runway> runways = new ArrayList<Runway>();

        try(BufferedReader br = new BufferedReader(new FileReader(resourse))){
            //read the first line (headers)
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] row = line.split(CVS_SPLIT_BY, -1);
                Runway runway = new Runway();
                runway.setType(row[5].replace("\"", ""));
                runway.setLatitude(row[8].replace("\"", ""));
                runway.setAirport_ident(row[2].replace("\"", ""));
                runways.add(runway);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return runways;
    }
}
