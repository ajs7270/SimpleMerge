package control;

import java.util.List;

import control.interfaces.IViewController;
import model.Edit;
import model.File;
import model.Status;
import view.LineColor;
import view.MainFrame;
import view.interfaces.IMainFrame;

public class ViewController implements IViewController{

	private MainFrame mainFrame;
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
		filemanager = new FileManager(this);
		
	}
	
	private void coloringLine(int whichPanel, List<Status> listStatus) {
		int lineNum = 0;
		for (Status lineStatus : listStatus) {
			
			// set line color
			LineColor lineColor = LineColor.LINE_BLANK;
			switch(lineStatus) {
			case EQUAL:
				lineColor = LineColor.LINE_BLANK;
				break;
			case CHANGE:
				lineColor = LineColor.LINE_DIFF;
				break;
			case ADD:
				lineColor = LineColor.LINE_PLUS;
				break;
			case DELETE:
				lineColor = LineColor.LINE_MINUS;
				break;
			}
			mainFrame.setFilePanelLineColor(whichPanel, lineNum, lineColor.getColor());
			lineNum++;
		}
	}
	
	private void addBtnActionListener() {
		mainFrame.setBtnAction(
				(mergeToLeftAction)->{
					filemanager.MergeToLeft();
				}, 
				(cmpAction)->{
					filemanager.Compare();
					mainFrame.setFilePanelLineColorSize(MainFrame.PANEL_LEFT, filemanager.getLeftLineStatus().size());
					mainFrame.setFilePanelLineColorSize(MainFrame.PANEL_RIGHT, filemanager.getRightLineStatus().size());
					coloringLine(MainFrame.PANEL_LEFT, filemanager.getLeftLineStatus());
					coloringLine(MainFrame.PANEL_RIGHT, filemanager.getRightLineStatus());
					mainFrame.paintFilePanelLineColor(MainFrame.PANEL_LEFT);
					mainFrame.paintFilePanelLineColor(MainFrame.PANEL_RIGHT);
					
				},
				(mergeToRightAction)->{
					filemanager.MergeToRight();
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_LEFT, 
				(loadEvent)->{
					filemanager.leftFileLoad();
				}, 
				(editEvent)->{
					if (filemanager.getEditMode(MainFrame.PANEL_LEFT) == Edit.UNEDITABLE) { // edit 모드 시작!
						filemanager.onEditMode(MainFrame.PANEL_LEFT);
					}
					else { // 보기만 하는 모드!
						filemanager.offEditMode(MainFrame.PANEL_LEFT);
					}
				},
				(saveEvent)->{
					filemanager.offEditMode(MainFrame.PANEL_LEFT);
					filemanager.leftFileSave();
				});
		
		mainFrame.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(loadEvent)->{
					filemanager.rightFileLoad();
				}, 
				(editEvent)->{
					if (filemanager.getEditMode(MainFrame.PANEL_RIGHT) == Edit.UNEDITABLE) { // edit 모드 시작!
						filemanager.onEditMode(MainFrame.PANEL_RIGHT);
					}
					else { // 보기만 하는 모드!
						filemanager.offEditMode(MainFrame.PANEL_RIGHT);
					}
				},
				(saveEvent)->{
					filemanager.offEditMode(MainFrame.PANEL_RIGHT);
					filemanager.rightFileSave();
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
	}
	
}