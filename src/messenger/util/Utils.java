package messenger.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Utils {
	
	public static BufferedImage getScaledImage(BufferedImage source, int width, int height){
	    BufferedImage resizedImage = new BufferedImage(width, height, source.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : source.getType());
	    Graphics2D g2 = resizedImage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(source, 0, 0, width, height, null);
	    g2.dispose();
	    return resizedImage;
	}
}
