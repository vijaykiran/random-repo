package com.lunatech.testing.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.lunatech.testing.ad.service.CsvPropertiesInUseConst;

@SpringBootApplication
public class AirportApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(AirportApplication.class, args);

	}
}
