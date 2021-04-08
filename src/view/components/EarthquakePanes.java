package view.components;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class EarthquakePanes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6997424019659922462L;
	private EarthquakeContentPane earthquakeContentPane;
	private CitiesContentPaneSelected citiesContentPaneSelected;
	private TabbedPane tabbedPane;
	
	public EarthquakePanes(TabbedPane tabbedPane) {
		
		earthquakeContentPane = new EarthquakeContentPane();
		citiesContentPaneSelected = new CitiesContentPaneSelected(this);
		
		this.tabbedPane = tabbedPane;
		
		this.setLayout(new GridLayout(1, 2));
		
		this.add(earthquakeContentPane);
		this.add(citiesContentPaneSelected);
	}
	
	/* ************ GETTERS ************ */
	
	public CitiesContentPaneSelected getCitiesContentPaneSelected() {
		return citiesContentPaneSelected;
	}
	
	public EarthquakeContentPane getEarthquakeContentPane() {
		return earthquakeContentPane;
	}
	
	public TabbedPane getTabbedPane() {
		
		return tabbedPane;
	}
}
