package control;

import java.util.LinkedList;
import java.util.List;

import control.interfaces.IFileManager;
import model.Edit;
import model.File;
import model.Merge;
import model.Status;
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
	// ���� ���� �ҷ���
	public void leftFileLoad(){
		
		left_file.init();
		left_file.load();
		viewController.loadFileCallback(MainFrame.PANEL_LEFT, left_file);
	}
	
	@Override
	// ������ ���� �ҷ���
	public void rightFileLoad(){
		
		right_file.init();
		right_file.load();
		viewController.loadFileCallback(MainFrame.PANEL_RIGHT, right_file);
	}
	
	
	// ###################################################
	// PART: Edit
	// ###################################################
	// inner
	
	// � �г��� ���� ��ü���� ��ȯ
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
	// �ش� �г��� ���� ��� ��ȯ
	public int getEditMode(int whichPanel) {
		Edit edit = getEdit(whichPanel);
		if (edit == null) return -1;
		return edit.getEditMode();
	}

	@Override
	// �ش� �г��� ���� ��带 Ŵ
	public void onEditMode(int whichPanel) {
		Edit edit = getEdit(whichPanel);
		if (edit == null) return;
		edit.setEditMode(Edit.EDITABLE);
		viewController.editModeChangeCallback(whichPanel, Edit.EDITABLE);
	}
	
	@Override
	// �ش� �г��� ���� ��带 ��
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
	
	// ###################################################
	// PART: Compare
	// ###################################################
	
	@Override
	public void Compare(){
		compare.setFiles(left_file.getData(), right_file.getData());
		compare.lcs();
	}
	

	@Override
	public List<Status> getLeftLineStatus() {
		return compare.getStatus("L");
	}

	@Override
	public List<Status> getRightLineStatus() {
		return compare.getStatus("R");
	}
	
	// ###################################################
	// PART: Save
	// ###################################################
	
	// Save file
	public void leftFileSave(){

		left_file.save();
	
	}
	
	public void rightFileSave(){

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
			System.out.println(strLine);
		}
		System.out.println("save it! " + file.getData().size());
		
	}


}
