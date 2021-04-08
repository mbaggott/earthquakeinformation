package view.components;

import javax.swing.*;
import controller.TopMenuController;

import java.awt.FlowLayout;
import java.awt.event.*;

public class TopMenu extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6808486029594138070L;
	private MainFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private IconBar iconBar;
	private TopMenuController controller;
	
	/* ********* CONSTRUCTOR ********** */
	public TopMenu (MainFrame frame) {
		
		this.frame = frame;
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		menuBar = new JMenuBar();
        iconBar = new IconBar(frame);
        controller = new TopMenuController(this);
        
        fileMenu();
        helpMenu();
		
		frame.setJMenuBar(menuBar);
		this.add(iconBar);
		
	}
	
	/* ********* GETTERS ********** */
	public MainFrame getFrame() {
        return this.frame;
    }
    
    public TopMenuController getController() {
        return this.controller;
    }
    
    public IconBar getIconBar() {
        return this.iconBar;
    }
	
	private void fileMenu() {  
        //Build the File menu, add accessibility values
        menu = new JMenu("File");
        menu.getAccessibleContext().setAccessibleDescription("File Menu");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);
        
        //Build the Exit option, add accessibility values, add listener
        menuItem = new JMenuItem("Exit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Exit");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
    }
	
	private void helpMenu() {  
        //Build the Help menu, add accessibility values
        menu = new JMenu("Help");
        menu.getAccessibleContext().setAccessibleDescription("Help Menu");
        menu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu);
        
        //Build the Visitor Pattern Information option, 
        //add accessibility values, add listener
        menuItem = new JMenuItem("About Earthquake Information");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "About Earthquake Information");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
    }
}
