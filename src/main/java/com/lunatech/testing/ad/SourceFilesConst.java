package com.lunatech.testing.ad;

import java.util.HashMap;
import java.util.Map;

public class SourceFilesConst {

	public static Map<String, String> sourceFiles = new HashMap<String, String>();

	public static Map<String, String> getSourceFiles() {
		sourceFiles.put("countries","countries.csv");
		sourceFiles.put("airports","airports.csv");
		sourceFiles.put("runways","runways.csv");
		return sourceFiles;
	}
	
}
