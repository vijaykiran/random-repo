package airports;

public class Country {  
	/*
	 * 	id	code	name	continent	wikipedia_link	keywords
		302672	AD	Andorra	EU	http://en.wikipedia.org/wiki/Andorra	
	 */
 private int id;  
 private String code;  
 private String name;
 //AF=1 AN=2 AS=3 EU=4 NA=5 OC=6 SA=7
 private byte continent;  
 private String wikipedia_link;
 private String keywords;
  
 public int getId() {  
	 return id;  
 }
 
 public void setId(int id) {  
	 this.id = id;  
 }  
  
 public String getCode() {  
	 return code;  
 }  
  
 public void setCode(String code) {  
	 this.code = code;  
 }  
  
 public String getName() {  
	 return name;  
 }  
  
 public void setName(String name) {  
	 this.name = name;  
 }  
  
 public byte getContinent() {  
	 return continent;  
 }  
  
 public void setContinent(byte continent) {
	 this.continent = continent;  
 }  
  
 public String getWikipedia_link() {  
	 return wikipedia_link;  
 }  
  
 public void setWikipedia_link(String wikipedia_link) {  
	 this.wikipedia_link = wikipedia_link;  
 }
 
 public String getKeywords() {  
	  return keywords;
 }
	  
 public void setKeywords(String keywords) {  
	  this.keywords = keywords;  
 }
  
}  