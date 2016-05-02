package com.lunatech.testing.ad.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunatech.testing.ad.SourceFilesConst;
import com.lunatech.testing.ad.dao.AirportDao;
import com.lunatech.testing.ad.dao.DataSource;
import com.lunatech.testing.ad.dao.RunwayDao;
import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Country;
import com.lunatech.testing.ad.entities.Runway;
import com.lunatech.testing.ad.mvc.AirportRunwaysDto;
import com.opencsv.CSVReader;

@Service
public class AirportService {

	public final static Logger log = LoggerFactory.getLogger(AirportService.class);

	@Autowired
	private CountryService countryService;

	@Autowired
	private AirportDao airportDao;

	@Autowired
	private RunwayDao runwayDao;

	@Autowired
	private CsvOperations csvOperations;

	public void createAllAirports() throws ClassNotFoundException, FileNotFoundException{
		String fileName = SourceFilesConst.getSourceFiles().get("airports");
		createAllAirportsFromFile(fileName);
	}

	public void createAllAirportsFromFile(String fileName) throws ClassNotFoundException, FileNotFoundException{
		log.info("Creating all airports from the source file "+fileName);
		CSVReader reader = csvOperations.getFileForName(fileName);
		String [] columns = CsvPropertiesInUseConst.getColumnsAirport()
				.stream()
				.toArray(String[]::new);
		List<Airport> liste = csvOperations.mapCsvToEntity("com.lunatech.testing.ad.entities.Airport", columns, reader);
		DataSource.setAirports(liste);
	}

	public void createAllRunways() throws ClassNotFoundException, FileNotFoundException{
		String fileName = SourceFilesConst.getSourceFiles().get("runways");
		createAllRunwaysFromFile(fileName);
	}

	public void createAllRunwaysFromFile(String fileName) throws ClassNotFoundException, FileNotFoundException{
		log.info("Creating all runways from the source file "+fileName);
		CSVReader reader = csvOperations.getFileForName(fileName);
		String [] columns = CsvPropertiesInUseConst.getColumnsRunway()
				.stream()
				.toArray(String[]::new);
		List<Runway> liste = csvOperations.mapCsvToEntity("com.lunatech.testing.ad.entities.Runway", columns, reader);
		DataSource.setRunways(liste);
	}

	public Set<Airport> getAirportsForCountry(Country country) throws ClassNotFoundException, FileNotFoundException{
		checkAirportsNotNull();

		return airportDao.getAirportsForCountry(country);
	}

	public Set<Runway> getRunwaysForAirport(Airport airport) throws ClassNotFoundException, FileNotFoundException{
		checkRunwaysNotNull();

		return runwayDao.getRunwaysForAirport(airport);
	}

	public Map<String, Long> getNbAirportsPerCountry() throws ClassNotFoundException, FileNotFoundException{
		checkAirportsNotNull();
		countryService.checkCountriesNotNull();
		Map<String, Long> nbAirportsPerCountry = airportDao.getNbAirportsPerCountry();

		// Taking into consideration the countries that have no airports
		Map<String, Long> nbAirportsPerCountryWithNullAirports = new HashMap<>();
		for(Country country : DataSource.countries){
			if(nbAirportsPerCountry.get(country.getCode())==null){
				nbAirportsPerCountryWithNullAirports.put(country.getName(), (long) 0);
			} else {
				nbAirportsPerCountryWithNullAirports.put(country.getName(), nbAirportsPerCountry.get(country.getCode()));
			}
		}
		return nbAirportsPerCountryWithNullAirports;			
	}

	public Map<String, Long> getHighestNbAirportsCountries() throws ClassNotFoundException, FileNotFoundException{
		Map<String, Long> airportsUnsorted = getNbAirportsPerCountry();
		Map<String, Long> airportsSorted = airportsUnsorted.entrySet().stream()
				.sorted(Entry.comparingByValue((e1,e2)->Long.compare(e2, e1)))
				.limit(10)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));
		return airportsSorted;
	}

	public Map<String, Long> getLowestNbAirportsCountries() throws ClassNotFoundException, FileNotFoundException{
		Map<String, Long> airportsUnsorted = getNbAirportsPerCountry();
		Map<String, Long> airportsSorted = airportsUnsorted.entrySet().stream()
				.sorted(Entry.comparingByValue())
				.limit(10)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));
		return airportsSorted;
	}

	public Set<AirportRunwaysDto> getAirportRunwaysForCountries(String searchStr) throws ClassNotFoundException, FileNotFoundException{
		Set<AirportRunwaysDto> listeDto = new HashSet<>();
		Set<Country> listCountries = countryService.getCountriesByNameAndCode(searchStr);
		AirportRunwaysDto dto = null;
		Set<Airport> listeAirports = null;
		for(Country country : listCountries){
			listeAirports = getAirportsForCountry(country);
			if(listeAirports.size() > 0){
				for(Airport airport : listeAirports){
					dto = new AirportRunwaysDto();
					dto.setCountry(country);
					dto.setAirport(airport);
					dto.setRunways(getRunwaysForAirport(airport));
					listeDto.add(dto);
				}
			} else {
				dto = new AirportRunwaysDto();
				dto.setCountry(country);
				dto.setAirport(new Airport());
				dto.setRunways(new HashSet<>());
				listeDto.add(dto);
			}
		}
		log.info("Number of runways to be shown = "+listeDto.size());
		return listeDto;
	}

	public Set<AirportRunwaysDto> getRunwaysForCountries() throws ClassNotFoundException, FileNotFoundException {
		Set<AirportRunwaysDto> listeDto = new HashSet<>();
		countryService.checkCountriesNotNull();
		List<Country> listCountries = DataSource.getCountries();
		checkRunwaysNotNull();
		Map<Long, List<Runway>> mapRunwaysPerAirport = runwayDao.getRunwayTypesByAirport();

		AirportRunwaysDto dto = null;
		Set<Airport> listeAirports = null;
		Set<Runway> listeRunways = null;
		for(Country country : listCountries){
			listeRunways = new HashSet<>();
			listeAirports = getAirportsForCountry(country);
			dto = new AirportRunwaysDto();
			dto.setCountry(country);			
			for(Airport airport : listeAirports){
				if(mapRunwaysPerAirport.get(airport.getId()) != null){
					listeRunways.addAll(mapRunwaysPerAirport.get(airport.getId()));
				}
			}		

			dto.setRunways(getDistinctSurfaces(listeRunways));
			listeDto.add(dto);
		}
		log.info("Number of runways to be shown = "+listeDto.size());
		return listeDto;
	}

	private Set<Runway> getDistinctSurfaces(Set<Runway> listeRunways){
		return listeRunways.stream()
				.filter(p -> !p.getSurface().isEmpty())
				.collect(Collectors.toCollection(
						() -> new TreeSet<Runway>((p1, p2) -> p1.getSurface().compareTo(p2.getSurface())) 
						));
	}

	public void checkAirportsNotNull() throws ClassNotFoundException, FileNotFoundException{
		if(DataSource.getAirports() == null){
			createAllAirports();
		}
	}

	public void checkRunwaysNotNull() throws ClassNotFoundException, FileNotFoundException{
		if(DataSource.getRunways() == null){
			createAllRunways();
		}
	}


}
