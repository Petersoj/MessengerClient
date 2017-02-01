package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import messenger.view.MessengerPanel;

public class MessagesPanel extends JPanel {
	
	private MessengerPanel messengerPanel;
	
	private Dimension prefferedSize;
	private BoxLayout boxLayout;
	
	public MessagesPanel(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.prefferedSize = new Dimension(5000, 9000);
		this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		this.setupPanel();
	}
	
	private void setupPanel(){
		this.setLayout(boxLayout);
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return prefferedSize;
	}
	
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
