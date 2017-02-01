package messenger.view.messagepanel;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.user.User;

public class MessagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;
	
	private User user;
	private SpringLayout springLayout;
	
	public MessagePanel(MessagesPanel messagesPanel, User user){
		this.messagesPanel = messagesPanel;
		this.user = user;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
	

}
