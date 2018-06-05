package control;

import control.interfaces.IViewController;
import view.BtnImage;
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
					BtnImage btn = (BtnImage) editEvent.getSource();
					if (btn.getImageIndex() == 0) { // edit 모드 시작!
						btn.setImageIndex(1);
					}
					else { // 보기만 하는 모드!
						btn.setImageIndex(0);
					}
				},
				(saveEvent)->{
					
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(loadEvent)->{
					
				}, 
				(editEvent)->{
					BtnImage btn = (BtnImage) editEvent.getSource();
					if (btn.getImageIndex() == 0) { // edit 모드 시작!
						btn.setImageIndex(1);
					}
					else { // 보기만 하는 모드!
						btn.setImageIndex(0);
					}
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