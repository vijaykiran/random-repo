package com.lunatech.testing.ad.mvc;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Country;
import com.lunatech.testing.ad.service.AirportService;
import com.lunatech.testing.ad.service.CountryService;

@Controller
public class QueryController {

	public final static Logger log = LoggerFactory.getLogger(QueryController.class);

	@Autowired
	private CountryService countryService;

	@Autowired
	private AirportService airportService;

	@RequestMapping("/query")
	public String renderQueryPage(@RequestParam(value="searchStr", required=false) String searchStr, Model model) {
		log.info("Rendering of the Query page");
		try {
			if(searchStr != null && searchStr.length()>0){
				model.addAttribute("airports", airportService.getAirportRunwaysForCountries(searchStr));
			} else {
				model.addAttribute("airports", new HashSet<>());	
			}
		} catch (ClassNotFoundException | FileNotFoundException e) {
			log.info("There was a problem in performing the search requested");
			model.addAttribute("errorMessage", "There was a problem in performing the search you requested.");
			e.printStackTrace();
		}
		return "query";
	}



}
