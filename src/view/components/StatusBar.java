package view.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.List;
import controller.StatusBarController;

public class StatusBar extends JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122800071781025766L;
	private JLabel online;
	private JLabel errorStatus;
	private StatusBarController controller;
	private MainFrame frame;
	private Timer timer;
	private int delay = 15000;
	private String connectionStatus;
	
	public StatusBar(MainFrame frame) {
		
		this.frame = frame;
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		online = new JLabel();
		errorStatus = new JLabel();
	
		this.add(online);
		this.add(errorStatus);
		controller = new StatusBarController(this);
		
		checkConnectionStatus();
		
	}
	
	/* *********** GETTERS ************ */
	
	public MainFrame getFrame() {
		return frame;
	}
	
	public StatusBarController getStatusBarController() {
		return controller;
	}
	
	public JLabel getErrorStatus() {
		return errorStatus;
	}
	
	/* *********** SETTERS ************ */
	
	/* *********** METHODS ************ */
	
	private void checkConnectionStatus() {
		
		connectionStatus = controller.getConnectionStatus();
		online.setText("\n" + connectionStatus);
		
		ActionListener action = new ActionListener()
        {   
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	
            	new Thread(new Runnable() {
        			public void run() {
		            	connectionStatus = controller.getConnectionStatus();
		            	if (connectionStatus == null) {
		            		online.setText("\n" + "");
		            	}
		            	else {
		            		online.setText("\n" + connectionStatus);
		            	}
        			}
            	}).start();
            }
        };
        
        timer = new Timer(delay, action);
        timer.setInitialDelay(15);
        timer.start();
	}
	
	public void displayErrors(List<String> errors) {
		
		String errorList = "Warning: ";
		int numElements = errors.size();
		
		for (int x = 0; x < numElements; x++) {
			errorList += errors.get(x);
			if (x < numElements - 2) {
				errorList += ", ";
			}
			if (x == numElements-2 && x < numElements - 1) {
				errorList += " and ";
			}
			if (x > 1 && x == (numElements - 1)) {
				errorList += " are";
			}
			if (numElements == 1) {
				errorList += " is";
			}
		}
		if (numElements > 1) {
			errorList += " not valid. Default values used for these parameters.";
		}
		else if (numElements == 1) {
			errorList += " not valid. Default values used for this parameter.";
		}
		if (numElements > 0) {
			errorStatus.setText(errorList);
		}
		else {
			errorStatus.setText("");
		}
		
	}
	
}
