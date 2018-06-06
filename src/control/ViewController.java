package control;

import control.interfaces.IViewController;
import model.File;
import view.BtnImage;
import view.MainFrame;
import view.interfaces.IMainFrame;

public class ViewController implements IViewController{

	private MainFrame mainFrame;
	private File leftFile;
	private File rightFile;
	private FileManager filemanager;
	
	public ViewController() {
		initComponents();
		addBtnActionListener();
	}
	
	// ###################################################
	// PART: inner function part 
	// ###################################################
	
	private void initComponents() {
		mainFrame = new MainFrame(); // init complete
		leftFile = new File();
		rightFile = new File();
		filemanager = new FileManager();
		
	}
	
	private void addBtnActionListener() {
		mainFrame.setBtnAction(
				(mergeToLeftAction)->{
					filemanager.MergeToLeft();
				}, 
				(cmpAction)->{
					filemanager.Compare();
				},
				(mergeToRightAction)->{
					filemanager.MergeToRight();
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_LEFT, 
				(loadEvent)->{
					filemanager.leftfile_Load(leftFile);
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
					filemanager.leftfile_Save(leftFile);
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(loadEvent)->{
					filemanager.rightfile_Load(rightFile);
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
					filemanager.rightfile_Save(rightFile);
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