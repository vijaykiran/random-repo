package org.lunatech.airports.resources;

import org.junit.Test;
import org.lunatech.airports.model.Country;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class ContextTest {

    @Test
    public void testGetCountries() throws Exception {
        List<Country> countries = new Context().getCountries();
        assertNotNull(countries);
        assertEquals(247, countries.size());
        assertEquals(32, countries.get(1).getAirports().size());
        assertEquals(63, countries.get(2).getAirports().size());
        assertEquals(1, countries.get(226).getAirports().get(97).getRunways().size());
    }
}