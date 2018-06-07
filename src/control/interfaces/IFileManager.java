package control.interfaces;

import java.util.List;

import model.File;
import model.Status;

public interface IFileManager {

	public File getFile(int whichFile);
	
	public void leftFileLoad();
	public void rightFileLoad();
	public boolean isFileLoad();
	
	public int getEditMode(int whichPanel);
	public void onEditMode(int whichPanel);
	public void offEditMode(int whichPanel);
	
	public int getCmpEditMode(int whichPanel);
	public void onCmpEditMode(int whichPanel);
	public void offCmpEditMode(int whichPanel);
	
	public void MergeToLeft(List<Integer> selectedLineList);
	public void MergeToRight(List<Integer> selectedLineList);
	
	public void changeFileData(int whichFile, String contents);
	
	public void leftFileSave();
	public void rightFileSave();
	
	public void Compare();
	public List<Status> getLeftLineStatus();
	public List<Status> getRightLineStatus();
	
}
