package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.view.MessengerPanel;

public class TypePanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	private SpringLayout springLayout;
	
	private TypeArea typeArea;
	private SendButton sendButton;
	
	private Color typeAreaColor;
	
	public TypePanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.typeAreaColor = new Color(230, 230, 230);
		this.springLayout = new SpringLayout();
		
		this.typeArea = new TypeArea(this);
		this.sendButton = new SendButton(this);
		
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(typeArea);
		this.add(sendButton);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, typeArea, 14, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, typeArea, -130, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, typeArea, 18, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, sendButton, -35, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, sendButton, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, sendButton, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, sendButton, 20, SpringLayout.EAST, typeArea);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Make every things smooth
		
		this.drawTypePanelBackground(g2);
	}
	
	private void drawTypePanelBackground(Graphics2D g2){
		RoundRectangle2D textArea = new RoundRectangle2D.Float(10, 10, this.getWidth() - 130, this.getHeight() - 20, 26, 26);
		
		g2.setColor(typeAreaColor);
		g2.fill(textArea);
		
		g2.setColor(Color.LIGHT_GRAY); // same as Color.lightgray
		g2.draw(textArea);
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
}
