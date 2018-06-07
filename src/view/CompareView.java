package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.ICompareView;

public class CompareView extends JFrame implements ICompareView{

	private static final long serialVersionUID = 1L;
	private static final String PATH_LEFT = "res/icon/toLeft.png";
	private static final String PATH_OK = "res/icon/checked.png";
	private static final String PATH_NO = "res/icon/x-button.png";
	private static final String PATH_RIGHT = "res/icon/toRight.png";
	
	private FilePanel leftPanel, rightPanel;
	private BtnImage btnMergeToLeft, btnApply, btnCancel, btnMergeToRight;
	
	public CompareView(String title) {
		super(title);
		
		initComponents();
		arrangeComponets();
		
	}
	
	private void initComponents() {
		leftPanel = new FilePanel(MainFrame.PANEL_LEFT, FilePanel.TYPE_COMPARE);
		rightPanel = new FilePanel(MainFrame.PANEL_RIGHT, FilePanel.TYPE_COMPARE);

		btnMergeToLeft = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_LEFT)), "Merge To Left");
		btnApply = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_OK)), "Apply");
		btnCancel = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_NO)), "Cancel");
		btnMergeToRight = new BtnImage(new ImageIcon(getClass().getClassLoader().getResource(PATH_RIGHT)), "Merge To Right");
	}
	
	private void arrangeComponets() {
		
		int iconSize = 64;
		btnMergeToLeft.setImageSize(iconSize, iconSize);
		btnMergeToLeft.setBorder(false);
		btnMergeToLeft.setOpaque(false);
		
		btnApply.setImageSize(iconSize, iconSize);
		btnApply.setBorder(false);
		btnApply.setOpaque(false);
		
		btnCancel.setImageSize(iconSize, iconSize);
		btnCancel.setBorder(false);
		btnCancel.setOpaque(false);
		
		btnMergeToRight.setImageSize(iconSize, iconSize);
		btnMergeToRight.setBorder(false);
		btnMergeToRight.setOpaque(false);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 2));
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(btnMergeToLeft);
		bottomPanel.add(btnApply);
		bottomPanel.add(btnCancel);
		bottomPanel.add(btnMergeToRight);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1200, 960);
		this.setVisible(true);
	}
	
	private FilePanel getFilePanelById(int idFilePanel) {
		switch(idFilePanel) {
		case MainFrame.PANEL_LEFT:
			return leftPanel;
		case MainFrame.PANEL_RIGHT:
			return rightPanel;
		default:
			return null;
		}
	}
	
	// ###################################################
	// PART: Interface part 
	// ###################################################

	@Override
	public void exit() {
		this.dispose();
	}
	
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
	public void setFilePanelLineColorSize(int idFilePanel, int size) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setLineColorSize(size);
	}

	@Override
	public void paintFilePanelLineColor(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.paintLineColor();
		
	}
	
	@Override
	public List<Integer> getFilePanelDraggeedLine(int idFilePanel) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return null;
		return filePanel.getDraggedLine();
	}

	@Override
	public void setFilePanelAction(int idFilePanel,ActionListener editEvent) {
		FilePanel filePanel = getFilePanelById(idFilePanel);
		if (filePanel == null) return;
		filePanel.setEditAction(editEvent);
	}

	@Override
	public void setFilePanelContentsMouseAction(MouseListener action) {
		leftPanel.addContentsAreaMouseAction(action);
		rightPanel.addContentsAreaMouseAction(action);		
	}

	@Override
	public void setBtnAction(ActionListener mergeToLeftAction, ActionListener applyAction, ActionListener cancelAction, ActionListener mergeToRightAction) {
		btnMergeToLeft.addActionListener(mergeToLeftAction);
		btnApply.addActionListener(applyAction);
		btnCancel.addActionListener(cancelAction);
		btnMergeToRight.addActionListener(mergeToRightAction);
	}

	@Override
	public void setCloseAction(WindowListener l) {
		this.addWindowListener(l);
	}


	
}
