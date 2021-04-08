package view.components;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.JSONArray;

public class TabbedPane extends JTabbedPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 528413662884119401L;
	MainFrame frame;
	JComponent panel2;
	JComponent panel3;
	JComponent panel4;
	private CitiesContentPane citiesContentPane;
	private EarthquakePanes earthquakePanes;
	private BrowserPanel browserPanel;
	private JTable table1;
	private JTable table2;
	JPopupMenu popupMenuEarthquake;
	JPopupMenu popupMenuCities;
	private EarthquakeTableModel tableModel1;
	private CitiesTableModel tableModel2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	JSONArray earthquakes;
	JSONArray cities;
	
	public TabbedPane(MainFrame frame) {

		this.frame = frame;
		
		panel3 = makeTextPanel("No earthquake search results to display");
		panel4 = makeTextPanel("No city search results to display");
	
		earthquakePanes = new EarthquakePanes(this);
		citiesContentPane = new CitiesContentPane();
		browserPanel = new BrowserPanel(this);
		
		earthquakes = new JSONArray();
		cities = new JSONArray();
		
		this.addTab("Earthquakes", null, earthquakePanes, "Earthquake Parameters");
		this.setMnemonicAt(0, KeyEvent.VK_1);
		this.addTab("Locations", null, citiesContentPane, "Locations Parameters");
		this.setMnemonicAt(1, KeyEvent.VK_2);
		this.addTab("Earthquake Results", null, panel3, "Earthquake search results");
		this.setMnemonicAt(2, KeyEvent.VK_3);
		this.addTab("Locations Results", null, panel4, "Locations search results");
		this.setMnemonicAt(3, KeyEvent.VK_4);
		this.addTab("Earthquakes Map", null, browserPanel, "Earthquakes Map");
		this.setMnemonicAt(4, KeyEvent.VK_5);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        
		        if (index != 0 && index != 1) {
		        	frame.getTopMenu().getIconBar().getSearchButton().setEnabled(false);
		        }
		        else {
		        	frame.getTopMenu().getIconBar().getSearchButton().setEnabled(true);
		        }
		        
		        if (index != 3 || table2 == null) {
		        	frame.getTopMenu().getIconBar().getSearchCitiesButton().setEnabled(false);
		        }
		        else  {
		        	frame.getTopMenu().getIconBar().getSearchCitiesButton().setEnabled(true);
		        }
		        
		        if (index == 0 && earthquakePanes.getEarthquakeContentPane().getName() != "") {
		        	frame.getTopMenu().getIconBar().getSearchSelectedCityButton().setEnabled(true);
		        }
		        else {
		        	frame.getTopMenu().getIconBar().getSearchSelectedCityButton().setEnabled(false);
		        }
		        if (index == 2 && table1 != null) {
		        	if (table1.getRowCount() > 0) {
		        		frame.getTopMenu().getIconBar().getGenerateMapButton().setEnabled(true);
		        	}
		        }
		        else {
		        	frame.getTopMenu().getIconBar().getGenerateMapButton().setEnabled(false);
		        }
		        
		      }
		    };
		    
		    this.addChangeListener(changeListener);
	}
	
	/* ************* GETTERS *************** */
	public EarthquakePanes getEarthquakePanes() {
		return earthquakePanes;
	}
	
	public CitiesContentPane getCitiesContentPane() {
		return citiesContentPane;
	}
	
	public MainFrame getFrame() {
		return frame;
	}
	
	private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	public JTable getCitiesResultsTable() {
		return table2;
	}
	
	public JSONArray getEarthquakes() {
		return earthquakes;
	}
	
	public BrowserPanel getBrowserPane() {
		return browserPanel;
	}
	
	/* ************ SETTERS ************ */
	public void setEarthquakes(JSONArray eqs) {
		earthquakes = eqs;
	}
	
	public void setCities(JSONArray cts) {
		cities = cts;
	}
	
	/* ************ METHODS ************ */
	public void createTableEarthquake(boolean selectedCity) {
		
		popupMenuEarthquake = new JPopupMenu();
		
		tableModel1 = new EarthquakeTableModel(this, earthquakes.size(), earthquakes, selectedCity);
		table1 = new JTable(tableModel1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		scrollPane1 = new JScrollPane(table1);
		
		table1.setAutoCreateRowSorter(true);
		
		this.removeTabAt(2);
		this.insertTab("Earthquake Results", null, scrollPane1, "Earthquake search results", 2);
		this.setMnemonicAt(2, KeyEvent.VK_3); 
		
		popupMenuEarthquake.add(new JMenuItem(new AbstractAction("Copy") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2520491451904112491L;

			@Override
			public void actionPerformed(ActionEvent e) {
		        int row = table1.getSelectedRow();
		        int col = table1.getSelectedColumn();

		        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		        StringSelection entry = new StringSelection(table1.getValueAt(row, col).toString());
		        cb.setContents(entry, null);

		    }
		}));
		
		table1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }
			
			@Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }
			
			protected void doPopup(MouseEvent e) { 
                popupMenuEarthquake.show(e.getComponent(), e.getX(), e.getY());
            }
			
			protected void highlightRow(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);

                table.setRowSelectionInterval(row, row);
                table.setColumnSelectionInterval(col, col);
            }
			
		});
	}
	
	public void createTableCities() {
	
		popupMenuCities = new JPopupMenu();
	
		tableModel2 = new CitiesTableModel(cities.size(), cities);
		table2 = new JTable(tableModel2);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumnModel().getColumn(0).setPreferredWidth(30);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane2 = new JScrollPane(table2);
		
		this.removeTabAt(3);
		this.insertTab("Locations Results", null, scrollPane2, "Locations search results", 3);
		this.setMnemonicAt(3, KeyEvent.VK_3);
		
		popupMenuCities.add(new JMenuItem(new AbstractAction("Copy") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7523998815604114844L;

			@Override 
			public void actionPerformed(ActionEvent e) {
		        int row = table2.getSelectedRow();
		        int col = table2.getSelectedColumn();

		        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		        StringSelection entry = new StringSelection(table2.getValueAt(row, col).toString());
		        cb.setContents(entry, null);

		    }
		}));
		
		table2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }
			
			@Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }
			
			protected void doPopup(MouseEvent e) {
                popupMenuCities.show(e.getComponent(), e.getX(), e.getY());
            }
			
			protected void highlightRow(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);

                table.setRowSelectionInterval(row, row);
                table.setColumnSelectionInterval(col, col);
            }
			
		});
	}
	
}
