import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class compareTest {

	@Test
	public void test() {
		
		LinkedList<String> L = new LinkedList<String>();                 	// left data
		LinkedList<String> R = new LinkedList<String>(); 
		
		L.add("Hi");
		L.add("abc");
		L.add("seolhee");
		L.add("dd");
		L.add("ohohoh");
		L.add("yeah");
		
		R.add("Hi");
		R.add("seolhee");
		R.add("hehe");
		R.add("ohohoh");
		R.add("yeah");
		R.add("?!?");
		
		compare compare = new compare(L, R);
		
		List<Status> statusL = new LinkedList<Status>();
		List<Status> statusR = new LinkedList<Status>();
		
		statusL.add(Status.EQUAL);
		statusL.add(Status.ADD);
		statusL.add(Status.EQUAL);
		statusL.add(Status.CHANGE);
		statusL.add(Status.EQUAL);
		statusL.add(Status.EQUAL);
		statusL.add(Status.DELETE);

		statusR.add(Status.EQUAL);
		statusR.add(Status.DELETE);
		statusR.add(Status.EQUAL);
		statusR.add(Status.CHANGE);
		statusR.add(Status.EQUAL);
		statusR.add(Status.EQUAL);
		statusR.add(Status.ADD);
		
		assertEquals(statusL, compare.getStatus("L"));
		assertEquals(statusR, compare.getStatus("R"));
	}

}
