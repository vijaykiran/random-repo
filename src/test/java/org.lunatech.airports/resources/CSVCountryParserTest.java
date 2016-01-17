package org.lunatech.airports.resources;

import org.junit.Before;
import org.junit.Test;
import org.lunatech.airports.model.Country;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class CSVCountryParserTest {
    private CSVCountryParser csvCountryParser;

    @Before
    public void setUp() throws Exception {
        csvCountryParser = new CSVCountryParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Country> countries = csvCountryParser.parse();
        assertNotNull(countries);
        assertEquals("AD", countries.get(0).getCode());
        assertEquals("United Arab Emirates", countries.get(1).getName());
    }
}