package org.lunatech.airports.resources;

import org.junit.Before;
import org.junit.Test;
import org.lunatech.airports.model.Runway;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anastasia on 17.01.2016.
 */
public class CSVRunwayParserTest {
    private CSVRunwayParser csvRunwayParser;

    @Before
    public void setUp() throws Exception {
        csvRunwayParser = new CSVRunwayParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Runway> runways = csvRunwayParser.parse();
        assertNotNull(runways);
        assertEquals(Runway.class, runways.get(1).getClass());
    }
}