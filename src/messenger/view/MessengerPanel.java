package messenger.view;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.view.messagepanel.MessagesPanel;
import messenger.view.typepanel.TypePanel;

public class MessengerPanel extends JPanel {
	
	private MessengerFrame messengerFrame;
	
	private SpringLayout springLayout;
	
	private MessagesPanel messagesPanel;
	private TypePanel typePanel;
	
	public MessengerPanel(MessengerFrame messengerFrame){
		super();
		this.messengerFrame = messengerFrame;
		this.springLayout = new SpringLayout();
		this.messagesPanel = new MessagesPanel(this);
		this.typePanel = new TypePanel(this);
		
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(messagesPanel);
		this.add(typePanel);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, typePanel, -46, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, typePanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, typePanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, typePanel, 0, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, messagesPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, messagesPanel, 0, SpringLayout.NORTH, typePanel);
		springLayout.putConstraint(SpringLayout.EAST, messagesPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, messagesPanel, 0, SpringLayout.WEST, this);
	}

	public MessengerFrame getMessengerFrame() {
		return messengerFrame;
	}

	public SpringLayout getSpringLayout() {
		return springLayout;
	}

	public MessagesPanel getMessagesPanel() {
		return messagesPanel;
	}

	public TypePanel getTypePanel() {
		return typePanel;
	}
}
