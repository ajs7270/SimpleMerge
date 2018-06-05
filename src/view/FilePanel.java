package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import view.interfaces.IFilePanel;

public class FilePanel extends JPanel implements IFilePanel{

	private static final long serialVersionUID = 1L;
	private static final String PATH_LOAD = "res/icon/folder.png";
	private static final String PATH_EDIT = "res/icon/pencil.png";
	private static final String PATH_VIEW = "res/icon/binoculars.png";
	private static final String PATH_SAVE = "res/icon/diskette.png";
	
	private int panelId = -1;
	private BtnImage btnLoad, btnSave, btnEdit;
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
		this.setTextEditable(false);
		//testSetting();
	}
	
	// ###################################################
	// PART: Inner function
	// ###################################################
	
	private void initComponents() {
		btnLoad = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_LOAD)));
		btnSave = new BtnImage 	(new ImageIcon(getClass().getClassLoader().getResource(PATH_SAVE)));
		ArrayList<ImageIcon> editImages = new ArrayList<>();
		editImages.add(new ImageIcon(getClass().getClassLoader().getResource(PATH_EDIT)));
		editImages.add(new ImageIcon(getClass().getClassLoader().getResource(PATH_VIEW)));
		btnEdit = new BtnImage(editImages);
		fileNameLabel = new JLabel();
		contentsArea = new JTextArea();
		scrollArea = new JScrollPane(contentsArea);
	}
	
	private void arrangeComponents() {
		
		int iconSize = 16;
		btnLoad.setImageSize(iconSize, iconSize);
		btnLoad.setBorder(false);
		btnSave.setImageSize(iconSize, iconSize);
		btnSave.setBorder(false);
		btnEdit.setImageSize(iconSize, iconSize);
		btnEdit.setBorder(false);
		
		btnLoad.setOpaque(false);
		btnSave.setOpaque(false);
		btnEdit.setOpaque(false);
		
		JPanel topTopPanel = new JPanel();
		topTopPanel.setLayout(new FlowLayout());
		topTopPanel.add(btnLoad);
		topTopPanel.add(btnSave);
		topTopPanel.add(btnEdit);
		
		JPanel topBottomPanel = new JPanel();
		topBottomPanel.setLayout(new FlowLayout());
		topBottomPanel.add(fileNameLabel);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(topTopPanel, BorderLayout.EAST);
		topPanel.add(topBottomPanel, BorderLayout.WEST);
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(scrollArea, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * adding button listener, 
	 */
	private void settingComponents() {
		contentsArea.setSelectionColor(new Color(255,223, 186, 180));
	}
	
	@SuppressWarnings("unused")
	private void testSetting() {
		lineColor.add(LineColor.LINE_BLANK);
		lineColor.add(LineColor.LINE_PLUS);
		lineColor.add(LineColor.LINE_MINUS);
		lineColor.add(LineColor.LINE_DIFF);
		this.setText("blank\nadded\nremoved\ndifference\ntest\ntest\ntest\ntest\ntest");
		paintLineColor();
	}
	
	private Color getColor(LineColor color){
		switch(color) {
		case LINE_BLANK:
			return Color.WHITE;
		case LINE_PLUS:
			return new Color(186, 255, 201);
		case LINE_MINUS:
			return new Color(255, 179, 186);
		case LINE_DIFF:
			return new Color(255, 255, 186);
		default:
			return Color.WHITE;
		}
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
		contentsArea.setDragEnabled(true);
	}
	
	@Override
	public List<Integer> getDraggedLine() {
		List<Integer> draggedLines = new LinkedList<>();
		int startPos = contentsArea.getSelectionStart();
		int endPos = contentsArea.getSelectionEnd();
		try {
			int startLine = contentsArea.getLineOfOffset(startPos);
			int endLine = contentsArea.getLineOfOffset(endPos);
			for (int index = startLine; index <= endLine; index++) {
				draggedLines.add(index);
			}
			return draggedLines;
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public void addContentsAreaMouseAction(MouseListener action) {
		contentsArea.addMouseListener(action);
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
    	
        
        for (int index = 0; index < lineColor.size(); index++) {
        	Color realColor = getColor(lineColor.get(index));
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