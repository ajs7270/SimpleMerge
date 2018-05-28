package view;

public enum LineColor {
	LINE_BLANK(0), LINE_COLORED(1);
	final private int color;
	private LineColor(int color) {
		this.color = color;
	}
	public int getColor() {
		return color;
	}
	
}
