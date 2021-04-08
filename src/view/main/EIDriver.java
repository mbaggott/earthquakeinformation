package view.main;

import model.facade.*;

import view.components.*;

import javax.swing.SwingUtilities;

public class EIDriver {
	
	private static EIModel model;
	private MainFrame mainFrame;
	
	public EIDriver() {
		mainFrame = new MainFrame(model);
		mainFrame.setVisible(true); 
	}
	
	public static void main(String[] args) {
		
		model = new EIFacade();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				/* Changes for Mac OSX */
				/* Also need to add the OSX jfxrt.jar */
				// Properties props = System.getProperties();
				// props.put("prism.order", "sw");
				// EIDriver eiInstance = new EIDriver();
			}
		});
	}

}
