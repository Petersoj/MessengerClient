package messenger.view.messagepanel;

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
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
