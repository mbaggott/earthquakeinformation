package view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.RangeComboController;

public class CitiesContentPaneSelected extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1512637428637192142L;

	private EarthquakePanes earthquakePanes;
	
	private JLabel headerLabel;
	private JLabel boundingBoxLabel;
	private JLabel boundingBoxNorth;
	private JLabel boundingBoxSouth;
	private JLabel boundingBoxEast;
	private JLabel boundingBoxWest;
	private JLabel name;
	private JLabel country;
	private JLabel longitude;
	private JLabel latitude;
	private JLabel boundingBoxRange;
	private JLabel date;
	private JLabel minMagnitude;
	private JLabel numResults;
	
	private JTextField boundingBoxNorthText;
	private JTextField boundingBoxSouthText;
	private JTextField boundingBoxEastText;
	private JTextField boundingBoxWestText;
	private JTextField nameText;
	private JTextField countryText;
	private JTextField longitudeText;
	private JTextField latitudeText;
	private JComboBox<String> boundingBoxRangeCombo;
	private JTextField dateText;
	private JTextField minMagnitudeText;
	private JTextField numResultsText;
	
	public CitiesContentPaneSelected(EarthquakePanes earthquakePanes) {
		
		this.earthquakePanes = earthquakePanes;
		
		displayContent();
		configureContent();
	}
	
