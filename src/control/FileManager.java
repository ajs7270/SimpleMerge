package control;
import java.util.LinkedList;
import java.util.List;

import model.File;
import model.Merge;

public class FileManager {
	
	// create temporary file object
	List<Integer> selectedLineList = new LinkedList<Integer>();
	List<String> leftfile_contents = new LinkedList<String>();                  // left file contents
	List<String> rightfile_contents = new LinkedList<String>();                 // right file contents
	File left_file = new File();
	File right_file = new File();
	Merge merge = new Merge();
	//compare compare = new compare(leftfile_contents, rightfile_contents);
	//Edit edit_mode = new Edit();   // 0: uneditable
	
	//constructor
	FileManager(){
		
	}
	
	// Load file
	public void leftfile_Load(File input_file){       // input_file -> left file
		
		left_file = input_file;
		left_file.load();
	}
	
	public void rightfile_Load(File input_file){       // input_file -> right file
		
		right_file = input_file;
		right_file.load();
	}
	
	// Turn on Edit mode
	public void on_Editmode(){
		
		//edit_mode.changeEditMode();       // edit mode change 0 -> 1 
		                                  // 1: editable
	}
	
	// Turn off Edit mode
	public void off_Editmode(){
		
		//edit_mode.changeEditMode();		  // edit mode change 1 -> 0 
		                                  // 0: uneditable
	}
	
	// Merge
	public void MergeToLeft(){
		
		//if(compare.isCompared())
		if(true) 				                               	// Merge is available
		{
			this.on_Editmode();                                 // turn on edit mode
			merge.setFiles(selectedLineList, left_file.getData(), right_file.getData());
			merge.MergeToLeft();
			this.off_Editmode();                                // turn off edit mode
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	public void MergeToRight(){
		
		//if(compare.isCompared())
		if(true)                                // Merge is available
		{
			this.on_Editmode();                                 // turn on edit mode
			merge.setFiles(selectedLineList, left_file.getData(), right_file.getData());
			merge.MergeToRight();
			this.off_Editmode();                                // turn off edit mode
		}
		else                                                    // Merge is not available
		{
			System.out.println("Merge is not available");
		}
		
	}
	
	/*
	// Compare
	public void Compare(){
		compare.setFiles(leftfile_contents, rightfile_contents);
		compare.lcs();
	}
	*/
	
	// Save file
	public void leftfile_Save(File input_file){

		left_file = input_file;
		left_file.save();
	
	}
	
	public void rightfile_Save(File input_file){

		right_file = input_file;
		right_file.save();
	
	}

}
