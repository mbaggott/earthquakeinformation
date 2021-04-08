package model.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CitiesFunction {
	
	private CitiesCommand cc;
	private JSONParser parser;
	HttpURLConnection request;
	URL url;
	JSONArray cities;
	
	public CitiesFunction(CitiesCommand newCc) {
		cc = newCc;
		parser = new JSONParser();
	}
	
	public void checkOnlineStatus() {
		
			try {
				URL URLTest = new URL(String.format("http://api.geonames.org/earthquakesJSON?username=%s&north=0.1&south=0.1&east=0.1&west=0.1", "michaelbaggottrmit"));
				HttpURLConnection requestTest = (HttpURLConnection) URLTest.openConnection();
				requestTest.connect();
				if (requestTest.getResponseMessage().equals("OK")) {
					cc.setConnectionStatus("Online");
					return;
				}
			}
			catch (Exception e) {
				cc.setConnectionStatus("Offline");
			}
			cc.setConnectionStatus("Offline");

	}
	
	public void connect() {
		try {
			url = new URL(String.format("http://api.geonames.org/searchJSON?username=%s&north=%s&south=%s&east=%s&west=%s&name=%s&country=%s&continetCode=%s&maxRows=%s", cc.getAPIKey(), cc.getNorthBoundary(), cc.getSouthBoundary(), cc.getEastBoundary(), cc.getWestBoundary(), cc.getName(), cc.getCountry(), cc.getContinent(), cc.getNumResults())); 
			System.out.println(url);
			request = (HttpURLConnection) url.openConnection();
			request.connect();	
		}
		catch (Exception e) {
			System.out.println("Error building html string: " + e.getMessage());
		}
	}
	
	public void runFunctionailty() {

		InputStream stream;
		
		try {
		
			stream = (InputStream) request.getContent();
			InputStreamReader reader = new InputStreamReader(stream, "utf-8");
			File file = new File("resources/jsonoutput.txt");
			Writer outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			IOUtils.copy(reader,  outputStream);
			outputStream.close();
			Object obj = parser.parse(new InputStreamReader(new FileInputStream("resources/jsonoutput.txt"), "utf-8"));
			JSONObject jsonObject = (JSONObject)obj;
			
			cities = (JSONArray)jsonObject.get("geonames");
		
		}
		catch(Exception e) {
			System.out.println(url);
			System.out.println("Error getting data: " + e.getMessage());
		}
		
		
	
	}
	
	/* ************ GETTERS ************ */
	public JSONArray getCities() {
		return cities;
	}
	
}
