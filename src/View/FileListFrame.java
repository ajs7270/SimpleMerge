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
		//파일오픈 다이얼로그를 띄움
	    int result = fileChooser.showOpenDialog(window);
	     
	    if (result == JFileChooser.APPROVE_OPTION) {
	        //선택한 파일의 경로 반환
	        File selectedFile = fileChooser.getSelectedFile();
	         
	        //경로 출력
	        System.out.println(selectedFile);
	    }
	}
}
