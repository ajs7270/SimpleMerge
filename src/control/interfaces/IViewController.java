package control.interfaces;

import view.interfaces.IMainFrame;

public interface IViewController {

	public IMainFrame getMainFrameInterface();
	public void editModeChangeCallback(int whichPanel, int editMode);
}
