package com.lunatech.testing.ad;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AirportApplication.class)
@WebAppConfiguration
public class AirportApplicationTests {

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void applicationStartUp(){
		
	}


}
