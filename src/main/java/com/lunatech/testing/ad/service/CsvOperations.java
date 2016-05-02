package com.lunatech.testing.ad.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

@Service
public class CsvOperations {
	
	public final static Logger log = LoggerFactory.getLogger(CsvOperations.class);
	
	public CSVReader getFileForName(String name) throws FileNotFoundException{
		Resource resource = new ClassPathResource("static/"+name);
		File file = null;
		try {
			file = resource.getFile();
		} catch (IOException e) {
			log.info("File "+name+" not found");
			e.printStackTrace();
		}
		CSVReader reader = new CSVReader(new FileReader(file),',', '\"', 1);
		return reader;
	}
	
	public List mapCsvToEntity(String beanName, String [] columns, CSVReader reader) throws ClassNotFoundException{
		
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
	    Class<?> classType = Class.forName(beanName);
		strat.setType(classType);
	    strat.setColumnMapping(columns);

	    CsvToBean csv = new CsvToBean();
	    List list = csv.parse(strat, reader);
	    log.info("Returned list contains "+ list.size()+" elements");
	    return list;
	}

}
