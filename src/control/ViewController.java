package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;

import control.interfaces.IViewController;
import model.Edit;
import model.File;
import model.Status;
import view.CompareView;
import view.LineColor;
import view.MainFrame;
import view.interfaces.IMainFrame;

public class ViewController implements IViewController{

	private MainFrame mainFrame;
	private FileManager filemanager;
	private CompareView compareView = null;
	
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
	
	private void addBtnActionListener() {
		mainFrame.setBtnAction(
				(cmpAction)->{
					filemanager.offEditMode(MainFrame.PANEL_LEFT);
					filemanager.offEditMode(MainFrame.PANEL_RIGHT);
					int check = checkCompareView();
					switch (check) {
					case 0:
						showCompareView();
						break;
					case 1:
						JOptionPane.showMessageDialog(mainFrame, "이미 비교 중입니다.");
						break;
					case 2:
						JOptionPane.showMessageDialog(mainFrame, "파일이 로드되지 않았습니다.");
						break;
					default:
						break;
					}
					
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
	
	//
	// compare view part
	//
	
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
			compareView.setFilePanelLineColor(whichPanel, lineNum, lineColor.getColor());
			lineNum++;
		}
	}
	
	private int checkCompareView() {
		if (compareView != null) {
			return 1;
		}
		if (!filemanager.isFileLoad()) {
			return 2;
		}
		return 0;
	}
	
	private void showCompareView() {
		
		compareView = new CompareView("Result Of Compare");
		
		filemanager.Compare();
		
		// actions
		compareView.setBtnAction(
				(mergeToLeftAction)-> {
					List<Integer> list = compareView.getFilePanelDraggeedLine(MainFrame.PANEL_RIGHT);
					for (Integer i : list) {
						System.out.println("sdf " + i);
					}
					if (list.size() == 0 || list.isEmpty()) {
						JOptionPane.showMessageDialog(compareView, "선택된 것이 없습니다.");
						return;
					}
					int ret = JOptionPane.showConfirmDialog(compareView, String.format("총 %d 개의 줄이 선택되었습니다. 머지하시겠습니까?", list.size()), "Merge", JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION) {
						filemanager.MergeToLeft(list);
					}
		}, 
				(applyAction) -> {
					filemanager.changeFileData(MainFrame.PANEL_LEFT, compareView.getFilePanelContents(MainFrame.PANEL_LEFT));
					filemanager.changeFileData(MainFrame.PANEL_RIGHT, compareView.getFilePanelContents(MainFrame.PANEL_RIGHT));
					this.setMainFrameContents(MainFrame.PANEL_LEFT, filemanager.left_file.getData());
					this.setMainFrameContents(MainFrame.PANEL_RIGHT, filemanager.right_file.getData());
					compareView.exit();
					cmpExitCallback();
		}, 
				(cancelAction)->{
					compareView.exit();
					cmpExitCallback();
		}, 
				(mergeToRightAction)->{
					List<Integer> list = compareView.getFilePanelDraggeedLine(MainFrame.PANEL_LEFT);
					if (list.size() == 0 || list.isEmpty()) {
						JOptionPane.showMessageDialog(compareView, "선택된 것이 없습니다.");
						return;
					}
					int ret = JOptionPane.showConfirmDialog(compareView, String.format("총 %d 개의 줄이 선택되었습니다. 머지하시겠습니까?", list.size()), "Merge", JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION) {
						filemanager.MergeToRight(list);
					}
		});
		
		compareView.setFilePanelAction(MainFrame.PANEL_LEFT, 
				(editEvent)->{
					if (filemanager.getCmpEditMode(MainFrame.PANEL_LEFT) == Edit.UNEDITABLE) { // edit 모드 시작!
						filemanager.onCmpEditMode(MainFrame.PANEL_LEFT);
					}
					else { // 보기만 하는 모드!
						filemanager.offCmpEditMode(MainFrame.PANEL_LEFT);
					}
		});
		
		compareView.setFilePanelAction(MainFrame.PANEL_RIGHT, 
				(editEvent)->{
					if (filemanager.getCmpEditMode(MainFrame.PANEL_RIGHT) == Edit.UNEDITABLE) { // edit 모드 시작!
						filemanager.onCmpEditMode(MainFrame.PANEL_RIGHT);
					}
					else { // 보기만 하는 모드!
						filemanager.offCmpEditMode(MainFrame.PANEL_RIGHT);
					}
		});
		
		compareView.setCloseAction(new WindowListener() {

			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				cmpExitCallback();			
			}
		});
		
		// set line coloring
		compareView.setFilePanelLineColorSize(MainFrame.PANEL_LEFT, filemanager.getLeftLineStatus().size());
		compareView.setFilePanelLineColorSize(MainFrame.PANEL_RIGHT, filemanager.getRightLineStatus().size());
		coloringLine(MainFrame.PANEL_LEFT, filemanager.getLeftLineStatus());
		coloringLine(MainFrame.PANEL_RIGHT, filemanager.getRightLineStatus());
		compareView.paintFilePanelLineColor(MainFrame.PANEL_LEFT);
		compareView.paintFilePanelLineColor(MainFrame.PANEL_RIGHT);
	}
	
	private void setCompareViewContents(int whichPanel, List<String> list) {
		StringBuilder contents = new StringBuilder();
		for (String lineStr : list) {
			contents.append(lineStr);
			contents.append("\n");
		}
		
		compareView.setFilePanelContents(whichPanel, contents.toString());
		compareView.setFilePanelName(whichPanel, filemanager.getFile(whichPanel).getPath());
	}
	
	private void setMainFrameContents(int whichPanel, List<String> list) {
		StringBuilder contents = new StringBuilder();
		for (String lineStr : list) {
			contents.append(lineStr);
			contents.append("\n");
		}
		
		mainFrame.setFilePanelContents(whichPanel, contents.toString());
	}
	
	private void setMainFramePath(int whichPanel, File file) {

		mainFrame.setFilePanelName(whichPanel, file.getPath());
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
	public void cmpEditModeChangeCallback(int whichPanel, int editMode) {
		boolean editable = (editMode == Edit.EDITABLE) ? true : false;
		compareView.setFilePanelEditable(whichPanel, editable);
	}
	
	
	@Override
	public void loadFileCallback(int whichPanel, File file) {
		
		setMainFrameContents(whichPanel, file.getData());
		setMainFramePath(whichPanel, file);
	}

	@Override
	public void cmpCallback(List<String> llist, List<String> rlist) {
		setCompareViewContents(MainFrame.PANEL_LEFT, llist);
		setCompareViewContents(MainFrame.PANEL_RIGHT, rlist);
	}

	@Override
	public void cmpExitCallback() {
		System.out.println("bye");
		compareView = null;	
	}

	@Override
	public void cmpMergeCallback(List<Integer> selectedLine, int toMerge) {
		
		setCompareViewContents(MainFrame.PANEL_LEFT, filemanager.compare.getDataL());
		setCompareViewContents(MainFrame.PANEL_RIGHT, filemanager.compare.getDataR());
		
		for (Integer i : selectedLine) {
			compareView.setFilePanelLineColor(MainFrame.PANEL_LEFT, i.intValue(), LineColor.LINE_BLANK.getColor());
			compareView.setFilePanelLineColor(MainFrame.PANEL_RIGHT, i.intValue(), LineColor.LINE_BLANK.getColor());
		}
		compareView.paintFilePanelLineColor(MainFrame.PANEL_LEFT);
		compareView.paintFilePanelLineColor(MainFrame.PANEL_RIGHT);
	}
	
}