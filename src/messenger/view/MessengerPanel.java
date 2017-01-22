package messenger.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import messenger.view.messagepanel.MessagePanel;
import messenger.view.typepanel.TypePanel;

public class MessengerPanel extends JPanel {
	
	private MessengerFrame messengerFrame;
	
	private BoxLayout boxLayout;
	
	private MessagePanel messagePanel;
	private TypePanel typePanel;
	
	public MessengerPanel(MessengerFrame messengerFrame){
		super();
		this.messengerFrame = messengerFrame;
		this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.messagePanel = new MessagePanel(this);
		this.typePanel = new TypePanel(this);
		
		this.setupPanel();
	}
	
	public void setupPanel(){
		this.setLayout(boxLayout);
		this.add(messagePanel);
		this.add(typePanel);
	}

	public MessengerFrame getMessengerFrame() {
		return messengerFrame;
	}

	public BoxLayout getBoxLayout() {
		return boxLayout;
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

	public TypePanel getTypePanel() {
		return typePanel;
	}
}
