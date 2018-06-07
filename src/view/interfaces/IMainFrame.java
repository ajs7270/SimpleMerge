package view.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public interface IMainFrame {

	public void setFilePanelName(int idFilePanel, String name);
	public String getFilePanelName(int idFilePanel);
	
	public void setFilePanelContents(int idFilePanel, String contents);
	public String getFilePanelContents(int idFilePanel);
	
	public void setFilePanelEditable(int idFilePanel, boolean editable);
	public boolean getFilePanelEditable(int idFilePanel);

	public void setFilePanelAction(int idFilePanel, ActionListener loadEvent, ActionListener editEvent, ActionListener saveEvent);
	public void setFilePanelContentsMouseAction(MouseListener action);
	public void setBtnAction(ActionListener cmpEvent);

}
