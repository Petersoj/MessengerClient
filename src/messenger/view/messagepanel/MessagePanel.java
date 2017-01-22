package messenger.view.messagepanel;

import java.awt.Dimension;

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
	public Dimension getPreferredSize() {
		return new Dimension(20, 400);
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
