package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import messenger.view.MessengerPanel;

public class TypePanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	private Dimension prefferedSize;
	
	public TypePanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.prefferedSize = new Dimension(20, 200); // width does not matter because BoxLayout ignores x
		
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Make every things smooth
		
		this.drawTypePanelBackground(g2);
		
	}
	
	private void setupPanel(){
		
	}

	private void setupLayout(){
		
	}
	
	private void setupListeners(){
		
	}
	
	private void drawTypePanelBackground(Graphics2D g2){
		Area background = new Area(new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight()));
		background.subtract(new Area(new RoundRectangle2D.Float(10, 10, this.getWidth() - 30, this.getHeight() - 20, 30, 30)));
		
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(background);
		
		g2.setColor(Color.BLACK); // same as Color.black
		g2.drawRoundRect(10, 10, this.getWidth() - 30, this.getHeight() - 20, 30, 30);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return prefferedSize;
	}

	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
}
