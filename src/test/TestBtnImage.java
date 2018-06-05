package test;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.BtnImage;

public class TestBtnImage {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		BtnImage btnImage = new BtnImage(new ImageIcon("C:\\Users\\start\\Documents\\Test\\folder.png"), "open");
		btnImage.setImageSize(63, 63);
		btnImage.setBorder(false);
		btnImage.addActionListener((action)->{
			System.out.println("sys");
		});
		
		BtnImage btnImage1 = new BtnImage(new ImageIcon("C:\\Users\\start\\Documents\\Test\\folder.png"));
		btnImage1.setImageSize(63, 63);
		
		btnImage1.addActionListener((action)->{
			System.out.println("sys1");
		});
		
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(btnImage);
		frame.getContentPane().add(btnImage1);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
