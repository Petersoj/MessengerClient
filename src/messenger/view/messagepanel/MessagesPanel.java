package messenger.view.messagepanel;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import messenger.view.MessengerPanel;

public class MessagesPanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	private BoxLayout boxLayout;
	
	public MessagesPanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		this.setupPanel();
	}
	
	private void setupPanel(){
		this.setLayout(boxLayout);
		this.setBackground(Color.WHITE);
	}
	
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
