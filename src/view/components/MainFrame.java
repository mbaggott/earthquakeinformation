package view.components;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import model.facade.*;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 139639974876333445L;
	private EIModel model;
	private BorderPane borderPane;
	//private ContentPane contentPane;
	private TopMenu topMenu;
	private StatusBar statusBar;
	private TabbedPane tabbedPane;

	/* ********* CONSTRUCTORS ********** */
	public MainFrame(EIModel model) {
		// Give the frame a name
		super("Earthquake Information");
		
		// Assign variables
		this.model = model;
		
		// Set the initial frame size
		this.setMinimumSize(new Dimension(800,660));
		
		//Set the frame to use a window listener to exit
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				//tabbedPane.getBrowserPane().getWebBrowser().setEnabled(false);
				System.exit(0);
			}
		});
		
		//Build components
		borderPane = new BorderPane();
		//contentPane = new ContentPane();
		topMenu = new TopMenu(this);
		statusBar = new StatusBar(this);
		tabbedPane = new TabbedPane(this);
		borderPane.add(topMenu, BorderLayout.NORTH);
		borderPane.add(statusBar, BorderLayout.SOUTH);
		borderPane.add(tabbedPane, BorderLayout.CENTER);
		//borderPane.add(contentPane, BorderLayout.CENTER);
		
		this.add(borderPane);
		
	}
	
	/* ********* GETTERS ********* */
	public EIModel getModel() {
		return model;
	}
	
	public TopMenu getTopMenu() {
		return topMenu;
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	

}
