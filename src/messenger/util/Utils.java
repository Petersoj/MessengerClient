package messenger.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Utils {
	
	public static BufferedImage getScaledImage(BufferedImage source, int width, int height){
	    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(source, 0, 0, width, height, null);
	    g2.dispose();
	    return resizedImage;
	}

	public static void drawCenteredText(Graphics2D g2, String text, int width, int height){
		FontMetrics fontMetrics = g2.getFontMetrics();
		int x = (width - fontMetrics.stringWidth(text)) / 2;
		int y = ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
		g2.drawString(text, x, y);
	}
	
	public static Graphics2D getChangedGraphics2D(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Make every things smooth
			return g2;
		}else{
			return null;
		}
	}
	
	public static BufferedImage getNewResizedImage(BufferedImage image, int width, int height){
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resized.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
		g2.dispose();
		return resized;
	}
	
	public static BufferedImage drawRoundedSquareImage(BufferedImage image, int size){
		BufferedImage rounded = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = rounded.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);

		Area area = new Area(new Rectangle2D.Float(0, 0, size, size));
		area.subtract(new Area(new Ellipse2D.Float(0, 0, size, size)));

		g2.drawImage(image, 0, 0, rounded.getWidth(), rounded.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
		g2.setComposite(AlphaComposite.Clear);
		g2.fill(area);

		g2.dispose();
		return rounded;
	}
	
}
