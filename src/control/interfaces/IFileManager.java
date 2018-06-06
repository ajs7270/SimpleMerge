package control.interfaces;

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
}
