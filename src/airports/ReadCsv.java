package airports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList;  
  
public class ReadCsv {  
 public static ArrayList<Country> readCsv(String s) {
  String csvFile = new java.io.File("").getAbsolutePath();
  csvFile = csvFile + File.separator + "resources" + File.separator + s + ".csv";  
  
  BufferedReader br = null;  
  String line = "";  
  String splitBy = ",";  
  
  ArrayList<Country> countries = new ArrayList<>();
  
  try {  
  
   br = new BufferedReader(new FileReader(csvFile));
   br.readLine();
   
   while ((line = br.readLine()) != null) {  
	   String[] columns = line.split(splitBy);
	   Country c = new Country();
	   //id	code	name	continent	wikipedia_link	keywords
	   int i = Integer.parseInt(columns[0]);
	   c.setId(i);
	   c.setCode(columns[1].substring(1, columns[1].length()-1));
	   c.setName(columns[2].substring(1, columns[2].length()-1));
	   
	   //AF=1 AN=2 AS=3 EU=4 NA=5 OC=6 SA=7
	   byte bt = 0; 
	   switch (columns[3].substring(1, columns[3].length()-1)) {
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
           throw new IllegalArgumentException("Invalid continent: " + columns[3]);
	   }
	   c.setContinent(bt);
	   c.setWikipedia_link(columns[4].substring(1, columns[4].length()-1));
	   if (columns.length == 6)
			   c.setKeywords(columns[5].substring(1, columns[5].length()-1));
	   
	   countries.add(c);
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
  
  return countries;  
 } 
 /*
 public static void main(String[] args) {
	 readCsv("countries");	 
 }
 */
}  
 