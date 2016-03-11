package airports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;  
  
public class ReadCsv2 {  
//public static Map<Integer, LinkedList<Runway>> readCsv(String s) {
 public static Map<String, LinkedList<Map<Integer, LinkedList<Runway>>>> readCsv(String s) {
  String csvFile = new java.io.File("").getAbsolutePath();
  csvFile = csvFile + File.separator + "resources" + File.separator + s + ".csv";  
  
  BufferedReader br = null;  
  String line = "";  
  
  Map<Integer, LinkedList<Runway>> airportRunways = new HashMap<Integer,LinkedList<Runway>>();
  //LinkedList<Runway> runways = new LinkedList<>();
  Map<String, LinkedList<Map<Integer, LinkedList<Runway>>>> countriesAirports = new HashMap<String, LinkedList<Map<Integer, LinkedList<Runway>>>>();
  LinkedList<Map<Integer, LinkedList<Runway>>> airports = new LinkedList<Map<Integer, LinkedList<Runway>>>();
  
  try {  
   br = new BufferedReader(new FileReader(csvFile));
   br.readLine();
   
   while ((line = br.readLine()) != null) {	   
	   String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");   
	   Airport a = new Airport();
	   
	   //id	ident	type	name	latitude_deg	longitude_deg	elevation_ft	continent	iso_country	iso_region	municipality	scheduled_service	gps_code	iata_code	local_code	home_link	wikipedia_link	keywords
	   int i = Integer.parseInt(columns[0]);
	   a.setId(i);
	   a.setIdent(columns[1].substring(1, columns[1].length()-1));
	   a.setType(columns[2].substring(1, columns[2].length()-1));
	   a.setName(columns[3].substring(1, columns[3].length()-1));
	   float f = Float.parseFloat(columns[4]);
	   a.setLatitude_deg(f);
	   f = Float.parseFloat(columns[5]);
	   a.setLongitude_deg(f);
	   
	   //has blanks
	   if (!columns[6].equals("")) {
		   i = Integer.parseInt(columns[6]);
		   a.setElevation_ft(i);		   
	   }
	   
	   byte bt = 0; 
	   String str = columns[7].substring(1, columns[7].length()-1); 
	   switch (str) {
	   case "AF":
           bt = 1;
           break;
	   case "AN":
		   bt = 2;
           break;
	   case "AS":
		   bt = 3;
           break;
	   case "EU":
		   bt = 4;
           break;
	   case "NA":
		   bt = 5;
           break;
	   case "OC":
		   bt = 6;
           break;
	   case "SA":
		   bt = 7;
           break;       
       default:
           throw new IllegalArgumentException("Invalid continent: " + columns[7]);
	   }
	   a.setContinent(bt);
	   
	   //8-String iso_country; FK
	   String iso_country = columns[8].substring(1, columns[8].length()-1);
	   a.setIso_country(iso_country);

	   //9-String iso_region;
	   a.setIso_region(columns[9].substring(1, columns[9].length()-1));

	   //10-String municipality;has blanks
	   if (!columns[10].equals(""))
		   a.setMunicipality(columns[10].substring(1, columns[10].length()-1));
	   
	   //11-boolean scheduled_service;
	   str = columns[11].substring(1, columns[11].length()-1); 
	   if (str.equals("yes"))
		   a.setScheduled_service(true);
	   else 
		   a.setScheduled_service(false);
	      
	   //12-String gps_code;has blanks	   
	   if (columns.length > 12)
		   if (!columns[12].equals(""))
			   if (columns[12].indexOf('"') != -1)
				   a.setGps_code(columns[12].substring(1, columns[12].length()-1));
			   else
				   a.setGps_code(columns[12]);
	   
	   //13-String iata_code;has blanks
	   if (columns.length > 13)
		   if (!columns[13].equals(""))
			   if (columns[13].indexOf('"') != -1)
				   a.setIata_code(columns[13].substring(1, columns[13].length()-1));
			   else
				   a.setIata_code(columns[13]);
	   
	   //14-String local_code;has blanks
	   if (columns.length > 14)
		   if (!columns[14].equals(""))
			   if (columns[12].indexOf('"') != -1)
				   a.setLocal_code(columns[14].substring(1, columns[14].length()-1));
			   else
				   a.setLocal_code(columns[14]);
	   
	   //15-String home_link;has blanks
	   if (columns.length > 15)
		   if (!columns[15].equals(""))
			   if (columns[12].indexOf('"') != -1)
				   a.setHome_link(columns[15].substring(1, columns[15].length()-1));
			   else
				   a.setHome_link(columns[15]);

	   //16-String wikipedia_link;has blanks
	   if (columns.length > 16)
		   if (!columns[16].equals(""))
			   if (columns[16].indexOf('"') != -1)
				   a.setWikipedia_link(columns[16].substring(1, columns[16].length()-1));
			   else
				   a.setWikipedia_link(columns[16]);
	   
	   //17-String keywords;has blanks
	   if (columns.length > 17)
		   if (!columns[17].equals(""))
			   if (columns[17].indexOf('"') != -1)
				   a.setKeywords(columns[17].substring(1, columns[17].length()-1));  
			   else
				   a.setKeywords(columns[17]);
	   
	   //Map<String, Map<Integer, LinkedList<Runway>>> countriesAirports = new HashMap<String, Map<Integer, LinkedList<Runway>>>();
	   //LinkedList<Map<Integer, LinkedList<Runway>>> airports = new LinkedList<Map<Integer, LinkedList<Runway>>>();
	   /*
	   if (airportRunways.containsKey(airport_ref)) { 
		   runways = airportRunways.get(airport_ref);
		   runways.addLast(r);
		   airportRunways.put(airport_ref, runways);
	   }
	   else
	   {
		   LinkedList<Runway> runways1 = new LinkedList<>();
		   runways1.addLast(r);
		   airportRunways.put(airport_ref, runways1);
	   }
	   LinkedList<Map<Integer, LinkedList<Runway>>> airports = new LinkedList<Map<Integer, LinkedList<Runway>>>();
	   */
	   
	   if (countriesAirports.containsKey(iso_country)) { 
		   airports = countriesAirports.get(iso_country);
		   /*
		   LinkedList<Runway> runways1 = new LinkedList<>();
		   runways1.addLast(r);
		   airportRunways.put(airport_ref, runways1);
		   */
		   airports.addLast(airportRunways);
		   countriesAirports.put(iso_country, airports);
	   }
	   else
	   {
		   LinkedList<Map<Integer, LinkedList<Runway>>> airports1 = new LinkedList<Map<Integer, LinkedList<Runway>>>();
		   Map<Integer, LinkedList<Runway>> airportRunways1 = new HashMap<Integer,LinkedList<Runway>>();
		   airports1.addLast(airportRunways1);
	   }
   } 
       
  } catch (FileNotFoundException e) {  
   e.printStackTrace();  
  } catch (IOException e) {  
   e.printStackTrace();  
  } finally {  
   if (br != null) {  
    try {  
     br.close();  
    } catch (IOException e) {  
     e.printStackTrace();  
    }  
   }  
  }  
  
  System.out.printf("Done reading %s \n",csvFile);
  
  return countriesAirports;

 } 
}  
