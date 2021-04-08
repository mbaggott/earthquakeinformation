package view.components;

import java.awt.*;
import javax.swing.*;

public class CitiesContentPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4843018778115971181L;
	private JLabel headerLabel;
	private JLabel boundingBoxLabel;
	private JLabel boundingBoxNorth;
	private JLabel boundingBoxSouth;
	private JLabel boundingBoxEast;
	private JLabel boundingBoxWest;
	private JLabel name;
	private JLabel country;
	private JLabel continent;
	private JLabel numResults;
	
	private JTextField boundingBoxNorthText;
	private JTextField boundingBoxSouthText;
	private JTextField boundingBoxEastText;
	private JTextField boundingBoxWestText;
	private JTextField nameText;
	private JTextField countryText;
	private JTextField continentText;
	private JTextField numResultsText;

	public CitiesContentPane() {
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
		continent = new JLabel("", JLabel.RIGHT);
		numResults = new JLabel("", JLabel.RIGHT);
		
		boundingBoxNorthText = new JTextField(5);
		boundingBoxSouthText = new JTextField(5);
		boundingBoxEastText = new JTextField(5);
		boundingBoxWestText = new JTextField(5);
		nameText = new JTextField(10);
		countryText = new JTextField(5);
		continentText = new JTextField(5);
		numResultsText = new JTextField(5);
		
		this.setVisible(true);
	
	}
	
public void configureContent() {
		
		headerLabel.setText("Search Locations"); 
		boundingBoxLabel.setText("Bounding Box");
		boundingBoxNorth.setText("North Border");
		boundingBoxSouth.setText("South Border");
		boundingBoxEast.setText("East Border");
		boundingBoxWest.setText("West Border");
		name.setText("Location Name");
		country.setText("Country Code");
		continent.setText("Continent Code");
		numResults.setText("Number of Results");
	    
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
	    panel.add(continent, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 8;       
	    panel.add(continentText, gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.insets = new Insets(2,2,2,2);
	    gbc.gridx = 0;
	    gbc.gridy = 9;       
	    panel.add(numResults, gbc);
	   
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(2,10,2,2);
	    gbc.gridx = 1;
	    gbc.gridy = 9;       
	    panel.add(numResultsText, gbc);
	
	    this.add(panel);
    
	}

	/* *********** GETTERS *********** */
	
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
	
	public String getName() { 
		return nameText.getText();
	}
	
	public String getCountry() { 
		return countryText.getText();
	}
	
	public String getContinent() { 
		return continentText.getText();
	}
	
	public String getNumResults() { 
		return numResultsText.getText();
	}
	
}
