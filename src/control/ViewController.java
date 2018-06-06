package control;

import control.interfaces.IViewController;
import model.Edit;
import model.File;
import view.MainFrame;
import view.interfaces.IMainFrame;

public class ViewController implements IViewController{

	private MainFrame mainFrame;
	private File leftFile;
	private File rightFile;
	private FileManager filemanager;
	
	public ViewController() {
		System.out.println("Test");
		initComponents();
		System.out.println("Test");
		addBtnActionListener();
	}
	
	// ###################################################
	// PART: inner function part 
	// ###################################################
	
	private void initComponents() {
		mainFrame = new MainFrame(); // init complete
		leftFile = new File();
		rightFile = new File();
		filemanager = new FileManager(this);
		
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
					filemanager.leftFileLoad();
				}, 
				(editEvent)->{
					if (filemanager.getEditMode(MainFrame.PANEL_LEFT) == Edit.UNEDITABLE) { // edit ��� ����!
						filemanager.onEditMode(MainFrame.PANEL_LEFT);
					}
					else { // ���⸸ �ϴ� ���!
						filemanager.offEditMode(MainFrame.PANEL_LEFT);
					}
				},
				(saveEvent)->{
					filemanager.leftfile_Save(leftFile);
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(loadEvent)->{
					filemanager.rightFileLoad();
				}, 
				(editEvent)->{
					if (filemanager.getEditMode(MainFrame.PANEL_RIGHT) == Edit.UNEDITABLE) { // edit ��� ����!
						filemanager.onEditMode(MainFrame.PANEL_RIGHT);
					}
					else { // ���⸸ �ϴ� ���!
						filemanager.offEditMode(MainFrame.PANEL_RIGHT);
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

	@Override
	public void editModeChangeCallback(int whichPanel, int editMode) {
		boolean editable = (editMode == Edit.EDITABLE) ? true : false;
		mainFrame.setFilePanelEditable(whichPanel, editable);
		if (editable == false) {
			filemanager.changeFileData(whichPanel, mainFrame.getFilePanelContents(whichPanel));
		}
	}
	
	@Override
	public void loadFileCallback(int whichPanel, File file) {
		
		StringBuilder contents = new StringBuilder();
		for (String lineStr : file.getData()) {
			contents.append(lineStr);
			contents.append("\n");
		}
		
		mainFrame.setFilePanelContents(whichPanel, contents.toString());
		mainFrame.setFilePanelName(whichPanel, file.getPath());
		System.out.println("hi");
	}
	
}