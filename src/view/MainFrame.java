package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.IMainFrame;

public class MainFrame extends JFrame implements IMainFrame{

	private static final long serialVersionUID = 1L;
	public static final int PANEL_LEFT = 0, PANEL_RIGHT=1;
	private static final String PATH_COMPARE = "res/icon/balance.png";
	
	private FilePanel leftPanel, rightPanel;
	private BtnImage btnCmp;
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
		
		btnCmp = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_COMPARE)), "Compare");
	}
	
	private void arrangeComponents() {
		
		int iconSize = 64;
		btnCmp.setImageSize(iconSize, iconSize);
		btnCmp.setBorder(false);
		btnCmp.setOpaque(false);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 2));
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		//bottomPanel.add(btnMergeToLeft);
		bottomPanel.add(btnCmp);
		//bottomPanel.add(btnMergeToRight);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 960);
		this.setVisible(true);
	}
	
	private FilePanel getFilePanelById(int idFilePanel) {
		switch(idFilePanel) {
		case PANEL_LEFT:
			return leftPanel;
		case PANEL_RIGHT:
			return rightPanel;
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
	public void setFilePanelAction(int idFilePanel, ActionListener loadEvent, ActionListener editEvent,
			ActionListener saveEvent) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setLoadAction(loadEvent);
		filePanel.setEditAction(editEvent);
		filePanel.setSaveAction(saveEvent);
		
	}

	@Override
	public void setFilePanelContentsMouseAction(MouseListener action) {
		leftPanel.addContentsAreaMouseAction(action);
		rightPanel.addContentsAreaMouseAction(action);		
	}

	@Override
	public void setBtnAction(ActionListener cmpEvent) {
		btnCmp.addActionListener(cmpEvent);
	}

	
}