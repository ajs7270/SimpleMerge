package view;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private FilePanel leftPanel, rightPanel;
	public MainFrame() {
		initComponents();
	}
	
	private void initComponents() {
		leftPanel = new FilePanel(0);
		rightPanel = new FilePanel(1);
	}
	
	
	
	
}