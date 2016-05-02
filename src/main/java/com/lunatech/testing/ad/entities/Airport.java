package com.lunatech.testing.ad.entities;

public class Airport extends BaseEntity{
	/**Airport csv contains:
	 * id,"ident","type","name","latitude_deg","longitude_deg","elevation_ft",
	 * "continent","iso_country","iso_region","municipality","scheduled_service",
	 * "gps_code","iata_code","local_code","home_link","wikipedia_link","keywords"
	 */
	private String name;
	private String iso_country;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso_country() {
		return iso_country;
	}

	public void setIso_country(String iso_country) {
		this.iso_country = iso_country;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + ", iso_country=" + iso_country + ", getId()=" + getId() + "]";
	}


	
}
