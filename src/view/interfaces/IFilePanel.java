package view.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import view.LineColor;

public interface IFilePanel {

	public int getPanelId();
	public void setPanelId(int panelId);
	
	public String getFileName();
	public void setFileName(String fileName);
	
	public String getText();
	public void setText(String text);
	public boolean getTextEditable();
	public void setTextEditable(boolean editable);
	public List<Integer> getDraggedLine();
	public void addContentsAreaMouseAction(MouseListener action);
	
	public void initLineColor();
	
	public LineColor getLineColor(int lineNum);
	public void setLineColor(int lineNum, LineColor color);
	public void paintLineColor();
	
	public void setLoadAction(ActionListener action);
	public void setEditAction(ActionListener action);
	public void setSaveAction(ActionListener action);
}
