package messenger.view.messagepanel;

import java.awt.Graphics;

import javax.swing.JPanel;

import messenger.view.MessengerPanel;

public class MessagesPanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	public MessagesPanel(MessengerPanel messengerPanel){
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
		
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
