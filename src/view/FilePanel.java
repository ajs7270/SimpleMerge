package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;

import view.interfaces.IFilePanel;

public class FilePanel extends JPanel implements IFilePanel{

	private static final long serialVersionUID = 1L;
	
	private int panelId = -1;
	private JButton btnLoad, btnSave, btnEdit;
	private JLabel fileNameLabel;
	private JTextArea contentsArea;
	private JScrollPane scrollArea;
	private List<LineColor> lineColor;
	
	public FilePanel(int panelId) {
		setPanelId(panelId);
		initLineColor();
		initComponents();
		arrangeComponents();
		settingComponents();
		
		lineColor.add(LineColor.LINE_BLANK);
		lineColor.add(LineColor.LINE_COLORED);
		paintLineColor();
	}
	
	// ###################################################
	// PART: Inner function
	// ###################################################
	
	private void initComponents() {
		btnLoad = new JButton("L");
		btnSave = new JButton("S");
		btnEdit = new JButton("E");
		fileNameLabel = new JLabel("File Path : ");
		contentsArea = new JTextArea("test\ntest");
		scrollArea = new JScrollPane(contentsArea);
	}
	
	private void arrangeComponents() {
		JPanel topTopPanel = new JPanel();
		topTopPanel.setLayout(new FlowLayout());
		topTopPanel.add(btnLoad);
		topTopPanel.add(btnSave);
		topTopPanel.add(btnEdit);
		
		JPanel topBottomPanel = new JPanel();
		topBottomPanel.setLayout(new FlowLayout());
		topBottomPanel.add(fileNameLabel);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));
		topPanel.add(topTopPanel);
		topPanel.add(topBottomPanel);
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(scrollArea, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * adding button listener, 
	 */
	private void settingComponents() {
		btnEdit.addActionListener((actionEvent)->{
			System.out.println("edit event call");
		});
		btnSave.addActionListener((actionEvent)->{
			System.out.println("save event call");
		});
		btnLoad.addActionListener((actionEvent)->{
			System.out.println("load event call");
		});
		//contentsArea.setDragEnabled(true);
		//contentsArea.setEditable(false);
	}
	
	
	// ###################################################
	// PART: Interface part 
	// ###################################################
	@Override
	public int getPanelId() {
		return this.panelId;
	}
	@Override
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	
	@Override
	public String getFileName() {
		if (fileNameLabel == null) return null;
		return fileNameLabel.getText();
	}
	@Override
	public void setFileName(String fileName) {
		if (fileNameLabel == null) return;
		fileNameLabel.setText(fileName);
	}
	
	@Override
	public String getText() {
		if (contentsArea == null) return null;
		return contentsArea.getText();
	}
	@Override
	public void setText(String text) {
		if (contentsArea == null) return;
		contentsArea.setText(text);
	}
	@Override
	public boolean getTextEditable() {
		if (contentsArea == null) return false;
		return contentsArea.isEditable();		
	}
	@Override
	public void setTextEditable(boolean editable) {
		if (contentsArea == null) return;
		contentsArea.setEditable(editable);
	}
	
	@Override
	public List<Integer> getDraggedLine() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void initLineColor() {
		if (lineColor != null) lineColor.clear();
		lineColor = new ArrayList<>();
	}
	
	private boolean availableLineNum(int lineNum) {
		return !(lineColor == null || lineColor.size() - 1 < lineNum || lineNum < 0);
	}
	@Override
	public LineColor getLineColor(int lineNum) {
		if (availableLineNum(lineNum)) {
			return null;
		}
		else {
			return lineColor.get(lineNum);
		}
	}
	
	@Override
	public void setLineColor(int lineNum, LineColor color) {
		if (availableLineNum(lineNum)) {
			return;
		}
		else {
			lineColor.set(lineNum, color);
		}
	}
	
	@Override
	public void paintLineColor() {
		DefaultHighlighter highlighter =  (DefaultHighlighter)contentsArea.getHighlighter();
    	java.awt.Color realColor = null;
        
        for (int index = 0; index < lineColor.size(); index++) {
        	if (lineColor.get(index).getColor() == LineColor.LINE_COLORED.getColor()) {
        		realColor = java.awt.Color.RED;
        	}
        	else {
        		realColor = java.awt.Color.WHITE;
        	}
        	DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( realColor );
            highlighter.setDrawsLayeredHighlights(false); // this is the key line
        	try {
                int start =  contentsArea.getLineStartOffset(index);
                int end =    contentsArea.getLineEndOffset(index);
                highlighter.addHighlight(start, end, painter );
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
	}

	@Override
	public void setLoadAction(ActionListener action) {
		btnLoad.addActionListener(action);
		
	}

	@Override
	public void setEditAction(ActionListener action) {
		btnEdit.addActionListener(action);
		
	}

	@Override
	public void setSaveAction(ActionListener action) {
		btnSave.addActionListener(action);
	}

	
	
	

}
