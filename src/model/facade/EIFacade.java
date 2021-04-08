package model.facade;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import model.util.EarthquakeCommand;
import model.util.CitiesCommand;

public class EIFacade implements EIModel {

	private EarthquakeCommand ec;
	private CitiesCommand cc;
	
	public EIFacade() {
		ec =  new EarthquakeCommand();
		cc = new CitiesCommand();
	}
	
/************** GETTERS ****************/
	
	public String getConnectionStatusEQ() {
		return ec.getConnectionStatus();
	}
	
	public String getAPIKeyEQ() {
		return ec.getAPIKey();
	}
	
	public double getNorthBoundaryEQ() {
		return ec.getNorthBoundary();
	}
	
	public double getSouthBoundaryEQ() {
		return ec.getSouthBoundary();
	}
	
	public double getEastBoundaryEQ() {
		return ec.getEastBoundary();
	}
	
	public double getWestBoundaryEQ() {
		return ec.getWestBoundary();
	}
	
	public DateFormat getDateFormatEQ() {
		return ec.getDateFormat();
	}
	
	public Date getDateEQ() {
		return ec.getDate();
	}
	
	public double getMinMagnitudeEQ() {
		return ec.getMinMagnitude();
	}
	
	public int getNumResultsEQ() {
		return ec.getNumResults();
	}
	
	public String getConnectionStatusCY() {
		return cc.getConnectionStatus();
	}
	
	public String getAPIKeyCY() {
		return cc.getAPIKey();
	}
	
	public double getNorthBoundaryCY() {
		return cc.getNorthBoundary();
	}
	
	public double getSouthBoundaryCY() {
		return cc.getSouthBoundary();
	}
	
	public double getEastBoundaryCY() {
		return cc.getEastBoundary();
	}
	
	public double getWestBoundaryCY() {
		return cc.getWestBoundary();
	}
	
	public String getNameCY() {
		return cc.getName();
	}
	
	public String getCountryCY() {
		return cc.getCountry();
	}
	
	public String getContinentCY() {
		return cc.getContinent();
	}
	
	public int getNumResultsCY() {
		return cc.getNumResults();
	}
	
	public String getMapZoom() {
		return ec.getMapZoom();
	}
	
	/************** GETTERS ****************/
	
	public EarthquakeCommand getEarthquakeCommand() {
		return ec;
	}
	
	public void setAPIKeyEQ(String newKey) {
		ec.setAPIKey(newKey);
	}
	
	public void setNorthBoundaryEQ(double newNorthBoundary) {
		ec.setNorthBoundary(newNorthBoundary);
	}
	
	public void setSouthBoundaryEQ(double newSouthBoundary) {
		ec.setSouthBoundary(newSouthBoundary);
	}
	
	public void setEastBoundaryEQ(double newEastBoundary) {
		ec.setEastBoundary(newEastBoundary);
	}
	
	public void setWestBoundaryEQ(double newWestBoundary) {
		ec.setWestBoundary(newWestBoundary);
	}
	
	public boolean setDateFormatEQ(String newDateFormat) {
		
		if (ec.setDateFormat(newDateFormat)) {
			return true;
		}
		return false;
		
	}
	
	public boolean setDateEQ(String newDate) {
		
		if (ec.setDate(newDate)) {
			return true;
		}
		return false;
	}
	
	public boolean setMinMagnitudeEQ(double newMinMagnitude) {
		
		if (ec.setMinMagnitude(newMinMagnitude)) {
			return true;
		}
		return false;
	
	}
	
	public void setNumResultsEQ(int newNumResults) {
		ec.setNumResults(newNumResults);
	}
	
	public CitiesCommand getCitiesCommand() {
		return cc;
	}
	
	public void setAPIKeyCY(String newKey) {
		cc.setAPIKey(newKey);
	}
	
	public void setNorthBoundaryCY(double newNorthBoundary) {
		cc.setNorthBoundary(newNorthBoundary);
	}
	
	public void setSouthBoundaryCY(double newSouthBoundary) {
		cc.setSouthBoundary(newSouthBoundary);
	}
	
	public void setEastBoundaryCY(double newEastBoundary) {
		cc.setEastBoundary(newEastBoundary);
	}
	
	public void setWestBoundaryCY(double newWestBoundary) {
		cc.setWestBoundary(newWestBoundary);
	}
	
	public void setNameCY(String newName) {
		cc.setName(newName);
	}
	
	public void setCountryCY(String newCountry) {
		cc.setCountry(newCountry);
	}
	
	public void setContinentCY(String newContinent) {
		cc.setContinent(newContinent);
	}
	
	public void setNumResultsCY(int newNumResults) {
		cc.setNumResults(newNumResults);
	}
	
	public void setMapCenter(double lat, double lng) {
		ec.setMapCenter(lat,  lng);
	}
	
	public void setMapCityCenter(double lat, double lng) {
		ec.getEarthquakeFunction().setMapCityCenter(lat, lng);
	}
	
	public boolean setMapMarkers() {
		return ec.getEarthquakeFunction().setMapMarkers();
	}
	
	public void buildMap() {
		ec.getEarthquakeFunction().buildMap();
	}
	
	public void buildMapCityCenter() {
		ec.getEarthquakeFunction().buildMapCityCenter();
	}
	
	public void setMapPath() {
		ec.getEarthquakeFunction().setMapPath();
	}
	
	public URL getMapURL() {
		return ec.getEarthquakeFunction().getMapURL();
	}
	
	public void setMapZoom(int zoom) {
		ec.setMapZoom(zoom);
	}
	
	public LinkedHashMap<String, ArrayList<String>> getMarkers() {
		return ec.getEarthquakeFunction().getMarkers();
	}
	
	
}
