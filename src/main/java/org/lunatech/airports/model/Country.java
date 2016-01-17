package org.lunatech.airports.model;

import java.util.List;

/**
 * Created by Anastasia on 16.01.2016.
 */
public class Country {
    private String name;
    private String code;
    private List<Airport> airports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}
