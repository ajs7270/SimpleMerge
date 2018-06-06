package control;

import java.util.LinkedList;
import java.util.List;

import control.interfaces.IFileManager;
import model.Edit;
import model.File;
import model.Merge;
import model.compare;
import view.MainFrame;

public class FileManager implements IFileManager{
	
	// create temporary file object
	List<Integer> selectedLineList = new LinkedList<Integer>();
	List<String> leftfile_contents = new LinkedList<String>();                  // left file contents
	List<String> rightfile_contents = new LinkedList<String>();                 // right file contents
	File left_file = new File();
	File right_file = new File();
	Merge merge = new Merge();
	compare compare = new compare();
	//Edit edit_mode = new Edit();   // 0: uneditable
	Edit leftEdit = new Edit();
	Edit rightEdit = new Edit();
	ViewController viewController;
	
	
	//constructor
	FileManager(ViewController viewController){
		this.viewController = viewController;
	}
	
	// ###################################################
	// PART: Load
	// ###################################################
	
	@Override
	// 왼쪽 파일 불러옴
	public void leftFileLoad(){
		
		left_file.init();
		left_file.load();
		viewController.loadFileCallback(MainFrame.PANEL_LEFT, left_file);
	}
	
	@Override
	// 오른쪽 파일 불러옴
	public void rightFileLoad(){
		
		right_file.init();
		right_file.load();
		viewController.loadFileCallback(MainFrame.PANEL_RIGHT, right_file);
	}
	
	
	// ###################################################
	// PART: Edit
	// ###################################################
	// inner
	
	// 어떤 패널의 에딧 객체인지 반환
	private Edit getEdit(int whichPanel) {
		switch(whichPanel) {
		case MainFrame.PANEL_LEFT:
			return leftEdit;
		case MainFrame.PANEL_RIGHT:
			return rightEdit;
		default:
			return null;
		}
	}
	
	// interface
	
	@Override
	// 해당 패널의 에딧 모드 반환
	public int getEditMode(int whichPanel) {
		Edit edit = getEdit(whichPanel);
		if (edit == null) return -1;
		return edit.getEditMode();
	}

	@Override
	// 해당 패널의 에딧 모드를 킴
	public void onEditMode(int whichPanel) {
		Edit edit = getEdit(whichPanel);
		if (edit == null) return;
		edit.setEditMode(Edit.EDITABLE);
		viewController.editModeChangeCallback(whichPanel, Edit.EDITABLE);
	}
	
	@Override
	// 해당 패널의 에딧 모드를 끔
	public void offEditMode(int whichPanel) {
		Edit edit = getEdit(whichPanel);
		if (edit == null) return;
		edit.setEditMode(Edit.UNEDITABLE);
		viewController.editModeChangeCallback(whichPanel, Edit.UNEDITABLE);
	}
	
	// ###################################################
	// PART: Merge
	// ###################################################
	
	@Override
	public void MergeToLeft(){
		
		
		if(compare.isCompared()) 				                 // Merge is available
		{
			merge.setFiles(selectedLineList, left_file.getData(), right_file.getData());
			merge.MergeToLeft();
			this.offEditMode(MainFrame.PANEL_LEFT);                                // turn off edit mode
			this.offEditMode(MainFrame.PANEL_RIGHT);
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	@Override
	public void MergeToRight(){
		
		
		if(compare.isCompared())                                // Merge is available
		{
			merge.setFiles(selectedLineList, left_file.getData(), right_file.getData());
			merge.MergeToRight();
			this.offEditMode(MainFrame.PANEL_LEFT);                                // turn off edit mode
			this.offEditMode(MainFrame.PANEL_RIGHT);
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	
	public void Compare(){
		compare.setFiles(leftfile_contents, rightfile_contents);
		compare.lcs();
	}

	
	// Save file
	public void leftfile_Save(File input_file){

		left_file = input_file;
		left_file.save();
	
	}
	
	public void rightfile_Save(File input_file){

		right_file = input_file;
		right_file.save();
	
	}
	
	private File getFile(int whichFile) {
		switch(whichFile) {
		case MainFrame.PANEL_LEFT:
			return left_file;
		case MainFrame.PANEL_RIGHT:
			return right_file;
		default:
			return null;
		}
	}
	
	@Override
	public void changeFileData(int whichFile, String contents) {
		File file = getFile(whichFile);
		if (file == null) return;
		
		file.getData().clear();
		String[] rawData = contents.split("\n");
		for (String strLine : rawData) {
			file.getData().add(strLine);
		}
		System.out.println("save it! " + file.getData().size());
		
	}

}
