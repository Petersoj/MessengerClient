package messenger.view.messagepanel.messagepanels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.user.User;
import messenger.view.messagepanel.MessagesPanel;

public class FilePanel extends JPanel {
	
	private MessagesPanel messagesPanel;

	private User user;
	private Dimension preferredSize;
	private SpringLayout springLayout;
	private String fileName;
	
	
	public FilePanel(MessagesPanel messagesPanel, User user, String fileName){
		this.messagesPanel = messagesPanel;
		this.user = user;
		this.fileName = fileName;
		
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
