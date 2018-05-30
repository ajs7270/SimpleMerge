package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class fileTest {

	@Test
	void test() {
		File file = new File();
		file.load();
		LinkedList<String> data = (LinkedList<String>) file.getData();
		System.out.println(data);
		file.save();
		
	}

}
