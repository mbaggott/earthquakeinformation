package controller;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.*;
import model.facade.*;
import view.components.*;

public class IconBarController implements ActionListener {
	
	private EIModel model;
    private IconBar iconBar;
    private List<String> statusErrors;
    
    private JTable citiesTable;
    private CitiesContentPaneSelected pane;
	
    private String lat;
    private String lng;
    private String distance;
    private double[] boxArray = { 0.0, 0.0, 0.0, 0.0 };
    private double[] locArray  = { 0.0, 0.0 };
 
    /* *********** CONSTRUCTOR ********** */
    public IconBarController(IconBar iconBar) {
    	this.iconBar = iconBar;
    	this.model = iconBar.getFrame().getModel();
    }
    
    /* ********** SETTERS *********** */
    public void setDistance(String distance) {
    	this.distance = distance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        //Execute switch based on Action Command.
        switch(e.getActionCommand()) {
            
            case "RunSearch":
                
            	if (iconBar.getFrame().getTabbedPane().getSelectedIndex() == 0) {
            		
            		searchEarthquakes();
            		iconBar.getFrame().getTabbedPane().getBrowserPane().hideEarthquakes();
            		
            	}
            	
            	else if (iconBar.getFrame().getTabbedPane().getSelectedIndex() == 1) {
            		
            		searchCities();
            		
            	}
            	break;
                
            case "RunCitiesSearch":
                	
            	if (iconBar.getFrame().getTabbedPane().getCitiesResultsTable().getSelectedRow() != -1) {
            		
            		citiesTable = iconBar.getFrame().getTabbedPane().getCitiesResultsTable();
            		pane = iconBar.getFrame().getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected();
            		
            		lat = citiesTable.getValueAt(citiesTable.getSelectedRow(), 8).toString();
            		lng = citiesTable.getValueAt(citiesTable.getSelectedRow(), 9).toString();
            		distance = (String)pane.getBoundingBoxRangeCombo().getSelectedItem();
            		
            		calculateBoundingBox(boxArray, locArray, lat, lng, distance);
            		enableCCPSTextFields(pane);
            		setCCPSTextFieldValues(citiesTable, pane, lat, lng, boxArray, locArray);
            		
            		iconBar.getFrame().getTabbedPane().setSelectedIndex(0);
            		iconBar.getSearchSelectedCityButton().setEnabled(true);
            		
            		
            		
            	}
            	
            	else {
            		System.out.println("Running else");
            		iconBar.getFrame().getStatusBar().getErrorStatus().setText("Please select a city from the table by clicking on a row");
            	}
            	
            	
            	break;
            	
            case "RunSelectedCitySearch":
            		
            	searchEarthquakesLocation();
            	iconBar.getFrame().getTabbedPane().getBrowserPane().hideEarthquakes();
            	
            	break;
            	
            case "GenerateMap":
            	
            	boolean testMarkers = false;
            	
            	model.setMapZoom(0);
            	iconBar.getFrame().getTabbedPane().getBrowserPane().getZoomLevel().setText("Auto");
            	iconBar.getFrame().getTabbedPane().getBrowserPane().getZoomIn().setEnabled(false);
            	iconBar.getFrame().getTabbedPane().getBrowserPane().getZoomOut().setEnabled(false);
            	
            	if (iconBar.getFrame().getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected().getNameText().getText().length() > 0) {
            		model.setMapCenter(locArray[0], locArray[1]);
            		model.setMapCityCenter(locArray[0], locArray[1]);
            		testMarkers = model.setMapMarkers();
            		model.setMapPath();
            		if (testMarkers == true) {
            			model.buildMapCityCenter();
            		}
            	}
            	else {
            		testMarkers = model.setMapMarkers();
            		model.setMapPath();
            		if (testMarkers == true) {
            			model.buildMap();
            		}
            	}
            	
            	if (testMarkers == true) {
            		iconBar.getFrame().getTabbedPane().getBrowserPane().displayEarthquakes(model.getMapURL());
            		iconBar.getFrame().getTabbedPane().getBrowserPane().displayZoomList(model.getMarkers());
            	}
            	else {
            		iconBar.getFrame().getStatusBar().getErrorStatus().setText("Cannot display more than 36 earthquakes on map, please reduce number of results");
            	}
            	
            	iconBar.getFrame().getTabbedPane().setSelectedIndex(4);
            	
            	break;
            	 
            default:
                break;
         
        }
        
    }
    
