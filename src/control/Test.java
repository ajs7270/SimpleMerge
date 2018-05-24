
public class Test {

	public static void main(String[] args) {
		Edit edit = new Edit();
		
		for (int i = 0; i < 6; i++)
		{
			System.out.println(edit.getEditMode());
			edit.changeEditMode();	
		}

	}
}