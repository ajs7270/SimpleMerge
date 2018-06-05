package test;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		test.test();
	}
	
	
	public Test() {
		
	}
	
	public void test() {
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/icon/folder.png"));
		ImageIcon icon = new ImageIcon(img);
		System.out.println(icon.getIconWidth());
	}
}
