package view.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CitiesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6805755173487345412L;
	private int rowCount;
	private int columnCount;
	List<String[]> rowList;
	
	public CitiesTableModel(int rows, JSONArray cities) {
		rowCount = rows;
		columnCount = 10;
		rowList = new ArrayList<String[]>();
		
		
		if (cities.size() == 0) {
			System.out.println("No cities found, please try adjusting the search requirements!");
		}
		else {
			
			rowCount = cities.size();
			
			for (int x = 0; x < cities.size(); x++) {
				
				JSONObject city = (JSONObject)cities.get(x);
				
				String geonameId = "", name = "", countryName = "", adminName1 = "", adminName2 = "", adminName3 = "", population = "", lat = "", lng = "";
				
				if (city.get("geonameId") == null) {
					
				}
				else {
					geonameId = city.get("geonameId").toString();
				}
				if (city.get("name") == null) {
					
				}
				else {
					name = city.get("name").toString();
				}
				if (city.get("countryName") == null) {
					
				}
				else {
					countryName = city.get("countryName").toString();
				}
				if (city.get("adminName1") == null) {
					
				}
				else {
					adminName1 = city.get("adminName1").toString();
				}
				if (city.get("adminName2") == null) {
					
				}
				else {
					adminName2 = city.get("adminName2").toString();
				}
				if (city.get("adminName3") == null) {
					
				}
				else {
					adminName3 = city.get("adminName3").toString();
				}
				if (city.get("population") == null) {
					
				}
				else {
					population = city.get("population").toString();
				}
				if (city.get("lat") == null) {
					
				}
				else {
					lat = city.get("lat").toString();
				}
				if (city.get("lng") == null) {
					
				}
				else {
					lng = city.get("lng").toString();
				}
				
				String[] cArray = { String.valueOf(x + 1), geonameId, name, countryName, adminName1, adminName2, adminName3, population, lat, lng };
				rowList.add(cArray);
			}	
		}
	}
	
	/* ************* GETTERS *********** */
	public int getRowCount() { 
		return rowCount; 
	}
    public int getColumnCount() { 
    	return columnCount;
    }
    
    public String getValueAt(int row, int col) { 
    	if (rowList.size() > 0) {
    		if (row <= rowList.size()) {
    			if (col < rowList.get(row).length) {
    				return rowList.get(row)[col];
    			}
    		}
    	}
    	return "null";
    }
    
    @Override
    public String getColumnName(int index) {
    	
    	String[] city = {"#", "City ID", "City Name", "Country Name", "Subdivision 1", "Subdivision 2", "Subdivision 3", "Population", "Latitude", "Longitude"};
    	
    	return city[index];
    	
    }
    
    /* ************** SETTERS ********** */
    public void setRowCount(int newRowCount) {
    	rowCount = newRowCount;
    }
    
    public void setColumnCount(int newColumnCount) {
    	columnCount = newColumnCount;
    }
}
