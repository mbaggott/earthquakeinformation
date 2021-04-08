package model.facade;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import model.util.EarthquakeCommand;
import model.util.CitiesCommand;

public interface EIModel {

	public EarthquakeCommand getEarthquakeCommand();
	public String getConnectionStatusEQ();
	public String getAPIKeyEQ();
	public double getNorthBoundaryEQ();
	public double getSouthBoundaryEQ();
	public double getEastBoundaryEQ();
	public double getWestBoundaryEQ();
	public DateFormat getDateFormatEQ();
	public Date getDateEQ();
	public double getMinMagnitudeEQ();
	public int getNumResultsEQ();
	public String getMapZoom();
	
	public void setAPIKeyEQ(String newKey);
	public void setNorthBoundaryEQ(double newNorthBoundary);
	public void setSouthBoundaryEQ(double newSouthBoundary);
	public void setEastBoundaryEQ(double newEastBoundary);
	public void setWestBoundaryEQ(double newWestBoundary);
	public boolean setDateFormatEQ(String newDateFormat);
	public boolean setDateEQ(String newDate);
	public boolean setMinMagnitudeEQ(double newMinMagnitude);
	public void setNumResultsEQ(int newNumResults);
	public void setMapZoom(int zoom);
	
	public CitiesCommand getCitiesCommand();
	public String getConnectionStatusCY();
	public String getAPIKeyCY();
	public double getNorthBoundaryCY();
	public double getSouthBoundaryCY();
	public double getEastBoundaryCY();
	public double getWestBoundaryCY();
	public String getNameCY();
	public String getCountryCY();
	public String getContinentCY();
	public int getNumResultsCY();
	
	public void setAPIKeyCY(String newKey);
	public void setNorthBoundaryCY(double newNorthBoundary);
	public void setSouthBoundaryCY(double newSouthBoundary);
	public void setEastBoundaryCY(double newEastBoundary);
	public void setWestBoundaryCY(double newWestBoundary);
	public void setNameCY(String newName);
	public void setCountryCY(String newCountry);
	public void setContinentCY(String newContinent);
	public void setNumResultsCY(int newNumResults);
	
	public void setMapCenter(double lat, double lng);
	public void setMapCityCenter(double lat, double lng);
	public boolean setMapMarkers();
	public void setMapPath();
	public void buildMap();
	public void buildMapCityCenter();
	public URL getMapURL();
	public LinkedHashMap<String, ArrayList<String>> getMarkers();
	
}
