package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private FilePanel leftPanel, rightPanel;
	private JButton btnCmp, btnMergeToLeft, btnMergeToRight;
	public MainFrame() {
		initComponents();
		arrangeComponents();
		
	}
	
	private void initComponents() {
		leftPanel = new FilePanel(0);
		rightPanel = new FilePanel(1);
		
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
	
	
	public static MainFrame mainFrame;
	public static void main(String[] data) {
		mainFrame = new MainFrame();
	}
	
	
}