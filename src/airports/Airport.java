package airports;

public class Airport{  
	/*
	id	ident	type	name	latitude_deg	longitude_deg	elevation_ft	continent	iso_country	iso_region	municipality	scheduled_service	gps_code	iata_code	local_code	home_link	wikipedia_link	keywords
	6523	00A	heliport	Total Rf Heliport	40.07080078	-74.93360138	11	NA	US	US-PA	Bensalem	no	00A		00A			
	*/
	
 private int id;  
 private String ident;  
 private String type;  
 private String name;  
 private float latitude_deg;
 private float longitude_deg;
 private int elevation_ft;
 private byte continent;
 private String iso_country;
 private String iso_region;
 private String municipality;
 private boolean scheduled_service;
 private String gps_code;
 private String iata_code;
 private String local_code;
 private String home_link;
 private String wikipedia_link;	
 private String keywords;
 
 /*
 public boolean equals(Object arg) {
	 if(arg == null) return false;
	 if(this == arg) return true;
	 if(arg instanceof Circle) {
	 Circle that = (Circle) arg;	 
	 183
	 if( (this.xPos == that.xPos) && (this.yPos == that.yPos)
	 && (this.radius == that.radius )) {
	 return true;
	 }
	 }
	 return false;
	 }
 */
 
 public int hashCode() {	 
	 return (17*id) ^ (19*elevation_ft) ^ (23*id);
 }
  
 public int getId() {  
	 return id;  
 }
 
 public void setId(int id) {  
	 this.id = id;  
 }

public String getIdent() {
	return ident;
}

public void setIdent(String ident) {
	this.ident = ident;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public float getLatitude_deg() {
	return latitude_deg;
}

public void setLatitude_deg(float latitude_deg) {
	this.latitude_deg = latitude_deg;
}

public float getLongitude_deg() {
	return longitude_deg;
}

public void setLongitude_deg(float longitude_deg) {
	this.longitude_deg = longitude_deg;
}

public int getElevation_ft() {
	return elevation_ft;
}

public void setElevation_ft(int elevation_ft) {
	this.elevation_ft = elevation_ft;
}

public byte getContinent() {
	return continent;
}

public void setContinent(byte continent) {
	this.continent = continent;
}

public String getIso_region() {
	return iso_region;
}

public void setIso_region(String iso_region) {
	this.iso_region = iso_region;
}

public String getIso_country() {
	return iso_country;
}

public void setIso_country(String iso_country) {
	this.iso_country = iso_country;
}

public String getMunicipality() {
	return municipality;
}

public void setMunicipality(String municipality) {
	this.municipality = municipality;
}

public boolean getScheduled_service() {
	return scheduled_service;
}

public void setScheduled_service(boolean scheduled_service) {
	this.scheduled_service = scheduled_service;
}

public String getGps_code() {
	return gps_code;
}

public void setGps_code(String gps_code) {
	this.gps_code = gps_code;
}

public String getIata_code() {
	return iata_code;
}

public void setIata_code(String iata_code) {
	this.iata_code = iata_code;
}

public String getLocal_code() {
	return local_code;
}

public void setLocal_code(String local_code) {
	this.local_code = local_code;
}

public String getWikipedia_link() {
	return wikipedia_link;
}

public void setWikipedia_link(String wikipedia_link) {
	this.wikipedia_link = wikipedia_link;
}

public String getHome_link() {
	return home_link;
}

public void setHome_link(String home_link) {
	this.home_link = home_link;
}

public String getKeywords() {
	return keywords;
}

public void setKeywords(String keywords) {
	this.keywords = keywords;
}
 
}
