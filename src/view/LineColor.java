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
	public static LineColor getLineColor(int color) {
		switch(color) {
		case 0:
			return LineColor.LINE_BLANK;
		case 1:
			return LineColor.LINE_COLORED;
		default:
			return LINE_BLANK;
		}
	}
}
