package messenger.view.messagepanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import messenger.view.MessengerPanel;

public class MessagePanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	public MessagePanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void setupPanel(){
		
	}

	private void setupLayout(){
		
	}
	
	private void setupListeners(){
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// FOR TESTING
		BufferedImage image = messengerPanel.getMessengerFrame().getMessengerController().getDataController().getMessengerIcon();
		((Graphics2D) g).drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
