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
	File left_file = new File();
	File right_file = new File();
	Merge merge = new Merge();
	compare compare = new compare();
	//Edit edit_mode = new Edit();   // 0: uneditable
	Edit leftEdit = new Edit();
	Edit rightEdit = new Edit();
	Edit leftCmpEdit = new Edit();
	Edit rightCmpEdit = new Edit();
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
	
	@Override
	public boolean isFileLoad() {
		return left_file.isLoad() && right_file.isLoad();
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
	
	private Edit getCmpEdit(int whichPanel) {
		switch(whichPanel) {
		case MainFrame.PANEL_LEFT:
			return leftCmpEdit;
		case MainFrame.PANEL_RIGHT:
			return rightCmpEdit;
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
	
	@Override
	// 해당 패널의 에딧 모드 반환
	public int getCmpEditMode(int whichPanel) {
		Edit edit = getCmpEdit(whichPanel);
		if (edit == null) return -1;
		return edit.getEditMode();
	}

	@Override
	// 해당 패널의 에딧 모드를 킴
	public void onCmpEditMode(int whichPanel) {
		Edit edit = getCmpEdit(whichPanel);
		if (edit == null) return;
		edit.setEditMode(Edit.EDITABLE);
		viewController.cmpEditModeChangeCallback(whichPanel, Edit.EDITABLE);
	}
	
	@Override
	// 해당 패널의 에딧 모드를 끔
	public void offCmpEditMode(int whichPanel) {
		Edit edit = getCmpEdit(whichPanel);
		if (edit == null) return;
		edit.setEditMode(Edit.UNEDITABLE);
		viewController.cmpEditModeChangeCallback(whichPanel, Edit.UNEDITABLE);
	}
	
	// ###################################################
	// PART: Merge
	// ###################################################
	
	@Override
	public void MergeToLeft(List<Integer> selectedLineList){
		
		
		if(compare.isCompared()) 				                 // Merge is available
		{
			merge.setFiles(selectedLineList, compare.getDataL(), compare.getDataR());
			merge.MergeToLeft();
			this.offCmpEditMode(MainFrame.PANEL_LEFT);                                // turn off edit mode
			this.offCmpEditMode(MainFrame.PANEL_RIGHT);
			viewController.cmpMergeCallback(selectedLineList, MainFrame.PANEL_LEFT);
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	@Override
	public void MergeToRight(List<Integer> selectedLineList){
		
		
		if(compare.isCompared())                                // Merge is available
		{
			merge.setFiles(selectedLineList, compare.getDataL(), compare.getDataR());
			merge.MergeToRight();
			this.offCmpEditMode(MainFrame.PANEL_LEFT);                                // turn off edit mode
			this.offCmpEditMode(MainFrame.PANEL_RIGHT);
			viewController.cmpMergeCallback(selectedLineList, MainFrame.PANEL_RIGHT);
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	// ###################################################
	// PART: Compare
	// ###################################################
	
	@SuppressWarnings("unchecked")
	@Override
	public void Compare(){
		LinkedList<String> llist = (LinkedList<String>) left_file.getData();
		LinkedList<String> rlist = (LinkedList<String>) right_file.getData();
		compare.setFiles((LinkedList<String>)llist.clone(), (LinkedList<String>)rlist.clone());
		compare.lcs();
		viewController.cmpCallback(compare.getDataL(), compare.getDataR());
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
	
	@Override
	public File getFile(int whichFile) {
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
