package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class File {

	// ������ �����ͷ� line by line���� �����Ͱ� ����Ǿ� �ִ�.
	private List<String> data = new LinkedList<String>();
	private JFrame window;
	private JFileChooser myFileChooser;

	public File() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {		}
		window = new JFrame();
		myFileChooser = new JFileChooser();
	}

	public void load() {

		int intRet = myFileChooser.showOpenDialog(window);

		/*
		 * ��ȯ�� : JFileChooser.CANCEL_OPTION : ��ȭ���ڿ��� ���(Cancel)��ư�� �������
		 * JFileChooser.APPROVE_OPTION : ��ȭ���ڿ��� ����(yes,ok��) ��ư�� �������
		 * JFileChooser.ERROR_OPTION : � ������ �߻��� ��ȭ���ڰ� ��ҵȰ��
		 */

		// ���پ� �б����� String ��ü
		String strLine;

		if (intRet == JFileChooser.APPROVE_OPTION) {
			try {
				// FileChooser�� ���õ� ������ ���ϰ�ü�� ����
				java.io.File myFile = myFileChooser.getSelectedFile();

				// ���õ� ������ �����θ� �����Ͽ� BufferedReader ��ü�� �ۼ�
				BufferedReader myReader = new BufferedReader(new FileReader(myFile.getAbsolutePath()));

				// ���پ� �о���̸鼭 �����Ϳ� ����
				while ((strLine = myReader.readLine()) != null) {
					this.data.add(strLine);
				}

				// Buffered Reader ��ü Ŭ����
				myReader.close();

			} catch (IOException ie) {
				System.out.println(ie + "=> ����¿���");
			}
		}
	}

	public void save() {
		
		int intRet = myFileChooser.showSaveDialog(window);

		if (intRet == JFileChooser.APPROVE_OPTION) {
			try {
				// FileChooser�� ���õ� ������ ���ϰ�ü�� ����
				java.io.File myFile = myFileChooser.getSelectedFile();

				// ���õ� ������ �����θ� �����Ͽ� PrintWriter ��ü�� �ۼ�
				PrintWriter myWriter = new PrintWriter(new BufferedWriter(new FileWriter(myFile.getAbsolutePath())));

				// linkedList�� �ϳ��� String���� �ٲ�
				ListIterator<String> it = (ListIterator<String>) this.data.listIterator();
				StringBuilder sb = new StringBuilder();
				while (it.hasNext()) {
					sb.append(it.next());
					sb.append("\r\n");
				}

				// ���Ͽ� ����
				myWriter.write(sb.toString());
				myWriter.close();

			} catch (IOException ie) {
				System.out.println(ie + "=> ����¿���");
			}
		}
	}

	public List<String> getData() {
		return this.data;
	}
}
