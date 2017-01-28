package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.util.Utils;
import messenger.view.MessengerPanel;

public class TypePanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	private SpringLayout springLayout;
	
	private TypeArea typeArea;
	private AttachmentButton attachmentButton;
	
	private Color typeAreaColor;
	
	public TypePanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.typeAreaColor = new Color(230, 230, 230);
		this.springLayout = new SpringLayout();
		
		this.typeArea = new TypeArea(this);
		this.attachmentButton = new AttachmentButton(this);
		
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(typeArea);
		this.add(attachmentButton);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, typeArea, 16, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, typeArea, -50, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, typeArea, 18, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, attachmentButton, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, attachmentButton, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, attachmentButton, -7, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, attachmentButton, -37, SpringLayout.EAST, this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		
		this.drawTypePanelBackground(g2);
	}
	
	private void drawTypePanelBackground(Graphics2D g2){
		RoundRectangle2D textArea = new RoundRectangle2D.Float(10, 10, typeArea.getWidth() + 15, typeArea.getHeight() + 10, 26, 26);
		
		g2.setColor(typeAreaColor);
		g2.fill(textArea);
		
		g2.setColor(Color.LIGHT_GRAY); // same as Color.lightgray
		g2.draw(textArea);
	}
	
	public void setAttachmentButtonShown(boolean show){
		if(show){
			this.add(attachmentButton);
			this.setupLayout(); // Will reset constraints for button and text Area
		}else{ // hide attachmentButton
			this.remove(attachmentButton);
			springLayout.removeLayoutComponent(attachmentButton);
			springLayout.putConstraint(SpringLayout.EAST, typeArea, -15, SpringLayout.EAST, this);
		}
		this.revalidate();
		this.repaint();
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
	public TypeArea getTypeArea(){
		return typeArea;
	}
	
	public AttachmentButton getAttachmentButton(){
		return attachmentButton;
	}
}