    private void enableCCPSTextFields(CitiesContentPaneSelected pane) {
    	
    	pane.getBoundingBoxNorthText().setEnabled(true);
    	pane.getBoundingBoxNorthText().setEditable(false);
    	
    	pane.getBoundingBoxSouthText().setEnabled(true);
    	pane.getBoundingBoxSouthText().setEditable(false);
    	
    	pane.getBoundingBoxEastText().setEnabled(true);
    	pane.getBoundingBoxEastText().setEditable(false);
    	
    	pane.getBoundingBoxWestText().setEnabled(true);
    	pane.getBoundingBoxWestText().setEditable(false);
    	
    	pane.getNameText().setEnabled(true);
    	pane.getNameText().setEditable(false);
    	
    	pane.getCountryText().setEnabled(true);
    	pane.getCountryText().setEditable(false);
    	
    	pane.getLongitudeText().setEnabled(true);
    	pane.getLongitudeText().setEditable(false);
    	
    	pane.getLatitudeText().setEnabled(true);
    	pane.getLatitudeText().setEditable(false);
    	
    	pane.getBoundingBoxRangeCombo().setEnabled(true);
    	
    	pane.getDateText().setEnabled(true);
    	pane.getDateText().setEditable(true);
    	
    	pane.getMinMagnitudeText().setEnabled(true);
    	pane.getMinMagnitudeText().setEditable(true);
    	
    	pane.getNumResultsText().setEnabled(true);
    	pane.getNumResultsText().setEditable(true);
    	
    }
    
    private void setCCPSTextFieldValues(JTable citiesTable, CitiesContentPaneSelected pane, String lat, String lng, double[] boxArray, double[] locArray) {
    
    	String cityName = citiesTable.getValueAt(citiesTable.getSelectedRow(), 2).toString();
		String countryName = citiesTable.getValueAt(citiesTable.getSelectedRow(), 3).toString();
    	
    	pane.getBoundingBoxNorthText().setText(String.format("%.4f", boxArray[0]));
    	pane.getBoundingBoxSouthText().setText(String.format("%.4f", boxArray[1]));
    	pane.getBoundingBoxEastText().setText(String.format("%.4f", boxArray[2]));
    	pane.getBoundingBoxWestText().setText(String.format("%.4f", boxArray[3]));
		
		pane.getNameText().setText(cityName);
    	
    	pane.getCountryText().setText(countryName);
    	
    	pane.getLatitudeText().setText(String.format("%.4f", locArray[0]));
    	
    	pane.getLongitudeText().setText(String.format("%.4f", locArray[1]));
 	
    
    }
    
    /* Function to populate the bounding box array with given latitude, longitude and distance variabels
     * boxArray[0] - North Border (Latitude)
     * boxArray[1] - South Border (Latitude)
     * boxArray[2] - East Border (Longitude)
     * boxArray[3] - West Border (Longitude)
     * locArray[0] - Latitude
     * locArray[1] - Longitude
     */
    
    private void calculateBoundingBox(double[] boxArray, double[] locArray, String lat, String lng, String distance) {
    	
    	double latDouble = 0.0;
    	double lngDouble = 0.0;
    	int distanceDouble = 0;
    	
    	try {
    		latDouble = Double.parseDouble(lat);
    		locArray[0] = latDouble;
    	}
    	catch (Exception e) {
    		System.out.println("Error parsing latitude string to double.");
    	}
    	
    	try {
    		lngDouble = Double.parseDouble(lng);
    		locArray[1] = lngDouble;
    	}
    	catch (Exception e) {
    		System.out.println("Error parsing longitude string to double.");
    	}
    	try {
    		distanceDouble = Integer.parseInt(distance);
    	}
    	catch (Exception e) {
    		System.out.println("Error parsing distance string to integer.");
    	}
    	
    	boxArray[0] = latDouble + (distanceDouble/111.12);
    	boxArray[1] = latDouble - (distanceDouble/111.12);
    	
    	boxArray[2] = lngDouble + (distanceDouble/111.12)/Math.cos(Math.toRadians(latDouble));
    	boxArray[3] = lngDouble - (distanceDouble/111.12)/Math.cos(Math.toRadians(latDouble));
    	
    }
    
