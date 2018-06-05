package control;

import control.interfaces.IViewController;
import view.MainFrame;
import view.interfaces.IMainFrame;

public class ViewController implements IViewController{

	private MainFrame mainFrame;
	
	public ViewController() {
		initComponents();
		addBtnActionListener();
	}
	
	// ###################################################
	// PART: inner function part 
	// ###################################################
	
	private void initComponents() {
		mainFrame = new MainFrame(); // init complete
		
	}
	
	private void addBtnActionListener() {
		mainFrame.setBtnAction(
				(mergeToLeftAction)->{
				}, 
				(cmpAction)->{
				},
				(mergeToRightAction)->{
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_LEFT, 
				(loadEvent)->{
					
				}, 
				(editEvent)->{
					
				},
				(saveEvent)->{
					
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(loadEvent)->{
					
				}, 
				(editEvent)->{
					
				},
				(saveEvent)->{
					
				});
	}
	
	// ###################################################
	// PART: Interface part 
	// ###################################################
	
	@Override
	public IMainFrame getMainFrameInterface() {
		return mainFrame;
	}
	
}