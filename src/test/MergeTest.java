package test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.LinkedList;
import org.junit.Test;

import model.Merge;

public class MergeTest {

	@Test
	public void test() {
		
		
		List<String> left_file = new LinkedList<String>();                  // left file
		List<String> right_file = new LinkedList<String>();                 // right file
			
		List<Integer> selected_listcase1 = new LinkedList<Integer>();
		List<Integer> selected_listcase2 = new LinkedList<Integer>();
		List<Integer> selected_listcase3 = new LinkedList<Integer>();
		List<Integer> selected_listcase4 = new LinkedList<Integer>();
	
		// Create Merge object
		Merge merge = new Merge();
		
		// create left file's string
		String a = "package UnitTest";
		String b = "import static org.junit.Assert";
		String c = "public class mergetest";
		
		left_file.add(a);
		left_file.add(b);
		left_file.add(c);
		
		// create right file's string
		String d = "package Unittest";
		String e = "";
		String f = "public class Mergetest";
		
		right_file.add(d);
		right_file.add(e);
		right_file.add(f);
		

		
		// case 1  : 1's line drag
		selected_listcase1.add(0);
		merge.setFiles(selected_listcase1, left_file, right_file);
		merge.MergeToRight();                // merge left -> right	
		assertEquals(left_file.get(0), right_file.get(0));
		
		
		// case 2 : 2's line drag
		selected_listcase2.add(1);
		merge.setFiles(selected_listcase2, left_file, right_file);
		merge.MergeToRight();                 // merge left -> right
		assertEquals(left_file.get(1), right_file.get(1));
		
		// case 3 : 3's line drag
		selected_listcase3.add(2);
		merge.setFiles(selected_listcase3, left_file, right_file);
		merge.MergeToRight();                 // merge left -> right
		assertEquals(left_file.get(2), right_file.get(2));
		
		// case 4 : same line drag
		selected_listcase4.add(0);
		selected_listcase4.add(1);
		selected_listcase4.add(2);
		merge.setFiles(selected_listcase4, left_file, right_file);
		merge.MergeToLeft();		
		assertEquals(left_file, right_file);

		
	}

}
