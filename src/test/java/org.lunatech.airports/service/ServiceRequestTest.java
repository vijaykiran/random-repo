package org.lunatech.airports.service;

import org.junit.Before;
import org.junit.Test;
import org.lunatech.airports.model.Country;
import org.lunatech.airports.model.Runway;
import org.lunatech.airports.resources.Context;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class ServiceRequestTest {
    private ServiceRequest serviceRequest;

    @Before
    public void setup(){
        serviceRequest = new ServiceRequest();
    }

    @Test
    public void testGetQuery() throws Exception {
        Country country = serviceRequest.getQuery("UA");
        Country country1 = serviceRequest.getQuery("ukra");
        assertNotNull(country);
        assertNotNull(country1);
        assertEquals("Ukraine", country.getName());
        assertEquals("Ukraine", country1.getName());
    }

    @Test
    public void testGetReport() throws Exception {
        List<Country> countries = serviceRequest.getReport();
        assertNotNull(countries);
        assertTrue(countries.get(1).getAirports().size()>countries.get(2).getAirports().size());
        assertTrue(countries.get(2).getAirports().size()>countries.get(3).getAirports().size());
    }

    @Test
    public void testGetRunways() throws Exception {
        List<Runway> runways = serviceRequest.getRunways();
        assertNotNull(runways);
    }
}