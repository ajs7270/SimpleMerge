package view;

public enum Color {
	LINE_BLANK(0), LINE_COLORED(1);
	final private int color;
	private Color(int color) {
		this.color = color;
	}
	public int getColor() {
		return color;
	}
	
}
