package com.lunatech.testing.ad.service;

import java.util.Arrays;
import java.util.List;

public class CsvPropertiesInUseConst {
		private static List<String> columnsCountry;
		private static List<String> columnsAirport;
		private static List<String> columnsRunway;
		
		public static List<String> getColumnsCountry() {
			return Arrays.asList("id","code","name","continent","wikipedia_link","keywords");
		}

		public static List<String> getColumnsAirport() {
			return Arrays.asList("id","ident","type","name","latitude_deg","longitude_deg","elevation_ft","continent","iso_country","iso_region","municipality","scheduled_service","gps_code","iata_code","local_code","home_link","wikipedia_link","keywords");
		}

		public static List<String> getColumnsRunway() {
			return Arrays.asList("id","airport_ref","airport_ident","length_ft","width_ft","surface","lighted","closed","le_ident","le_latitude_deg","le_longitude_deg","le_elevation_ft","le_heading_degT","le_displaced_threshold_ft","he_ident","he_latitude_deg","he_longitude_deg","he_elevation_ft","he_heading_degT","he_displaced_threshold_ft");
		}
		
}
