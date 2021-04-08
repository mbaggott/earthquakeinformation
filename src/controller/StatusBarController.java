package controller;

import java.awt.event.*;

import model.facade.*;
import view.components.StatusBar;

public class StatusBarController implements ActionListener {

	private EIModel model;
	// private StatusBar statusBar;
	
	public StatusBarController(StatusBar statusBar) {
		// this.statusBar = statusBar;
		this.model = statusBar.getFrame().getModel();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
	}
	
	public void initStatusBar() {

	}
	
	public String getConnectionStatus() {
		return model.getConnectionStatusEQ();
	}
	
	public void updateStatusbar() {
		
	}
	
	
}
