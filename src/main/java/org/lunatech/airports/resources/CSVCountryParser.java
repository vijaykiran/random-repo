package org.lunatech.airports.resources;

import org.lunatech.airports.model.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class CSVCountryParser {

    private static String resourse = Config.getCountries();
    private static final String CVS_SPLIT_BY = ",";

    protected List<Country> parse() {
        String line = null;
        List<Country> countries = new ArrayList<Country>();

        try(BufferedReader br = new BufferedReader(new FileReader(resourse))){
            //read the first line (headers)
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] row = line.split(CVS_SPLIT_BY, -1);
                Country country = new Country();
                country.setName(row[2].replace("\"", ""));
                country.setCode(row[1].replace("\"", ""));
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
