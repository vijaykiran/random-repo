package com.lunatech.testing.ad.mvc;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunatech.testing.ad.service.AirportService;

@Controller
public class ReportController {
	
	public final static Logger log = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private AirportService airportService;
	
	@RequestMapping("/report")
    public String renderReportPage(Model model) {
		try {
			model.addAttribute("airportHighest", airportService.getHighestNbAirportsCountries());
			model.addAttribute("airportLowest", airportService.getLowestNbAirportsCountries());
			model.addAttribute("runways", airportService.getRunwaysForCountries());
		} catch (ClassNotFoundException | FileNotFoundException e) {
			log.info("There was a problem in performing the search requested");
			model.addAttribute("errorMessage", "There was a problem in performing the search you requested.");
			e.printStackTrace();
		}
		return "report";
    }
	
}
