package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import messenger.util.Utils;

public class ScrollBarUI extends BasicScrollBarUI {
	
	public ScrollBarUI(){
		super();
	}
	
	private JButton createZeroButton() {
        JButton button = new JButton("zero button");
        Dimension zeroDim = new Dimension(0,0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }
	
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return createZeroButton();
	}
	@Override
	protected JButton createIncreaseButton(int orientation) {
		return createZeroButton();
	}
	
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
	}
	
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		RoundRectangle2D roundRectangle = new RoundRectangle2D.Double(thumbBounds.getX() + 4, thumbBounds.getY(), thumbBounds.getWidth() - 8, thumbBounds.getHeight(), 8, 8);
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(roundRectangle);
	}

}
