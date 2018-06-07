package view.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.List;

public interface ICompareView {

	public void exit();
	
	public void setFilePanelName(int idFilePanel, String name);
	public String getFilePanelName(int idFilePanel);
	
	public void setFilePanelContents(int idFilePanel, String contents);
	public String getFilePanelContents(int idFilePanel);
	
	public void setFilePanelEditable(int idFilePanel, boolean editable);
	public boolean getFilePanelEditable(int idFilePanel);

	public int getFilePanelLineColor(int idFilePanel, int lineNo);
	public void setFilePanelLineColor(int idFilePanel, int lineNo, int lineColor);
	public void paintFilePanelLineColor(int idFilePanel);
	public void setFilePanelLineColorSize(int idFilePanel, int size);

	public List<Integer> getFilePanelDraggeedLine(int idFilePanel);
	
	public void setBtnAction(ActionListener mergeToLeftAction, ActionListener applyAction, ActionListener cancelAction, ActionListener mergeToRightAction);
	public void setCloseAction(WindowListener l);
	public void setFilePanelContentsMouseAction(MouseListener action);
	public void setFilePanelAction(int idFilePanel, ActionListener editEvent);
	
}
