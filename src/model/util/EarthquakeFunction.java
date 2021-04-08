package model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EarthquakeFunction {
	
	private EarthquakeCommand ec;
	private JSONParser parser;
	private ArrayList<String> markersInner;
	private LinkedHashMap<String, ArrayList<String>> markers;
	HttpURLConnection request;
	String APIKeyGeonames;
	String APIKeyGoogleStaticMaps;
	URL url;
	URL mapURL;
	
	
	JSONArray earthquakes;
	
	public EarthquakeFunction(EarthquakeCommand newEc) {
		ec = newEc;
		parser = new JSONParser();
	}
	
	public void checkOnlineStatus() {
	
		try {
			URL URLTest = new URL(String.format("http://api.geonames.org/earthquakesJSON?username=%s&north=0.1&south=0.1&east=0.1&west=0.1", ec.getAPIKey()));
			HttpURLConnection requestTest = (HttpURLConnection) URLTest.openConnection();
			requestTest.connect();
			if (requestTest.getResponseMessage().equals("OK")) {
				ec.setConnectionStatus("Online");
				return;
			}
		}
		catch (Exception e) {
			ec.setConnectionStatus("Offline");
		}
		ec.setConnectionStatus("Offline");
	}
	
	public void connect() {
		try {
			url = new URL(String.format("http://api.geonames.org/earthquakesJSON?username=%s&north=%s&south=%s&east=%s&west=%s&date=%s&minMagnitude=%s&maxRows=%s", ec.getAPIKey(), ec.getNorthBoundary(), ec.getSouthBoundary(), ec.getEastBoundary(), ec.getWestBoundary(), ec.getDateFormat().format(ec.getDate()), ec.getMinMagnitude(), ec.getNumResults())); 
			System.out.println(url);
			request = (HttpURLConnection) url.openConnection();
			request.connect();	
		}
		catch (Exception e) {
			System.out.println("Error building html string: " + e.getMessage());
		}
	}
	
	public void runFunctionailty() {

		try (InputStream stream = (InputStream) request.getContent()){
			File file = new File("resources/jsonoutput.txt");
			OutputStream outputStream = new FileOutputStream(file);
			IOUtils.copy(stream,  outputStream);
			outputStream.close();
			Object obj = parser.parse(new FileReader("resources/jsonoutput.txt"));
			JSONObject jsonObject = (JSONObject)obj;
			
			earthquakes = (JSONArray)jsonObject.get("earthquakes");
		
		}
		catch(Exception e) {
			System.out.println(url);
			System.out.println("Error getting data: " + e.getMessage());
		}
		
		
	
	}
	
	public void buildMap() {
		
		try {
			
			mapURL = new URL(String.format("https://maps.googleapis.com/maps/api/staticmap?key=%s&size=%s&scale=%s&zoom=%s&center=%s&color=%s&path=%s&%s", ec.getAPIKeyGSM(), ec.getMapSize(), ec.getMapScale(), ec.getMapZoom(), ec.getMapCenter(), ec.getMapPathColor(), ec.getMapPath(), ec.getMapMarkers())); 
			System.out.println("Map: " + mapURL);
			System.out.println(mapURL.toString().length());
		}
		catch (Exception e) {
			System.out.println("Error building URL for Google Maps");
		}
	}
	
public void buildMapCityCenter() {
		
		try {
			
			mapURL = new URL(String.format("https://maps.googleapis.com/maps/api/staticmap?key=%s&size=%s&scale=%s&zoom=%s&center=%s&color=%s&path=%s&markers=%s&%s", ec.getAPIKeyGSM(), ec.getMapSize(), ec.getMapScale(),ec.getMapZoom(), ec.getMapCenter(), ec.getMapPathColor(), ec.getMapPath(), ec.getMapCityCenter(),ec.getMapMarkers())); 
			System.out.println("Map: " + mapURL);
			System.out.println(mapURL.toString().length());
		}
		catch (Exception e) {
			System.out.println("Error building URL for Google Maps");
		}
	}
	
	
	public boolean setMapMarkers() {
		
		String mapMarkers = "";
		char labelChar = 'A';
		int count = 0;
		
		markers = new LinkedHashMap<String, ArrayList<String>>();
		
		if (earthquakes.size() > 36) {
			return false;
		}
		
		
		for (int x = 0; x < earthquakes.size(); x++) {
			
			JSONObject earthquake = (JSONObject)earthquakes.get(x);
			
			if (count < 10) {
				mapMarkers += "markers=label:" + count + "|";
				
			}
			else {
				mapMarkers += "markers=label:" + String.valueOf(labelChar) + "|";
			}
			
			mapMarkers += earthquake.get("lat").toString() + "," + earthquake.get("lng").toString();
			
			markersInner = new ArrayList<String>();
			markersInner.add(earthquake.get("lat").toString());
			markersInner.add(earthquake.get("lng").toString());
			if (count < 10) {
				markers.put(String.valueOf(count), markersInner);
			}
			else {
				markers.put(String.valueOf(labelChar), markersInner);
			}
			
			if (x != earthquakes.size() - 1) {
				mapMarkers += "&";
			}
			
			if (count > 9)
			labelChar = (char)(labelChar + 1);
			count++;
		}
		
		ec.setMapMarkers(mapMarkers);
		
		return true;
	}
	
	public void setMapCityCenter(double lat, double lng) {
		
		String mapMarkers = "color:green" + "|" + String.format("%.4f", lat) + "," + String.format("%.4f", lng) + "|";
		
		ec.setMapCityCenter(mapMarkers);
	}
	
	public void setMapPath() {
		
		String path = "";
		
		String northBoundary = String.format("%.4f", ec.getNorthBoundary());
		String southBoundary = String.format("%.4f", ec.getSouthBoundary());
		String eastBoundary = String.format("%.4f", ec.getEastBoundary());
		String westBoundary = String.format("%.4f", ec.getWestBoundary());
	
		path = northBoundary + "," + westBoundary + "|" + northBoundary + "," + eastBoundary + "|" + southBoundary + "," + eastBoundary + "|" + southBoundary + "," + westBoundary + "|" + northBoundary + "," + westBoundary;
		
		ec.setMapPath(path);
		
	}
	
	/* ************ GETTERS ************ */
	public JSONArray getEarthquakes() {
		return earthquakes;
	}
	
	public URL getMapURL() {
		return mapURL;
	}
	
	public LinkedHashMap<String, ArrayList<String>> getMarkers() {
		return markers;
	}
	
}