    public void setNewBoundingBox() {
    	calculateBoundingBox(boxArray, locArray, lat, lng, distance);
    	setCCPSTextFieldValues(citiesTable, pane, lat, lng, boxArray, locArray);
    }
    
    private void searchEarthquakes() {
    
    	iconBar.getSearchButton().setEnabled(false);
		statusErrors = new ArrayList<String>();
		
		EarthquakeContentPane pane = iconBar.getFrame().getTabbedPane().getEarthquakePanes().getEarthquakeContentPane();
		TabbedPane paneTab = (TabbedPane)iconBar.getFrame().getTabbedPane();
		
		try {
			model.setNorthBoundaryEQ(Double.parseDouble(pane.getBBN()));
		}
		catch (Exception eNB) {
			model.setNorthBoundaryEQ(179.9);
			statusErrors.add("North Border");
		}
		
		try {
			model.setSouthBoundaryEQ(Double.parseDouble(pane.getBBS()));
		}
		catch (Exception eSB) {
			model.setSouthBoundaryEQ(-179.9);
			statusErrors.add("South Border");
		}
		
		try {
			model.setEastBoundaryEQ(Double.parseDouble(pane.getBBE()));
		}
		catch (Exception eEB) {
			model.setEastBoundaryEQ(179.9);
			statusErrors.add("East Border");
		}
		
		try {
			model.setWestBoundaryEQ(Double.parseDouble(pane.getBBW()));
		}
		catch (Exception eWB) {
			model.setWestBoundaryEQ(-179.9);
			statusErrors.add("West Border");
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date date = new Date();
		int year = 0;

		try {
			date = dateFormat.parse(pane.getDate());
			DateFormat yearFormat = new SimpleDateFormat("yyyy");
			year = Integer.parseInt(yearFormat.format(date));
			if (year > 2016) {
				statusErrors.add("Date");
			}
			else{
				model.setDateEQ(pane.getDate());
			}
		}
		
		catch (Exception eDate) {
			statusErrors.add("Date");
			date = new Date();
			model.setDateEQ(date.toString());
		}
		
		try {
			model.setMinMagnitudeEQ(Double.parseDouble(pane.getMinMagnitude()));
		}
		catch (Exception eM) {
			model.setMinMagnitudeEQ(0);
			statusErrors.add("Magnitutde");
		}
		
		try {
			model.setNumResultsEQ(Integer.parseInt(pane.getNumResults()));
		}
		catch (Exception eR) {
			model.setNumResultsEQ(10);
			statusErrors.add("Results");
		}
	
		model.getEarthquakeCommand().getEarthquakeFunction().connect();
		model.getEarthquakeCommand().getEarthquakeFunction().runFunctionailty();
		
		paneTab.setEarthquakes(model.getEarthquakeCommand().getEarthquakeFunction().getEarthquakes());
		paneTab.createTableEarthquake(false);
		iconBar.getFrame().getStatusBar().displayErrors(statusErrors);
		iconBar.getSearchButton().setEnabled(true);
		iconBar.getFrame().getTabbedPane().setSelectedIndex(2);
    }
    
    private void searchEarthquakesLocation() {
    	
    	iconBar.getSearchSelectedCityButton().setEnabled(false);
		statusErrors = new ArrayList<String>();
		
		CitiesContentPaneSelected pane = iconBar.getFrame().getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected();
		TabbedPane paneTab = (TabbedPane)iconBar.getFrame().getTabbedPane();
		
		try {
			model.setNorthBoundaryEQ(Double.parseDouble(pane.getBBN()));
		}
		catch (Exception eNB) {
			model.setNorthBoundaryEQ(179.9);
			statusErrors.add("North Border");
		}
		
		try {
			model.setSouthBoundaryEQ(Double.parseDouble(pane.getBBS()));
		}
		catch (Exception eSB) {
			model.setSouthBoundaryEQ(-179.9);
			statusErrors.add("South Border");
		}
		
		try {
			model.setEastBoundaryEQ(Double.parseDouble(pane.getBBE()));
		}
		catch (Exception eEB) {
			model.setEastBoundaryEQ(179.9);
			statusErrors.add("East Border");
		}
		
		try {
			model.setWestBoundaryEQ(Double.parseDouble(pane.getBBW()));
		}
		catch (Exception eWB) {
			model.setWestBoundaryEQ(-179.9);
			statusErrors.add("West Border");
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date date = new Date();
		int year = 0;

		try {
			date = dateFormat.parse(pane.getDate());
			DateFormat yearFormat = new SimpleDateFormat("yyyy");
			year = Integer.parseInt(yearFormat.format(date));
			if (year > 2016) {
				statusErrors.add("Date");
			}
			else{
				model.setDateEQ(pane.getDate());
			}
		}
		
		catch (Exception eDate) {
			statusErrors.add("Date");
			date = new Date();
			model.setDateEQ(date.toString());
		}
		
		try {
			model.setMinMagnitudeEQ(Double.parseDouble(pane.getMinMagnitude()));
		}
		catch (Exception eM) {
			model.setMinMagnitudeEQ(0);
			statusErrors.add("Magnitutde");
		}
		
		try {
			model.setNumResultsEQ(Integer.parseInt(pane.getNumResults()));
		}
		catch (Exception eR) {
			model.setNumResultsEQ(10);
			statusErrors.add("Results");
		}
	
		model.getEarthquakeCommand().getEarthquakeFunction().connect();
		model.getEarthquakeCommand().getEarthquakeFunction().runFunctionailty();
		
		paneTab.setEarthquakes(model.getEarthquakeCommand().getEarthquakeFunction().getEarthquakes());
		paneTab.createTableEarthquake(true);
		iconBar.getFrame().getStatusBar().displayErrors(statusErrors);
		iconBar.getSearchButton().setEnabled(true);
		iconBar.getFrame().getTabbedPane().setSelectedIndex(2);
		
    }
    
    private void searchCities() {
    	
    	statusErrors = new ArrayList<String>();
		
		iconBar.getSearchButton().setEnabled(false);
		
		CitiesContentPane pane = (CitiesContentPane)iconBar.getFrame().getTabbedPane().getComponentAt(1);
		TabbedPane paneTab = (TabbedPane)iconBar.getFrame().getTabbedPane();
		
		try {
			model.setNorthBoundaryCY(Double.parseDouble(pane.getBBN()));
		}
		catch (Exception eNB) {
			model.setNorthBoundaryCY(179.9);
			statusErrors.add("North Border");
		}
		
		try {
			model.setSouthBoundaryCY(Double.parseDouble(pane.getBBS()));
		}
		catch (Exception eSB) {
			model.setSouthBoundaryCY(-179.9);
			statusErrors.add("South Border");
		}
		
		try {
			model.setEastBoundaryCY(Double.parseDouble(pane.getBBE()));
		}
		catch (Exception eEB) {
			model.setEastBoundaryCY(179.9);
			statusErrors.add("East Border");
		}
		
		try {
			model.setWestBoundaryCY(Double.parseDouble(pane.getBBW()));
		}
		catch (Exception eWB) {
			model.setWestBoundaryCY(-179.9);
			statusErrors.add("West Border");
		}

		try {
			model.setNameCY(pane.getName());
		}
		
		catch (Exception eN) {
			model.setNameCY("");
			statusErrors.add("City Name");
		}
		
		try {
			model.setCountryCY(pane.getCountry());
		}
		catch (Exception eC) {
			model.setCountryCY("");
			statusErrors.add("Country Code");
		}
		
		try {
			model.setContinentCY(pane.getContinent());
		}
		catch (Exception eR) {
			model.setContinentCY("");
			statusErrors.add("ContinentCode");
		}
		
		try {
			model.setNumResultsCY(Integer.parseInt(pane.getNumResults()));
		}
		catch (Exception eR) {
			model.setNumResultsCY(10);
			statusErrors.add("Results");
		}
	
		model.getCitiesCommand().getCitiesFunction().connect();
		model.getCitiesCommand().getCitiesFunction().runFunctionailty();
		
		paneTab.setCities(model.getCitiesCommand().getCitiesFunction().getCities());
		paneTab.createTableCities(); 
		iconBar.getFrame().getStatusBar().displayErrors(statusErrors);
		iconBar.getSearchButton().setEnabled(true);
		iconBar.getFrame().getTabbedPane().setSelectedIndex(3);
    	
    }
    
	
}
