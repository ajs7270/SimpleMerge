package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int panelId = -1;
	private JButton btnLoad, btnSave, btnEdit;
	private JLabel fileNameLabel;
	private JTextArea contentsArea;
	private List<Color> lineColor;
	
	public FilePanel(int panelId) {
		setPanelId(panelId);
	}
	
	public int getPanelId() {
		return this.panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	
	public String getFileName() {
		if (fileNameLabel == null) return null;
		return fileNameLabel.getText();
	}
	public void setFileName(String fileName) {
		if (fileNameLabel == null) return;
		fileNameLabel.setText(fileName);
	}
	
	public String getText() {
		if (contentsArea == null) return null;
		return contentsArea.getText();
	}
	public void setText(String text) {
		if (contentsArea == null) return;
		contentsArea.setText(text);
	}
	public boolean getTextEditable() {
		if (contentsArea == null) return false;
		return contentsArea.isEditable();		
	}
	public void setTextEditable(boolean editable) {
		if (contentsArea == null) return;
		contentsArea.setEditable(editable);
	}
	
	public void initLineColor() {
		if (lineColor != null) lineColor.clear();
		lineColor = new ArrayList<>();
	}
	
	private boolean availableLineNum(int lineNum) {
		return !(lineColor == null || lineColor.size() - 1 < lineNum || lineNum < 0);
	}
	public Color getLineColor(int lineNum) {
		if (availableLineNum(lineNum)) {
			return null;
		}
		else {
			return lineColor.get(lineNum);
		}
	}
	
	public void setLineColor(int lineNum, Color color) {
		if (availableLineNum(lineNum)) {
			return;
		}
		else {
			lineColor.set(lineNum, color);
		}
	}
	
	public void paintLineColor() {
		// TODO
	}
	
	
	
	

}
