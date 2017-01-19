package messenger.user;

import java.awt.Color;

public enum UserColor {
	
	RED(231, 76, 60), BLUE(52, 152, 219), GREEN(46, 204, 113), PURPLE(155, 89, 182), ORANGE(243, 156, 18);
	
	private Color color;
	
	private UserColor(int r, int g, int b){
		color = new Color(r, g, b, 130);
	}
	
	public Color getColor(){
		return color;
	}
	
	public Color getSofterColor(){
		int r = color.getRed() - 50;
		int g = color.getGreen() - 50;
		int b = color.getBlue() - 50;
		return new Color((r < 0 ? 0 : r), (g < 0 ? 0 : g), (b < 0 ? 0 : b));
	}
}
