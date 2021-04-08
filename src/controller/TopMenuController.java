package controller;

import java.awt.event.*;
import javax.swing.*;

import view.components.*;

public class TopMenuController implements ActionListener {
	
	private TopMenu topMenu;
	// private EIModel model;
    
    /* *********** CONSTRUCTOR ************* */
    public TopMenuController(TopMenu topMenu) {
    	this.topMenu = topMenu;
    	// this.model = topMenu.getFrame().getModel();
 
    }
    
	@Override
	public void actionPerformed(ActionEvent ae) {
	        
	        //Execute switch based on Action Command.
	        switch(ae.getActionCommand()) {
	            
	            //Action Command to Exit the program. Will ask for confirmation.
	            case "Exit":
	                int confirmExit = JOptionPane.showOptionDialog(topMenu.getFrame(),
	                        "Are you sure you wish to exit Earthquake Information?",
	                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
	                        JOptionPane.QUESTION_MESSAGE, null, null, null);
	                if (confirmExit == 0) {     
	                    System.exit(0);
	                }
	                break;
	            
	        }
	}
   
}
