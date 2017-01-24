package messenger.util;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	
	public static BufferedImage getScaledImage(BufferedImage source, int width, int height){
	    BufferedImage resizedImage = new BufferedImage(width, height, source.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : source.getType());
	    Graphics2D g2 = resizedImage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(source, 0, 0, width, height, null);
	    g2.dispose();
	    return resizedImage;
	}
	
	public static byte[] bufferedImageToBytes(BufferedImage bufferedImage, String formatName) throws IOException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8000); // creates a buffer of 8 kilobytes.
		byte[] bytes = null;
		ImageIO.write(bufferedImage, formatName, outputStream);
		outputStream.flush(); // Gets rid of bytes that weren't filled in the buffer
		bytes = outputStream.toByteArray();
		outputStream.close();
		return bytes;
	}
	
	public static BufferedImage bytesToBufferedImage(byte[] imageBytes) throws IOException {
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(imageBytes);
		return ImageIO.read(byteInputStream);
	}
	
	public static void drawCenteredText(Graphics2D g2, String text, int width, int height){
		FontMetrics fontMetrics = g2.getFontMetrics();
		int x = (width - fontMetrics.stringWidth(text)) / 2;
		int y = ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
		g2.drawString(text, x, y);
	}
	
	
	
}
