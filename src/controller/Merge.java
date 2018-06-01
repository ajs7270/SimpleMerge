import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Merge {


	List<Integer> selectedLineList = new LinkedList<Integer>();         // The lineList that user wants to copy
	List<String> left_file = new LinkedList<String>();                  // left file
	List<String> right_file = new LinkedList<String>();                 // right file
	
	//constructor
	Merge(List<Integer> input_selectedLineList, File left, File right){
		
		selectedLineList = input_selectedLineList;
		left_file = left.getData();                                 // get left file contents
		right_file = right.getData();								// get right file contents
		
	}
	
	// Check whether it is compared
	public boolean isMerged(){
		
		return true;
		
	}
	
	// Merge button clicked -> Turn on edit_mode !!!!!!! 
	
	public void MergeToLeft(){           // Copy to Left Method
		
		if(isMerged())                   // If "Merge" is possible
		{
			
			/* System.out.println("Merge To Left button clicked"); */
			
			// Integer iteration
			ListIterator<Integer> it = selectedLineList.listIterator();
			int index = 0;
			
			// Repeat the selected lines from beginning to end
			
			while(it.hasNext()){
				
				index = it.nextIndex();
								
				if(left_file.get(index) == right_file.get(index))    // If exist some same line
				{
					 System.out.println("The selected lines have the same contents."); 
				}
				else                                                 // else
				{
					left_file.remove(index);
					left_file.add(index, right_file.get(index));
				}
				
				it.next();
			}	

		}
		
	}
	
	public void MergeToRight(){           // Copy to Right Method
		
		if(isMerged())                   // If "Merge" is possible
		{
			
			/* System.out.println("Merge To Left button clicked"); */
			
			// Integer iteration
			ListIterator<Integer> it = selectedLineList.listIterator();
			int index = 0;
			
			// Repeat the selected lines from beginning to end
			
			while(it.hasNext()){
				
				index = it.nextIndex();
				System.out.println(index);
								
				if(right_file.get(index) == left_file.get(index))      // If exist some same line
				{
					System.out.println("The selected lines have the same contents."); 
				}
				else												  // else
				{
					right_file.remove(index);
					right_file.add(index, left_file.get(index));
				}
				
				it.next();
			}	


		}
    }
}