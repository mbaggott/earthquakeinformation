package model.util;

import org.json.simple.parser.JSONParser;

public class CitiesCommand {
	
	private String connectionStatus;
	private String apiKey;
	private double northBoundary;
	private double southBoundary;
	private double eastBoundary;
	private double westBoundary;
	private String name;
	private String country;
	private String continent;
	private int numResults;
	private CitiesFunction cf;
	JSONParser parser;
	
	/************** CONSTRUCTORS ****************/
	public CitiesCommand() {
		cf = new CitiesFunction(this);
		apiKey = "michaelbaggottrmit";
		northBoundary = -1000.0;
		southBoundary = -1000.0;
		eastBoundary = -1000.0;
		westBoundary = -1000.0;
		name = "";
		country = "";
		continent = "";
		numResults = 10;
		parser = new JSONParser();
	}
	
	public CitiesCommand(String newApiKey) {
		this();
		apiKey = newApiKey;
	}
	
	
	/************** GETTERS ****************/
	
	public String getConnectionStatus() {
		//cf.checkOnlineStatus();
		return connectionStatus;
	}
	
	public CitiesFunction getCitiesFunction() {
		return cf;
	}
	
	public String getAPIKey() {
		return apiKey;
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
	
	public String getName() {
		return name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public int getNumResults() {
		return numResults;
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
	
	public void setName(String newName) {
		
		name = newName;
	}
	
	public void setCountry(String newCountry) {
		
		country = newCountry;
	}
	
	public void setContinent(String newContinent) {
		
		continent = newContinent;
	
	}
	
	public void setNumResults(int newNumResults) {
		numResults = newNumResults;
	}
	
		
}