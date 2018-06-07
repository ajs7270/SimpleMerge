package control.interfaces;

import java.util.List;

import model.File;
import view.interfaces.IMainFrame;

public interface IViewController {

	public IMainFrame getMainFrameInterface();
	public void editModeChangeCallback(int whichPanel, int editMode);
	public void cmpEditModeChangeCallback(int whichPanel, int editMode);
	public void loadFileCallback(int whichPanel, File file);
	public void cmpCallback(List<String> llist, List<String> rlist);
	public void cmpExitCallback();
}
