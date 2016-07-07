package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.components.CitiesContentPaneSelected;

public class RangeComboController implements ItemListener{
    
	private CitiesContentPaneSelected citiesContentPaneSelected;
	
	public RangeComboController(CitiesContentPaneSelected citiesContentPaneSelected) {
		this.citiesContentPaneSelected = citiesContentPaneSelected;
	}
	
	@Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          
    	   // Gets value of combo box
    	   Object item = event.getItem();
    	   IconBarController ccps = citiesContentPaneSelected.getEarthquakePanes().getTabbedPane().getFrame().getTopMenu().getIconBar().getController();
           ccps.setDistance(item.toString());
           ccps.setNewBoundingBox();
    	   
       }
    }       
}