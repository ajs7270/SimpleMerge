package control.interfaces;

import java.util.List;

import model.Status;

public interface IFileManager {

	public void leftFileLoad();
	public void rightFileLoad();
	
	public int getEditMode(int whichPanel);
	public void onEditMode(int whichPanel);
	public void offEditMode(int whichPanel);
	
	public void MergeToLeft();
	public void MergeToRight();
	
	public void changeFileData(int whichFile, String contents);
	
	public void leftFileSave();
	public void rightFileSave();
	
	public void Compare();
	public List<Status> getLeftLineStatus();
	public List<Status> getRightLineStatus();
	
}
