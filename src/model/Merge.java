package model;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Merge {


	List<Integer> selectedLineList = new LinkedList<Integer>();         // The lineList that user wants to copy
	List<String> left_file = new LinkedList<String>();                  // left file
	List<String> right_file = new LinkedList<String>();                 // right file
	
	Merge() {}
	
	//constructor
	Merge(List<Integer> input_selectedLineList, List<String> left_filecontents , List<String> right_filecontents){
		
		selectedLineList = input_selectedLineList;
		left_file = left_filecontents;                                 // get left file contents
		right_file = right_filecontents;								// get right file contents
		
	}
	
	// set files
	public void setFiles(List<Integer> input_selectedLineList, List<String> left_filecontents , List<String> right_filecontents){
		
		selectedLineList = input_selectedLineList;
		left_file = left_filecontents;                                 // get left file contents
		right_file = right_filecontents;								// get right file contents
	
	}
	
	// Merge button clicked
	
	public void MergeToLeft(){           // Copy to Left Method
				
		/* System.out.println("Merge To Left button clicked"); */
		
		// Integer iteration
		ListIterator<Integer> it = selectedLineList.listIterator();
		int index = 0;
		
		// Repeat the selected lines from beginning to end

		while(it.hasNext()){
			
			
			index = it.next();
							
			if(left_file.get(index).equals(right_file.get(index)))    // If exist some same line
			{
				 System.out.println("The selected lines have the same contents."); 
			}
			else                                                 // else
			{
				left_file.remove(index);
				left_file.add(index, right_file.get(index));
			}
	
		}	
		
	}
	
	public void MergeToRight(){           // Copy to Right Method
		
		/* System.out.println("Merge To Left button clicked"); */
		
		// Integer iteration
		ListIterator<Integer> it = selectedLineList.listIterator();
		int index = 0;
		
		// Repeat the selected lines from beginning to end
		
		while(it.hasNext()){

			index = it.next();
							
			if(right_file.get(index).equals(left_file.get(index)))      // If exist some same line
			{
				System.out.println("The selected lines have the same contents."); 
			}
			else												  // else
			{
				right_file.remove(index);
				right_file.add(index, left_file.get(index));
			}
			
		}	

    }
}