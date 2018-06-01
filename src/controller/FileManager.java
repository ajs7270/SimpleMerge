
public class FileManager {
	
	// create temporary file object
	File file = new File();
	Edit edit_mode = new Edit();   // 0: uneditable
	
	//constructor
	FileManager(){
		
	}
	
	FileManager(File input_file){
		file = input_file;
	}
	
	// Load file
	public void Load(File input_file){       // input_file -> left file or right file
		
		file = input_file;
		file.load();
	}
	
	// Turn on Edit mode
	public void on_Editmode(){
		
		edit_mode.changeEditMode();       // edit mode change 0 -> 1 
		                                  // 1: editable
	}
	
	// Turn off Edit mode
	public void off_Editmode(){
		
		edit_mode.changeEditMode();		  // edit mode change 1 -> 0 
		                                  // 0: uneditable
	}
	
	// Save file
	public void Save(File input_file){

		file = input_file;
		file.save();
	
	}

}
