package org.lunatech.airports.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class Config {
    private static String runways;
    private static String airports;
    private static String countries;
    private static Properties props = new Properties();

    private static void loadProperties(){
        try {
            props.load(props.getClass().getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException("Couldn't find resources", e);
        }
    }

    public static String getRunways() {
        loadProperties();
        runways = props.getProperty("runways");
        return runways;
    }

    public static String getAirports() {
        loadProperties();
        airports = props.getProperty("airports");
        return airports;
    }

    public static String getCountries() {
        loadProperties();
        countries = props.getProperty("countries");
        return countries;
    }


}
