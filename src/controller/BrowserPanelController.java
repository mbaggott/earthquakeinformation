package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.facade.EIModel;
import view.components.BrowserPanel;

public class BrowserPanelController implements ActionListener {
	
	private BrowserPanel browserPanel;
	private EIModel model;
	
	public BrowserPanelController(BrowserPanel browserPanel) {
		this.browserPanel = browserPanel;
		this.model = browserPanel.getTabbedPane().getFrame().getModel();
	}
	
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
		String zoomLevel = "";
		int zoomLevelInt = 0;
		
		 //Execute switch based on Action Command.
        switch(e.getActionCommand()) {
            
        
        	case "ResetZoom":
            	
            	model.setMapZoom(1);
            	browserPanel.getZoomIn().setEnabled(true);
            	browserPanel.getZoomOut().setEnabled(true);
            	browserPanel.setZoomLevel(1);
            	browserPanel.getZoomOut().setEnabled(false);
            	
            	
            	if (browserPanel.getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected().getNameText().getText().length() > 0) {
            		model.buildMapCityCenter();
            
            	}
            	else {
            		model.buildMap();
            	}
            	
            	browserPanel.getTabbedPane().getBrowserPane().displayEarthquakes(model.getMapURL());
            	
            	break;
            	
            case "ZoomIn":
            	
            	zoomLevel = model.getMapZoom();
            	zoomLevelInt = Integer.parseInt(zoomLevel);
            	
            	if (zoomLevelInt +1 <= 21) {
            		zoomLevelInt += 1;
            		browserPanel.getZoomOut().setEnabled(true);
            	}
            	if (zoomLevelInt == 21) {
            		browserPanel.getZoomIn().setEnabled(false);
            	}
            	
            	model.setMapZoom(zoomLevelInt);
            	
            	if (browserPanel.getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected().getNameText().getText().length() > 0) {
            		model.buildMapCityCenter();
            		browserPanel.setZoomLevel(zoomLevelInt);
            		
            
            	}
            	else {
            		model.buildMap();
            		browserPanel.setZoomLevel(zoomLevelInt);
            	}
            	
            	browserPanel.getTabbedPane().getBrowserPane().displayEarthquakes(model.getMapURL());
            	
            	break;
            	
            case "ZoomOut":
            	
            	zoomLevel = model.getMapZoom();
            	zoomLevelInt = Integer.parseInt(zoomLevel);
            	
            	if (zoomLevelInt -1 >= 1) {
            		zoomLevelInt -= 1;
            		browserPanel.getZoomIn().setEnabled(true);
            	}
            	if (zoomLevelInt == 1) {
            		browserPanel.getZoomOut().setEnabled(false);
            	}
            	
            	model.setMapZoom(zoomLevelInt);
            	
            	if (browserPanel.getTabbedPane().getEarthquakePanes().getCitiesContentPaneSelected().getNameText().getText().length() > 0) {
            		model.buildMapCityCenter();
            		browserPanel.setZoomLevel(zoomLevelInt);
            		
            
            	}
            	else {
            		model.buildMap();
            		browserPanel.setZoomLevel(zoomLevelInt);
            	}
            	
            	browserPanel.getTabbedPane().getBrowserPane().displayEarthquakes(model.getMapURL());
            	
            	break;
        }
		
		
	}

}
