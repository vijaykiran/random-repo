package airports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;  
  
public class ReadCsv3 {  
 public static Map<Integer, LinkedList<Runway>> readCsv(String s) {
  String csvFile = new java.io.File("").getAbsolutePath();
  csvFile = csvFile + File.separator + "resources" + File.separator + s + ".csv";  
  
  BufferedReader br = null;  
  String line = "";
  int i;
  float f;
  short sho;
  boolean bool;
    
  Map<Integer, LinkedList<Runway>> airportRunways = new HashMap<Integer,LinkedList<Runway>>();
  LinkedList<Runway> runways = new LinkedList<>();
    
  try {  
  
   br = new BufferedReader(new FileReader(csvFile));
   br.readLine();
   
   while ((line = br.readLine()) != null) {	   
	   String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");	   
	     
	   Runway r = new Runway();
	   //int id;  
	   i = Integer.parseInt(columns[0]);
	   r.setId(i);
	   
	   //int airport_ref; FK
	   int airport_ref = Integer.parseInt(columns[1]);
	   r.setAirport_ref(airport_ref);
	   
	   //String airport_ident;
	   r.setAirport_ident(columns[2].substring(1, columns[2].length()-1));
	   
	   //int length_ft; 
	   if (columns.length > 3)
		   if (!columns[3].equals(""))
			   if (columns[3].indexOf('"') != -1) {
				   i = Integer.parseInt(columns[3]);
				   r.setAirport_ref(i);				   
			   }

	   //short width_ft;
	   if (columns.length > 4)
		   if (!columns[4].equals(""))
			   if (columns[4].indexOf('"') != -1) {
				   sho = Short.parseShort(columns[4]);
				   r.setAirport_ref(sho);
			   }
	   
	   //String surface;
	   if (columns.length > 5)
		   if (!columns[5].equals(""))
			   if (columns[5].indexOf('"') != -1)
				   r.setSurface(columns[5].substring(1, columns[5].length()-1));
			   else 
				   r.setSurface(columns[5]);	   
	   
	   //boolean lighted;	  
	   if (columns.length > 6)
		   if (!columns[6].equals(""))
			   if (columns[6].indexOf('"') != -1) {
				   bool = Boolean.parseBoolean(columns[6]);
				   if (bool)
					   r.setLighted(true);
				   else 
					   r.setLighted(false);
			   }

	   //boolean closed;
	   if (columns.length > 7)
		   if (!columns[7].equals(""))
			   if (columns[7].indexOf('"') != -1) {
				   bool = Boolean.parseBoolean(columns[7]);
				   if (bool)
					   r.setClosed(true);
				   else 
					   r.setClosed(false);
			   }

	   //String le_ident;	
	   if (columns.length > 8)
		   if (!columns[8].equals(""))
			   if (columns[8].indexOf('"') != -1) 
				   r.setLe_ident(columns[8].substring(1, columns[8].length()-1));
			   else
				   r.setLe_ident(columns[8]);
	   
	   //float le_latitude_deg;
	   if (columns.length > 9)
		   if (!columns[9].equals(""))
			   if (columns[9].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[9]);
				   r.setLe_latitude_deg(f);
			   }
				   
	   //float le_longitude_deg;
	   if (columns.length > 10)
		   if (!columns[10].equals(""))
			   if (columns[10].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[10]);
				   r.setLe_longitude_deg(f);
			   }
	   
	   //int le_elevation_ft;
	   if (columns.length > 11)
		   if (!columns[11].equals(""))
			   if (columns[11].indexOf('"') != -1) {
				   i = Integer.parseInt(columns[11]);
				   r.setLe_elevation_ft(i);
			   }
	   
	   //float le_heading_degT;
	   if (columns.length > 12)
		   if (!columns[12].equals(""))
			   if (columns[12].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[12]);
				   r.setLe_heading_degT(f);
			   }
	   
	   //int le_displaced_threshold_ft;
	   if (columns.length > 13)
		   if (!columns[13].equals(""))
			   if (columns[13].indexOf('"') != -1) {
				   i = Integer.parseInt(columns[13]);
				   r.setLe_displaced_threshold_ft(i);
			   }
	   
	   //String he_ident;
	   if (columns.length > 14)
		   if (!columns[14].equals(""))
			   if (columns[14].indexOf('"') != -1) 
				   r.setHe_ident(columns[14].substring(1, columns[14].length()-1));
			   else
				   r.setHe_ident(columns[14]);
				   
	   //float he_latitude_deg;	  
	   if (columns.length > 15)
		   if (!columns[15].equals(""))
			   if (columns[15].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[15]);
				   r.setHe_latitude_deg(f);
			   }

	   //float he_longitude_deg;	
	   if (columns.length > 16)
		   if (!columns[16].equals(""))
			   if (columns[16].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[16]);
				   r.setHe_longitude_deg(f);
			   }
	   
	   //short he_elevation_ft;
	   if (columns.length > 17)
		   if (!columns[17].equals(""))
			   if (columns[17].indexOf('"') != -1) {
				   sho = Short.parseShort(columns[17]);
				   r.setHe_elevation_ft(sho);
			   }
	   
	   //float he_heading_degT;
	   if (columns.length > 18)
		   if (!columns[18].equals(""))
			   if (columns[18].indexOf('"') != -1) {
				   f = Float.parseFloat(columns[18]);
				   r.setHe_heading_degT(f);
			   }
	   
	   //short he_displaced_threshold_ft;
	   if (columns.length > 19)
		   if (!columns[19].equals(""))
			   if (columns[19].indexOf('"') != -1) {
				   sho = Short.parseShort(columns[19]);
				   r.setHe_displaced_threshold_ft(sho);
			   }
	   
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
  return airportRunways;
 } 
 /*
 public static void main(String[] args) {
	 readCsv("runways");	  
 }
 */
}  
