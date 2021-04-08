package view.components;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends Region {
	
	final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load("http://www.google.com");
        //add the web view to the scene
        getChildren().add(browser);
 
    }
 
    
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
    
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
    
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
    
    public void navigate(String url) {
    	webEngine.load(url);
    }
    
    
}
