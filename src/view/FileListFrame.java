package view;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class FileListFrame {

	private JFrame window;
	private JFileChooser fileChooser;
	
	public FileListFrame()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			
		}
		
		window = new JFrame();
		fileChooser = new JFileChooser();
	}
	
	public void show()
	{
		//���Ͽ��� ���̾�α׸� ���
	    int result = fileChooser.showOpenDialog(window);
	     
	    if (result == JFileChooser.APPROVE_OPTION) {
	        //������ ������ ��� ��ȯ
	        File selectedFile = fileChooser.getSelectedFile();
	         
	        //��� ���
	        System.out.println(selectedFile);
	    }
	}
}
