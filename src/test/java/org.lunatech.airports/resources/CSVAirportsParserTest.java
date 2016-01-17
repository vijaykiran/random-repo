package org.lunatech.airports.resources;

import org.junit.Before;
import org.junit.Test;
import org.lunatech.airports.model.Airport;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class CSVAirportsParserTest {
    private CSVAirportsParser csvAirportsParser;

    @Before
    public void setUp() throws Exception {
        csvAirportsParser = new CSVAirportsParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Airport> airports = csvAirportsParser.parse();
        assertNotNull(airports);
        assertEquals("00A", airports.get(0).getIdent());
        assertEquals("Lowell Field", airports.get(1).getName());
        assertEquals("US", airports.get(2).getIso_country());
    }
}