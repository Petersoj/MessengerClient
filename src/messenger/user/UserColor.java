package messenger.user;

import java.awt.Color;

public enum UserColor {
	
	RED(231, 76, 60), BLUE(52, 152, 219), GREEN(46, 204, 113), PURPLE(155, 89, 182), ORANGE(243, 156, 18);
	
	private Color color;
	
	private UserColor(int r, int g, int b){
		color = new Color(r, g, b);
	}
	
	public Color getColor(){
		return color;
	}
}
