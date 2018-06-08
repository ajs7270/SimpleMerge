package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class File {

	// 파일의 데이터로 line by line으로 데이터가 저장되어 있다.
	private String path = "[No File]";
	private List<String> data = new LinkedList<String>();
	private boolean isLoad = false;
	private String OSname = System.getProperty("os.name").toLowerCase();
	
	private JFrame window;
	private JFileChooser myFileChooser;

	public File() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {		}
		window = new JFrame();
		myFileChooser = new JFileChooser();
	}
	
	public void init() {
		path = "[No File]";
		data.clear();
	}

	public void load() {

		int intRet = myFileChooser.showOpenDialog(window);

		/*
		 * 반환값 : JFileChooser.CANCEL_OPTION : 대화상자에서 취소(Cancel)버튼이 눌린경우
		 * JFileChooser.APPROVE_OPTION : 대화상자에서 응당(yes,ok등) 버튼이 눌린경우
		 * JFileChooser.ERROR_OPTION : 어떤 에러가 발생해 대화상자가 취소된경우
		 */

		// 한줄씩 읽기위한 String 객체
		String strLine;

		if (intRet == JFileChooser.APPROVE_OPTION) {
			try {
				// FileChooser로 선택된 파일을 파일객체에 대입
				java.io.File myFile = myFileChooser.getSelectedFile();
				path = myFile.getAbsolutePath();

				// 선택된 파일의 절대경로를 지정하여 BufferedReader 객체를 작성
				BufferedReader myReader = new BufferedReader(new FileReader(myFile.getAbsolutePath()));

				// 한줄씩 읽어들이면서 데이터에 저장
				while ((strLine = myReader.readLine()) != null) {
					this.data.add(strLine);
				}

				// Buffered Reader 객체 클로즈
				myReader.close();
				isLoad = true;
			} catch (IOException ie) {
				System.out.println(ie + "=> 입출력오류");
				isLoad = false;
			}
		}
	}

	public void save() {
		
		int intRet = myFileChooser.showSaveDialog(window);

		if (intRet == JFileChooser.APPROVE_OPTION) {
			try {
				// FileChooser로 선택된 파일을 파일객체에 대입
				java.io.File myFile = myFileChooser.getSelectedFile();

				// 선택된 파일의 절대경로를 지정하여 PrintWriter 객체를 작성
				PrintWriter myWriter = new PrintWriter(new BufferedWriter(new FileWriter(myFile.getAbsolutePath())));

				// linkedList를 하나의 String으로 바꿈
				ListIterator<String> it = (ListIterator<String>) this.data.listIterator();
				StringBuilder sb = new StringBuilder();
				while (it.hasNext()) {
					sb.append(it.next());
					//Windows 개행
					if(OSname.startsWith("windows"))
						sb.append("\r\n");
					//Linux Mac 개행
					else
						sb.append("\n");
				}

				// 파일에 저장
				myWriter.write(sb.toString());
				myWriter.close();

			} catch (IOException ie) {
				System.out.println(ie + "=> 입출력오류");
			}
		}
	}

	public List<String> getData() {
		return this.data;
	}
	
	public String getPath() {
		return this.path;
	}

	public boolean isLoad() {
		return isLoad;
	}

	public void setLoad(boolean isLoad) {
		this.isLoad = isLoad;
	}
}