public void displayContent() {
		
		this.setLayout(new GridLayout(1,1));
		
		headerLabel = new JLabel("", JLabel.RIGHT);
		boundingBoxLabel = new JLabel("", JLabel.RIGHT);
		boundingBoxNorth = new JLabel("", JLabel.RIGHT);
		boundingBoxSouth = new JLabel("", JLabel.RIGHT);
		boundingBoxEast = new JLabel("", JLabel.RIGHT);
		boundingBoxWest = new JLabel("", JLabel.RIGHT);
		name = new JLabel("", JLabel.RIGHT);
		country = new JLabel("", JLabel.RIGHT);
		longitude = new JLabel("", JLabel.RIGHT);
		latitude = new JLabel("", JLabel.RIGHT);
		boundingBoxRange = new JLabel("", JLabel.RIGHT);
		date = new JLabel("", JLabel.RIGHT);
		minMagnitude = new JLabel("", JLabel.RIGHT);
		numResults = new JLabel("", JLabel.RIGHT);
		
		boundingBoxNorthText = new JTextField(5);
		boundingBoxSouthText = new JTextField(5);
		boundingBoxEastText = new JTextField(5);
		boundingBoxWestText = new JTextField(5);
		nameText = new JTextField(10);
		countryText = new JTextField(5);
		longitudeText = new JTextField(5);
		latitudeText = new JTextField(5);
		dateText = new JTextField(5);
		minMagnitudeText = new JTextField(5);
		numResultsText = new JTextField(5);
		
		String[] rangeStrings = {"10", "100", "500", "1000", "5000", "10000"};
		boundingBoxRangeCombo = new JComboBox<String>(rangeStrings);
		boundingBoxRangeCombo.setSelectedIndex(0);
		boundingBoxRangeCombo.setEnabled(false);
		boundingBoxRangeCombo.addItemListener(new RangeComboController(this));
		
		this.setVisible(true);
	
	}

	public void configureContent() {
	
		headerLabel.setText("Search Location"); 
		boundingBoxLabel.setText("Bounding Box");
		boundingBoxNorth.setText("North Border");
		boundingBoxSouth.setText("South Border");
		boundingBoxEast.setText("East Border");
		boundingBoxWest.setText("West Border");
		name.setText("Location Name");
		country.setText("Country");
		longitude.setText("Longitude");
		latitude.setText("Latitude");
		boundingBoxRange.setText("Range From City Centre (km)");
		date.setText("Date");
		minMagnitude.setText("Minimum Magnitude");
		numResults.setText("Number of Results");
		
		boundingBoxNorthText.setEditable(false);
		boundingBoxSouthText.setEditable(false);
		boundingBoxEastText.setEditable(false);
		boundingBoxWestText.setEditable(false);
		nameText.setEditable(false);
		countryText.setEditable(false);
		longitudeText.setEditable(false);
		latitudeText.setEditable(false);
		dateText.setEditable(false);
		minMagnitudeText.setEditable(false);
		numResultsText.setEditable(false);
		
		JPanel panel = new JPanel();
	    //panel.setBorder(BorderFactory.createLineBorder(new Color(256)));
	    GridBagLayout layout = new GridBagLayout();
	    panel.setLayout(layout);
	    GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	  
	    panel.add(headerLabel, gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	 
	    panel.add(boundingBoxLabel, gbc);
	
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    panel.add(boundingBoxNorth, gbc);
	
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 2;    
	    panel.add(boundingBoxNorthText, gbc);
	
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 3;  
	    panel.add(boundingBoxSouth, gbc);
	    
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 3;  
	    panel.add(boundingBoxSouthText, gbc);
	  
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    panel.add(boundingBoxEast, gbc);
	
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 4;       
	    panel.add(boundingBoxEastText, gbc);
	
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 5;  
	    panel.add(boundingBoxWest, gbc);
	    
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 5;  
	    panel.add(boundingBoxWestText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 6;       
	    panel.add(name, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 6;       
	    panel.add(nameText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 7;       
	    panel.add(country, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 7;       
	    panel.add(countryText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 8;       
	    panel.add(latitude, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 8;       
	    panel.add(latitudeText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 9;       
	    panel.add(longitude, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 9;       
	    panel.add(longitudeText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 10;       
	    panel.add(boundingBoxRange, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 10;       
	    panel.add(boundingBoxRangeCombo, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 11;       
	    panel.add(date, gbc);
	    
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 11;       
	    panel.add(dateText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 12;       
	    panel.add(minMagnitude, gbc);
	    
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 12;       
	    panel.add(minMagnitudeText, gbc);
	   
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 13;       
	    panel.add(numResults, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 13;       
	    panel.add(numResultsText, gbc);
	
	    this.add(panel);
	
	}
	
	/* ********* GETTERS *********** */
	
	public EarthquakePanes getEarthquakePanes() {
		return earthquakePanes;
	}
	
	public JTextField getBoundingBoxNorthText() {
		return boundingBoxNorthText;
	}
	
	public JTextField getBoundingBoxSouthText() {
		return boundingBoxSouthText;
	}
	
	public JTextField getBoundingBoxEastText() {
		return boundingBoxEastText;
	}
	
	public JTextField getBoundingBoxWestText() {
		return boundingBoxWestText;
	}
	
	public String getBBN() {
		return boundingBoxNorthText.getText();
	}
	
	public String getBBS() {
		return boundingBoxSouthText.getText();
	}
	
	public String getBBE() {
		return boundingBoxEastText.getText();
	}
	
	public String getBBW() {
		return boundingBoxWestText.getText();
	}
	
	public JTextField getNameText() {
		return nameText;
	}
	
	public JTextField getCountryText() {
		return countryText;
	}
	
	public JTextField getLongitudeText() {
		return longitudeText;
	}
	
	public JTextField getLatitudeText() {
		return latitudeText;
	}
	
	public JComboBox<String> getBoundingBoxRangeCombo() {
		return boundingBoxRangeCombo;
	}
	
	public String getDate() {
		return dateText.getText();
	}
	
	public JTextField getDateText() {
		return dateText;
	}
	
	public String getMinMagnitude() {
		return minMagnitudeText.getText();
	}
	
	public JTextField getMinMagnitudeText() {
		return minMagnitudeText;
	}
	
	public String getNumResults() {
		return numResultsText.getText();
	}
	
	public JTextField getNumResultsText() {
		return numResultsText;
	}
	
}

