package com.lunatech.testing.ad.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.lunatech.testing.ad.entities.Airport;
import com.lunatech.testing.ad.entities.Runway;

@Repository
public class RunwayDao {

	public Set<Runway> getRunwaysForAirport(Airport airport) {
		return DataSource.runways
				.stream()
				.filter(run -> run.getAirport_ref().equals(airport.getId()))
				.collect(Collectors.toSet());
	}

	public Map<Long, List<Runway>> getRunwayTypesByAirport() {
		return DataSource.runways
				.stream()
				.collect(Collectors.groupingBy(Runway::getAirport_ref));
				
	}

}
