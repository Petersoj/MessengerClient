package messenger.view.messagepanel.messagepanels;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.user.User;
import messenger.view.messagepanel.MessagesPanel;

public class ImagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;

	private User user;
	private Dimension preferredSize;
	private SpringLayout springLayout;
	private BufferedImage image;
	
	public ImagePanel(MessagesPanel messagesPanel, User user, BufferedImage image){
		this.messagesPanel = messagesPanel;
		this.user = user;
		this.image = image;
		
		this.springLayout = new SpringLayout();
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void setupComponents(){
		
	}
	
	private void setupPanel(){
		
	}
	
	private void setupLayout(){
		
	}
	
	private void setupListeners(){
		
	}
}
