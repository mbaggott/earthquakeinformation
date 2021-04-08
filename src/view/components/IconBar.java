package view.components;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import controller.IconBarController;

public class IconBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8132292966530841588L;
	private IconBarController controller;
    private MainFrame frame;
    private JButton search;
    private JButton searchCities;
    private JButton searchSelectedCity;
    private JButton generateMap;
    
    
    /* *********** CONSTRUCTOR ************ */
    public IconBar(MainFrame frame) {
    	
    	this.frame = frame;
    	controller = new IconBarController(this);
    	search = new JButton();
    	searchCities = new JButton();
    	searchSelectedCity = new JButton();
    	generateMap = new JButton();
    	
    	this.setLayout(new FlowLayout());
    	try {
    		
    		InputStream input1 = this.getClass().getClassLoader().getResourceAsStream("searchIcon.png");
    		InputStream input2 = this.getClass().getClassLoader().getResourceAsStream("citiesIcon.png");
    		InputStream input3 = this.getClass().getClassLoader().getResourceAsStream("searchIconGreyed.png");
    		InputStream input4 = this.getClass().getClassLoader().getResourceAsStream("citiesIconGreyed.png");
    		InputStream input5 = this.getClass().getClassLoader().getResourceAsStream("citySearchIcon.png");
    		InputStream input6 = this.getClass().getClassLoader().getResourceAsStream("citySearchIconGreyed.png");
    		InputStream input7 = this.getClass().getClassLoader().getResourceAsStream("mapIcon.png");
    		InputStream input8 = this.getClass().getClassLoader().getResourceAsStream("mapIconGreyed.png");
    		
    		Image searchIcon = ImageIO.read(input1);
    		Image citiesIcon = ImageIO.read(input2);
    		Image citySearchIcon = ImageIO.read(input5);
    		Image searchIconGreyed = ImageIO.read(input3);
    		Image citiesIconGreyed = ImageIO.read(input4);
    		Image citySearchIconGreyed = ImageIO.read(input6);
    		Image mapIcon = ImageIO.read(input7);
    		Image mapIconGreyed = ImageIO.read(input8);
    		
    		Image scaledSearchIcon = searchIcon.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledCitiesIcon = citiesIcon.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledCitySearchIcon = citySearchIcon.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledSearchIconGreyed = searchIconGreyed.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledCitiesIconGreyed = citiesIconGreyed.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledCitySearchIconGreyed = citySearchIconGreyed.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledMapIcon = mapIcon.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		Image scaledMapIconGreyed = mapIconGreyed.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    		
    		search.setIcon(new ImageIcon(scaledSearchIcon));
    		search.setPressedIcon(new ImageIcon(scaledSearchIconGreyed));
    		search.setDisabledIcon(new ImageIcon(scaledSearchIconGreyed));
    		search.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    		search.setBorderPainted(false);
    		search.setOpaque(false);
    		search.setFocusPainted(false);
    		search.setContentAreaFilled(false);
    		search.setMargin(new Insets(0,0,0,0));
    		search.setToolTipText("Search Earquakes/Locations");
    		
    		searchCities.setIcon(new ImageIcon(scaledCitiesIcon));
    		searchCities.setPressedIcon(new ImageIcon(scaledCitiesIconGreyed));
    		searchCities.setDisabledIcon(new ImageIcon(scaledCitiesIconGreyed));
    		searchCities.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    		searchCities.setBorderPainted(false);
    		searchCities.setOpaque(false);
    		searchCities.setFocusPainted(false);
    		searchCities.setContentAreaFilled(false);
    		searchCities.setMargin(new Insets(0,0,0,0));
    		searchCities.setToolTipText("Select Location");
    		searchCities.setEnabled(false);
    		
    		searchSelectedCity.setIcon(new ImageIcon(scaledCitySearchIcon));
    		searchSelectedCity.setPressedIcon(new ImageIcon(scaledCitySearchIconGreyed));
    		searchSelectedCity.setDisabledIcon(new ImageIcon(scaledCitySearchIconGreyed));
    		searchSelectedCity.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    		searchSelectedCity.setBorderPainted(false);
    		searchSelectedCity.setOpaque(false);
    		searchSelectedCity.setFocusPainted(false);
    		searchSelectedCity.setContentAreaFilled(false);
    		searchSelectedCity.setMargin(new Insets(0,0,0,0));
    		searchSelectedCity.setToolTipText("Search Selected Location");
    		searchSelectedCity.setEnabled(false);
    		
    		generateMap.setIcon(new ImageIcon(scaledMapIcon));
    		generateMap.setPressedIcon(new ImageIcon(scaledMapIconGreyed));
    		generateMap.setDisabledIcon(new ImageIcon(scaledMapIconGreyed));
    		generateMap.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    		generateMap.setBorderPainted(false);
    		generateMap.setOpaque(false);
    		generateMap.setFocusPainted(false);
    		generateMap.setContentAreaFilled(false);
    		generateMap.setMargin(new Insets(0,0,0,0));
    		generateMap.setToolTipText("Generate map of earthquakes");
    		generateMap.setEnabled(false);
    	
    	}
    	catch (Exception e) {
    		System.out.println("Error opening image file");
    	}
    	
    	search.getAccessibleContext().setAccessibleDescription("Run Earthquake Search");
    	search.setActionCommand("RunSearch");
    	search.addActionListener(controller);
    	searchCities.getAccessibleContext().setAccessibleDescription("Run Location Search");
    	searchCities.setActionCommand("RunCitiesSearch");
    	searchCities.addActionListener(controller);
    	searchSelectedCity.getAccessibleContext().setAccessibleDescription("Run Selected Location Search");
    	searchSelectedCity.setActionCommand("RunSelectedCitySearch");
    	searchSelectedCity.addActionListener(controller);
    	generateMap.getAccessibleContext().setAccessibleDescription("Generate map from earthquakes");
    	generateMap.setActionCommand("GenerateMap");
    	generateMap.addActionListener(controller);
    	
    	this.add(search);
    	this.add(searchCities);
    	this.add(searchSelectedCity);
    	this.add(generateMap);
    	
    }
    
    /* *********** GETTERS ************ */
    public MainFrame getFrame() {
        return this.frame;
    }
    
    public IconBarController getController() {
        return this.controller;
    }
    
    public JButton getSearchButton() {
    	return search;
    }
    
    public JButton getSearchCitiesButton() {
    	return searchCities;
    }
    
    public JButton getSearchSelectedCityButton() {
    	return searchSelectedCity;
    }
    
    public JButton getGenerateMapButton() {
    	return generateMap;
    }
    
    
    
    
    
    
	
}
