package airports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

public class AppAirports {
	AppAirports() {
      JFrame frame = new JFrame("Airports");
      frame.setSize(350,150);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JLabel label = new JLabel("Please select data files!");
      frame.add(label);
      frame.setVisible(true);
   }
		
	public static void main(String[] args) {	
		//Query Option 
		//will ask the user for the country name or code and print the airports & runways at each airport. 
		//-Bonus points make the test partial/fuzzy. e.g. entering zimb will result in Zimbabwe
		
		//Reports will print the following:
		//-10 countries with highest number of airports (with count) and countries  with lowest number of airports.
		//-Type of runways (as indicated in "surface" column) per country
		//-Bonus: Print the top 10 most common runway latitude (indicated in "le_ident" column)
			 

	    
	    Map<Integer, LinkedList<Runway>> airportsRunways = new HashMap<Integer,LinkedList<Runway>>();
	    airportsRunways = ReadCsv3.readCsv("runways");
		//printing runways
	    /*
	    LinkedList<Runway> runways = airportsRunways.get(27223);
	    int num = 0;
	    while (runways.size() > num) {
	    	System.out.println(runways.get(num).getId());
	    	num++;
	      }
	     */

	    Map<String, LinkedList<Map<Integer, LinkedList<Runway>>>> countriesAirports = new HashMap<String, LinkedList<Map<Integer, LinkedList<Runway>>>>();
	    countriesAirports = ReadCsv2.readCsv("airports");
		//printing airports
	    //LinkedList<Map<Integer, LinkedList<Runway>>> airports = countriesAirports.get("RO");
	    /*
		Iterator<String> keySetIterator2 = countriesAirports.keySet().iterator();
		while(keySetIterator2.hasNext()) {
			String key = keySetIterator2.next();
		    System.out.println("key: " + key + " value: " + countriesAirports.get(key).get(0));
		  }
	    */
		ArrayList<Country> countries = new ArrayList<>();
		countries = ReadCsv.readCsv("countries");	    
	    
		System.out.println("Please type Query, Reports or exit !");
		Scanner input = new Scanner(System.in);
	    boolean wantsExit = false;
	    
	    while (!wantsExit) {
	    	String answer = input.nextLine();
	    	if ("exit".equals(answer)) 
	    		wantsExit = true;
	    	else
	    		if("Query".equals(answer)) {
	    			System.out.println("Query-ing...");
	    			System.out.println("Please insert country");
	    			String qCountry = input.nextLine();
	    		/*	
	    			for (Country c: countries) {
	    				if (qCountry.length() > 2)
	    					qCountry = c.getCode();
	    				if (qCountry.equals(c.getCode())) {
	    					LinkedList<Map<Integer, LinkedList<Runway>>> airports = countriesAirports.get(qCountry);
    						int num = 0;
    						while (airports.size() > num) {
    							int num2 = 0;
    							LinkedList<Runway> runways = airportsRunways.get(num);
    							while (runways.size() > num2) {
	    							System.out.println(airports.get(num).get(num2).getId());
	    							num2++;
    							}
    							num++;
    						}
	    					
//	    					System.out.println(countriesAirports.get(qCountry));
	    				}
	    					
	    						
	    			}
	    		*/	
	    		}
	    		else {
	    			if ("Reports".equals(answer)){
	    				System.out.println("Reporting:");
	    				
	    			}
	    			else
	    			{
	    /*
	    System.out.println("Printing non-standard report");
		//reading countries
		ArrayList<Country> countries = new ArrayList<>();
		countries = ReadCsv.readCsv("countries");
		//printing countries
		for(Country d : countries) {
			  System.out.println(d.getName());
			  }
		
		//reading airports
		Map<String, Airport> airports = new HashMap<String, Airport>();
		airports = ReadCsv2.readCsv("airports");		
		//printing airports
		Airport a = airports.get("RO");
		System.out.println(a.getIso_region());		
		Iterator<String> keySetIterator = airports.keySet().iterator();
		while(keySetIterator.hasNext()){
			String key = keySetIterator.next();
		    System.out.println("key: " + key + " value: " + airports.get(key));
		  }
		System.out.println(airports.size());
		//reading runways
		Map<Integer, Runway> runways = new HashMap<Integer, Runway>();
		runways = ReadCsv3.readCsv("runways");
		//printing runways
		Runway r = runways.get(6524);
		System.out.println(r.getSurface());		
		Iterator<Integer> keySetIterator2 = runways.keySet().iterator();
		while(keySetIterator2.hasNext()){
			Integer key = keySetIterator2.next();
		    System.out.println("key: " + key + " value: " + runways.get(key));
		  }
		System.out.println(runways.size());
		*/
		System.out.println("Try again! Please type Query, Reports or exit !");
	    }
	    }
	    
	}
	
	}
	
}

		/*
      	SwingUtilities.invokeLater(new Runnable() {
         	public void run() {
            new AppAirports();
         	}
      	});
      */