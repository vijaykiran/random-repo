package com.lunatech.testing.ad.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunatech.testing.ad.SourceFilesConst;
import com.lunatech.testing.ad.dao.CountryDao;
import com.lunatech.testing.ad.dao.DataSource;
import com.lunatech.testing.ad.entities.Country;
import com.opencsv.CSVReader;

@Service
public class CountryService {
	
	public final static Logger log = LoggerFactory.getLogger(CountryService.class);

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private CsvOperations csvOperations;
	
	public void createAllCountries() throws ClassNotFoundException, FileNotFoundException{
		String fileName = SourceFilesConst.getSourceFiles().get("countries");
		createAllCountriesFromFile(fileName);
	}
	
	public void createAllCountriesFromFile(String fileName) throws ClassNotFoundException, FileNotFoundException{
		log.debug("Creating all countries from the source file "+fileName);
		CSVReader reader = csvOperations.getFileForName(fileName);
		String [] columns = CsvPropertiesInUseConst.getColumnsCountry()
				.stream()
				.toArray(String[]::new);
		List<Country> liste = csvOperations.mapCsvToEntity("com.lunatech.testing.ad.entities.Country", columns, reader);
		DataSource.setCountries(liste);
	}

	public Set<Country> getCountriesByNameAndCode(String searchStr) throws ClassNotFoundException, FileNotFoundException{
		checkCountriesNotNull();
		
		Set<Country> countries = new HashSet<>();
		countries.addAll(countryDao.getCountriesByName(searchStr));
		countries.addAll(countryDao.getCountriesByCode(searchStr));
		
		return countries;
	}
	
	public void checkCountriesNotNull() throws ClassNotFoundException, FileNotFoundException{
		if(DataSource.getCountries() == null){
			createAllCountries();
		}
	}
}
