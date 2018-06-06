package control.interfaces;

import model.File;
import view.interfaces.IMainFrame;

public interface IViewController {

	public IMainFrame getMainFrameInterface();
	public void editModeChangeCallback(int whichPanel, int editMode);
	public void loadFileCallback(int whichPanel, File file);
}
