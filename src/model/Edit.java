package model;

public class Edit {
	
	private int editmode; // 0: uneditable, 1: editable
	public static final int EDITABLE = 1, UNEDITABLE = 0;
	
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