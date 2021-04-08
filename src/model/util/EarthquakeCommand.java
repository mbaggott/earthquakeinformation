package model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.parser.JSONParser;

public class EarthquakeCommand {
	
	private String connectionStatus;
	private String apiKey;
	private String apiKeyGSM;
	private double northBoundary;
	private double southBoundary;
	private double eastBoundary;
	private double westBoundary;
	private DateFormat dateFormat;
	private Date date;
	private double minMagnitude;
	private int numResults;
	private EarthquakeFunction ef;
	JSONParser parser;
	
	String mapSize;
	String mapScale;
	String mapZoom;
	String mapCenter;
	String mapCityCenter;
	String mapPath;
	String mapPathColor;
	String mapFillColor;
	String mapMarkers;
	
	/************** CONSTRUCTORS ****************/
	public EarthquakeCommand() {
		ef = new EarthquakeFunction(this);
		apiKey = "michaelbaggottrmit";
		apiKeyGSM = "#################";
		northBoundary = -1000.0;
		southBoundary = -1000.0;
		eastBoundary = -1000.0;
		westBoundary = -1000.0;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		date = new Date();
		minMagnitude = 0.0;
		numResults = 10;
		parser = new JSONParser();
		
		mapSize = "600x400";
		mapScale = "1";
		mapZoom = "";
		mapCenter = "";
		mapCityCenter = "";
		mapPath = "";
		mapPathColor = "blue";
		mapFillColor = "yellow";
		mapMarkers = "";
		
	}
	
	public EarthquakeCommand(String newApiKey) {
		this();
		apiKey = newApiKey;
	}
	
	
	/************** GETTERS ****************/
	
	public String getConnectionStatus() {
		ef.checkOnlineStatus();
		return connectionStatus;
	}
	
	public EarthquakeFunction getEarthquakeFunction() {
		return ef;
	}
	
	public String getAPIKey() {
		return apiKey;
	}
	
	public String getAPIKeyGSM() {
		return apiKeyGSM;
	}
	
	public double getNorthBoundary() {
		return northBoundary;
	}
	
	public double getSouthBoundary() {
		return southBoundary;
	}
	
	public double getEastBoundary() {
		return eastBoundary;
	}
	
	public double getWestBoundary() {
		return westBoundary;
	}
	
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getMinMagnitude() {
		return minMagnitude;
	}
	
	public int getNumResults() {
		return numResults;
	}
	
	public String getMapSize() {
		return mapSize;
	}
	
	public String getMapScale() {
		return mapScale;
	}
	
	public String getMapZoom() {
		return mapZoom;
	}
	
	public String getMapCenter() {
		return mapCenter;
	}
	
	public String getMapCityCenter() {
		return mapCityCenter;
	}
	
	public String getMapMarkers() {
		return mapMarkers;
	}
	
	public String getMapPath() {
		return mapPath;
	}
	
	public String getMapPathColor() {
		return mapPathColor;
	}
	
	/************** SETTERS ****************/
	
	public void setConnectionStatus(String status) {
		connectionStatus = status;
	}
	
	public void setAPIKey(String newKey) {
		this.apiKey = newKey;
	}
	
	public void setNorthBoundary(double newNorthBoundary) {
		northBoundary = newNorthBoundary;
	}
	
	public void setSouthBoundary(double newSouthBoundary) {
		southBoundary = newSouthBoundary;
	}
	
	public void setEastBoundary(double newEastBoundary) {
		eastBoundary = newEastBoundary;
	}
	
	public void setWestBoundary(double newWestBoundary) {
		westBoundary = newWestBoundary;
	}
	
	public boolean setDateFormat(String newDateFormat) {
		
		// Code to validate simple date fomrat supplied
		
		dateFormat = new SimpleDateFormat(newDateFormat);
		
		return true;
	}
	
	public boolean setDate(String newDate) {
		
		try {
			date = this.dateFormat.parse(newDate);
		}
		catch (ParseException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean setMinMagnitude(double newMinMagnitude) {
		
		if (newMinMagnitude > 0.0 && newMinMagnitude < 20.0) {
			minMagnitude = newMinMagnitude;
			return true;
		}
		
		return false;
	
	}
	
	public void setNumResults(int newNumResults) {
		numResults = newNumResults;
	}
	
	public void setMapCenter(double lat, double lng) {
		mapCenter = String.format("%4f", lat) + "," + String.format("%4f", lng);
	}
	
	public void setMapCityCenter(String mapCityCenter) {
		this.mapCityCenter = mapCityCenter;
	}
	
	public void setMapMarkers(String mapMarkers) {
		this.mapMarkers = mapMarkers; 
	}
	
	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
	
	public void setMapZoom(int zoom) {
		
		if (zoom == 0) {
			this.mapZoom = "";
		}
		else {
			this.mapZoom = String.valueOf(zoom);
		}
	}
		
}