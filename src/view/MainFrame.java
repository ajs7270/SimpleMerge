package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.IMainFrame;

public class MainFrame extends JFrame implements IMainFrame{

	private static final long serialVersionUID = 1L;
	public static final int PANEL_LEFT = 0, PANEL_RIGHT=1;
	
	private FilePanel leftPanel, rightPanel;
	private JButton btnCmp, btnMergeToLeft, btnMergeToRight;
	public MainFrame() {
		initComponents();
		arrangeComponents();
		
	}
	
	// ###################################################
	// PART: inner function part 
	// ###################################################
	private void initComponents() {
		leftPanel = new FilePanel(PANEL_LEFT);
		rightPanel = new FilePanel(PANEL_RIGHT);
		
		btnCmp = new JButton("Compare");
		btnMergeToLeft = new JButton("Merge To Left");
		btnMergeToRight = new JButton("Merge To Right");
	}
	
	private void arrangeComponents() {
			
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 2));
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(btnMergeToLeft);
		bottomPanel.add(btnCmp);
		bottomPanel.add(btnMergeToRight);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
	private FilePanel getFilePanelById(int idFilePanel) {
		switch(idFilePanel) {
		case PANEL_LEFT:
			return leftPanel;
		case PANEL_RIGHT:
		default:
			return null;
		}
	}
	
	
	// ###################################################
	// PART: Interface part 
	// ###################################################
	@Override
	public void setFilePanelName(int idFilePanel, String name) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel != null) filePanel.setFileName(name);
		
	}

	@Override
	public String getFilePanelName(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null)	return null;
		else return filePanel.getFileName();
	}

	@Override
	public void setFilePanelContents(int idFilePanel, String contents) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setText(contents);
		
	}

	@Override
	public String getFilePanelContents(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null)	return null;
		else return filePanel.getText();
	}

	@Override
	public void setFilePanelEditable(int idFilePanel, boolean editable) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setTextEditable(editable);
		
	}

	@Override
	public boolean getFilePanelEditable(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null)	return false;
		else return filePanel.getTextEditable();
	}

	@Override
	public int getFilePanelLineColor(int idFilePanel, int lineNo) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null)	return -1;
		else return filePanel.getLineColor(lineNo).getColor();
	}

	@Override
	public void setFilePanelLineColor(int idFilePanel, int lineNo, int lineColor) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setLineColor(lineNo, LineColor.getLineColor(lineColor));
		
		
	}

	@Override
	public void paintFilePanelLineColor(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.paintLineColor();
		
	}

	@Override
	public void setFilePanelAction(int idFilePanel, ActionListener loadEvent, ActionListener editEvent,
			ActionListener saveEvent) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setLoadAction(loadEvent);
		filePanel.setEditAction(editEvent);
		filePanel.setSaveAction(saveEvent);
		
	}
}