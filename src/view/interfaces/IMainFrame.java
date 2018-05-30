package view.interfaces;

import java.awt.event.ActionListener;

public interface IMainFrame {

	public void setFilePanelName(int idFilePanel, String name);
	public String getFilePanelName(int idFilePanel);
	
	public void setFilePanelContents(int idFilePanel, String contents);
	public String getFilePanelContents(int idFilePanel);
	
	public void setFilePanelEditable(int idFilePanel, boolean editable);
	public boolean getFilePanelEditable(int idFilePanel);

	public int getFilePanelLineColor(int idFilePanel, int lineNo);
	public void setFilePanelLineColor(int idFilePanel, int lineNo, int lineColor);
	public void paintFilePanelLineColor(int idFilePanel);
	
	public void setFilePanelAction(int idFilePanel, ActionListener loadEvent, ActionListener editEvent, ActionListener saveEvent);

}
