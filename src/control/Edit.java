
public class Edit {
	
	private int editmode; // 0: uneditable, 1: editable
	
	public Edit() {
		editmode = 0;
	}
	
	public int getEditMode(){
		return editmode;
	}
	
	public void setEditMode(int state) {
		editmode = state;
	}
	
	public void changeEditMode() {
		editmode += 1;
		editmode %= 2;
	}
}