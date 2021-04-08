package view.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EarthquakeTableModel extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6520026651798508958L;
	private int rowCount;
	private int columnCount;
	List<Object> rowList;
	boolean selectedCity;
	private TabbedPane tabbedPane;
	
	public EarthquakeTableModel(TabbedPane tabbedPane, int rows, JSONArray earthquakes, boolean selectedCity) {
		
		this.tabbedPane = tabbedPane;
		rowCount = rows;
		this.selectedCity = selectedCity;		
		
		if (selectedCity == true) {
			columnCount = 8;
		}
		else {
			columnCount = 7;
		}
		
		rowList = new ArrayList<Object>();
		
		if (earthquakes.size() == 0) {
			System.out.println("No earthquakes found, please try adjusting the search requirements!");
		}
		else {
			
			rowCount = earthquakes.size();
			
			for (int x = 0; x < earthquakes.size(); x++) {
				
				JSONObject earthquake = (JSONObject)earthquakes.get(x);
						
				if (selectedCity == true) {
					EarthquakeTableRowCity eArrayCity = new EarthquakeTableRowCity(x + 1, earthquake.get("eqid").toString(), earthquake.get("datetime").toString(),
							Double.parseDouble(earthquake.get("depth").toString()), Double.parseDouble(earthquake.get("magnitude").toString()), 
							Double.parseDouble(earthquake.get("lat").toString()), Double.parseDouble(earthquake.get("lng").toString()),
							calculateDistance(earthquake.get("lat").toString(), earthquake.get("lng").toString())
							); 
					rowList.add(eArrayCity);
				}
				else {
					EarthquakeTableRow eArray = new EarthquakeTableRow(x + 1, earthquake.get("eqid").toString(), earthquake.get("datetime").toString(),
							Double.parseDouble(earthquake.get("depth").toString()), Double.parseDouble(earthquake.get("magnitude").toString()), 
							Double.parseDouble(earthquake.get("lat").toString()), Double.parseDouble(earthquake.get("lng").toString())); 
					rowList.add(eArray);
				}
				
				
			}	
		}
	}
	
	private double calculateDistance(String toLatString, String toLngString) {
		
		String fromLatString = tabbedPane.getEarthquakePanes().getCitiesContentPaneSelected().getLatitudeText().getText();
		String fromLngString = tabbedPane.getEarthquakePanes().getCitiesContentPaneSelected().getLongitudeText().getText();
		double distance = 0.0;
		double toLat = 0.0, toLng = 0.0, fromLat = 0.0, fromLng = 0.0;
		double radius = 6371.0;
		
		try {
			toLat = Double.parseDouble(toLatString);
			toLng = Double.parseDouble(toLngString);
			fromLat = Double.parseDouble(fromLatString);
			fromLng = Double.parseDouble(fromLngString);
		}
		catch (Exception e) {
			System.out.println("Error converting latitudes/longitudes to double in EarthquakeTableModel distance function");
		}
		
		double dLat = Math.toRadians(toLat - fromLat);
		double dLon = Math.toRadians(toLng - fromLng);
		double fromLatRad = Math.toRadians(fromLat);
		double toLatRad = Math.toRadians(toLat);
		
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				   Math.cos(fromLatRad) * Math.cos(toLatRad) *
				   Math.sin(dLon/2) * Math.sin(dLon/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		distance = radius * c;
		
		return distance;
		
	}
	
	/* ************* GETTERS *********** */
	public int getRowCount() { 
		return rowCount; 
	}
    public int getColumnCount() { 
    	return columnCount;
    }
    
    public Object getValueAt(int row, int col) { 
    	if (rowList.size() > 0) {
    		if (row <= rowList.size()) {
    			if (selectedCity == true) {
    			
    				EarthquakeTableRowCity rowCity = (EarthquakeTableRowCity)rowList.get(row);
    				if (col < rowCity.size()) {
	    				return rowCity.get(col);
	    			}
    			}
    			else {
    				EarthquakeTableRow rowPlain = (EarthquakeTableRow)rowList.get(row);
    				if (col < rowPlain.size()) {
	    				return rowPlain.get(col);
	    			}
    			}
    		}
    	}
    	return "null";
    }
    
    @Override
    public String getColumnName(int index) {
    	
    	ArrayList<String> earthquake = new ArrayList<String>(Arrays.asList("#", "Earthquake ID", "Date/Time", "Depth", "Magnitude", "Latitude", "Longitude" )); 	
    	
    	if (selectedCity == true) {
    		earthquake.add("Distance from City");
    	}
    	
    	return earthquake.get(index);
    	
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch (columnIndex) {
    	case 0:
    		return Integer.class;
    	case 3:
    	case 4:
    	case 5:
    	case 6:
    	case 7:
    		return Double.class;
    	default: 
    		return String.class;
    	}
    		
    }
    
    /* ************** SETTERS ********** */
    public void setRowCount(int newRowCount) {
    	rowCount = newRowCount;
    }
    
    public void setColumnCount(int newColumnCount) {
    	columnCount = newColumnCount;
    }
    
}
