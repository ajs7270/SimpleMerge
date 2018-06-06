package control.interfaces;

public interface IFileManager {

	
	public int getEditMode(int whichPanel);
	public void onEditMode(int whichPanel);
	public void offEditMode(int whichPanel);
	
	public void MergeToLeft();
	public void MergeToRight();
	
	
}
