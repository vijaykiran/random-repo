package com.lunatech.testing.ad.entities;

public class Runway extends BaseEntity{
	/**Runways csv contains:
	 * id,"airport_ref","airport_ident","length_ft","width_ft",
	 * "surface","lighted","closed","le_ident","le_latitude_deg",
	 * "le_longitude_deg","le_elevation_ft","le_heading_degT",
	 * "le_displaced_threshold_ft","he_ident","he_latitude_deg",
	 * "he_longitude_deg","he_elevation_ft","he_heading_degT","he_displaced_threshold_ft",
	 */
	
	private Long airport_ref;
	private String surface;
	private String le_ident;
	
	public Long getAirport_ref() {
		return airport_ref;
	}
	public void setAirport_ref(Long airport_ref) {
		this.airport_ref = airport_ref;
	}
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getLe_ident() {
		return le_ident;
	}
	public void setLe_ident(String le_ident) {
		this.le_ident = le_ident;
	}
	@Override
	public String toString() {
		return "Runway [airport_ref=" + airport_ref + ", surface=" + surface + ", le_ident=" + le_ident + ", getId()="
				+ getId() + "]";
	}
	
}
