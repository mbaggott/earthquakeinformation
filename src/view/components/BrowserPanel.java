package view.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.BrowserPanelController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import model.facade.EIModel;

public class BrowserPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8005489819876944972L;
	private TabbedPane tabbedPane;
	private BrowserPanelController controller;
	private Browser browser;
	private Scene scene;
	private JPanel zoomPanelHolder;
	private JPanel zoomPanel;
	private JButton zoomIn;
	private JButton zoomOut;
	private JButton resetZoom;
	private JLabel zoomLevel;
	private JLabel zoomLevelLabel;
	private JPanel zoomLevelPanel;
	private JPanel zoomLevelListHolder;
	private JScrollPane zoomLevelListScrollPane;
	private JFXPanel fxPanel;
	private JPanel zoomLevelList; 
	String key;
	
	public BrowserPanel(TabbedPane tabbedPane) {
		
		this.tabbedPane = tabbedPane;
		this.setLayout(new GridBagLayout());
	
		controller = new BrowserPanelController(this);
		
		zoomPanelHolder = new JPanel();
		zoomPanelHolder.setLayout(new GridBagLayout());
		zoomLevelListHolder = new JPanel();
		
		zoomLevelListHolder.setPreferredSize(new Dimension(150, 300));
		
		zoomPanel = new JPanel();
		zoomIn = new JButton();
		zoomOut = new JButton();
		resetZoom = new JButton("Reset Zoom");
		zoomLevel = new JLabel("Auto");
		zoomLevelLabel = new JLabel("Zoom (1-21): ");
		zoomLevelPanel = new JPanel();
		
		zoomLevelPanel.setLayout(new FlowLayout());
		zoomLevelLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		zoomLevel.setFont(new Font("Serif", Font.BOLD, 12));
		
		zoomLevelPanel.add(zoomLevelLabel);
		zoomLevelPanel.add(zoomLevel);
		
		try {
			
			InputStream input1 = this.getClass().getClassLoader().getResourceAsStream("zoomIn.png");
			InputStream input2 = this.getClass().getClassLoader().getResourceAsStream("zoomOut.png");
			InputStream input3 = this.getClass().getClassLoader().getResourceAsStream("zoomInGreyed.png");
			InputStream input4 = this.getClass().getClassLoader().getResourceAsStream("zoomOutGreyed.png");
			Image zoomInIcon = ImageIO.read(input1);
			Image zoomOutIcon = ImageIO.read(input2);
			Image zoomInIconGreyed = ImageIO.read(input3);
			Image zoomOutIconGreyed = ImageIO.read(input4);
			Image scaledZoomInIcon = zoomInIcon.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
			Image scaledZoomOutIcon = zoomOutIcon.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
			Image scaledZoomInIconGreyed = zoomInIconGreyed.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
			Image scaledZoomOutIconGreyed = zoomOutIconGreyed.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
			
			zoomIn.setIcon(new ImageIcon(scaledZoomInIcon));
			zoomIn.setPressedIcon(new ImageIcon(scaledZoomInIconGreyed));
			zoomIn.setDisabledIcon(new ImageIcon(scaledZoomInIconGreyed));
			zoomIn.setBorder(BorderFactory.createEmptyBorder());
			zoomIn.setBorderPainted(false);
			zoomIn.setOpaque(false);
    		zoomIn.setFocusPainted(false);
    		zoomIn.setContentAreaFilled(false);
    		zoomIn.setMargin(new Insets(0,0,0,0));
    		zoomIn.setToolTipText("Zoom In Map");
    		zoomIn.setEnabled(false);
    		
    		zoomOut.setIcon(new ImageIcon(scaledZoomOutIcon));
    		zoomOut.setPressedIcon(new ImageIcon(scaledZoomOutIconGreyed));
    		zoomOut.setDisabledIcon(new ImageIcon(scaledZoomOutIconGreyed));
    		zoomOut.setBorder(BorderFactory.createEmptyBorder());
    		zoomOut.setBorderPainted(false);
    		zoomOut.setOpaque(false);
    		zoomOut.setFocusPainted(false);
    		zoomOut.setContentAreaFilled(false);
    		zoomOut.setMargin(new Insets(0,0,0,0));
    		zoomOut.setToolTipText("Zoom Out Map");
    		zoomOut.setEnabled(false);
    		
    		zoomIn.getAccessibleContext().setAccessibleDescription("Zoom In");
    		zoomIn.setActionCommand("ZoomIn");
    		zoomIn.addActionListener(controller);
    		zoomOut.getAccessibleContext().setAccessibleDescription("Zoom Out");
    		zoomOut.setActionCommand("ZoomOut");
    		zoomOut.addActionListener(controller);
    		resetZoom.getAccessibleContext().setAccessibleDescription("Reset Zoom Level");
    		resetZoom.setActionCommand("ResetZoom");
    		resetZoom.addActionListener(controller);
    		
    		zoomPanel.add(zoomIn);
    		zoomPanel.add(zoomOut);
    		
    		GridBagConstraints gbc = new GridBagConstraints();
    		gbc.gridx = 0;
    		gbc.gridy = 0;
    		zoomPanelHolder.add(zoomLevelPanel, gbc);
    		
    		gbc.gridx = 0;
    		gbc.gridy = 1;
    		zoomPanelHolder.add(resetZoom, gbc);
    		
    		gbc.gridx = 0;
    		gbc.gridy = 2;
    		zoomPanelHolder.add(zoomPanel, gbc);
    		
    		gbc.gridx = 0;
    		gbc.gridy = 3;
    		zoomPanelHolder.add(zoomLevelListHolder, gbc);
    		
    		zoomPanelHolder.setVisible(false);
    		zoomPanelHolder.setBorder(new EmptyBorder(10,0,0,20));
    		
		}
		catch (Exception e) {
	    	System.out.println("Error opening image file");
	    }
		
		initAndShowGUI();
	}	
	
	public Browser getWebBrowser() {
		return browser;
	}
	
	public JPanel getZoomPanelHolder() {
		return zoomPanelHolder;
	}
	
	public void displayEarthquakes(URL url) {
		
		zoomPanelHolder.setVisible(true);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	
	        	browser.webEngine.load(url.toString());
	        	browser.webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
	        	      @Override
	        	      public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State state) {
	        	        switch (state) {
	        	          case RUNNING:
	        	            break;

	        	          case SUCCEEDED:
	        	        	  fxPanel.setVisible(true);
	        	            break;
						default:
							break;
	        	        }
	        	      }
	        	    });
	        }
	    });
		
		
	}
	
	public void displayZoomList(LinkedHashMap<String, ArrayList<String>> markers) {
		
		zoomLevelListHolder.removeAll();
		
		zoomLevelList = new JPanel();
		zoomLevelList.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		String listValue;
		JLabel label;
		JButton button;
		
		for (int x = 0; x < markers.size(); x++) {
			
			label = new JLabel();
			button = new JButton("\u25CE");
			button.setPreferredSize(new Dimension(25,20));
			button.setFont(new Font("Serif", Font.PLAIN, 12));
			key = markers.keySet().toArray()[x].toString();
			
			button.addActionListener(new ActionListener() {
				
				EIModel model = tabbedPane.getFrame().getModel();
				String keyValue = new String(key);
				
				public void actionPerformed(ActionEvent e) {
					model.setMapCenter(Double.parseDouble(markers.get(keyValue).get(0)), Double.parseDouble(markers.get(keyValue).get(1)));
					model.buildMap();
					tabbedPane.getBrowserPane().displayEarthquakes(model.getMapURL());
				}
				
			});
			
			gbc.gridx = 0;
			gbc.gridy = x;
			button.setMargin(new Insets(0,0,0,0));
			zoomLevelList.add(button, gbc);
			
			label.setFont(new Font("Serif", Font.PLAIN, 10));
			gbc.gridx = 1;
			gbc.gridy = x;
			gbc.insets = new Insets(5,5,5,5);
			gbc.anchor = GridBagConstraints.WEST;
			
			listValue = key + ": " + markers.get(key).get(0) + ", " + markers.get(key).get(1);
			label.setText(listValue);
			zoomLevelList.add(label, gbc);
			
		}
		zoomLevelListScrollPane = new JScrollPane(zoomLevelList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		zoomLevelListScrollPane.setPreferredSize(new Dimension(145, 295));
		zoomLevelListHolder.add(zoomLevelListScrollPane);
		zoomLevelListHolder.revalidate();
		zoomLevelListHolder.repaint();
		
	}
	
	public void hideEarthquakes() {
		fxPanel.setVisible(false);
		zoomPanelHolder.setVisible(false);
	}
	
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public JButton getZoomIn() {
		return zoomIn;
	}
	
	public JButton getZoomOut() {
		return zoomOut;
	}
	
	public JLabel getZoomLevel() {
		return zoomLevel;
	}
	
	public void setZoomLevel(int newLevel) {
		zoomLevel.setText(String.valueOf(newLevel));
	}
	
	 private void initAndShowGUI() {
		fxPanel = new JFXPanel();
		this.setPreferredSize(new Dimension(700,400));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor  = GridBagConstraints.NORTHEAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(zoomPanelHolder, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(fxPanel, gbc);
		
		// This method is invoked on the FX thread
		Platform.runLater(new Runnable() {
	       @Override
	       public void run() {
	           initFX(fxPanel);
	       }
		});
	}

	private void initFX(JFXPanel fxPanel) {
	    Scene scene = createScene();
	    fxPanel.setScene(scene);
	    fxPanel.setVisible(false);
	}
	
	private Scene createScene() {
		browser = new Browser();
        scene = new Scene(browser, 600,400);
        return (scene);
    }
}
