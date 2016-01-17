package org.lunatech.airports.model;

/**
 * Created by Anastasia on 16.01.2016.
 */
public class Runway {
    private String type;
    private String latitude;
    private String airport_ident;

    public String getAirport_ident() {
        return airport_ident;
    }

    public void setAirport_ident(String airport_ident) {
        this.airport_ident = airport_ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

