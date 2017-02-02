package messenger.view.messagepanel;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import messenger.user.users.ClientUser;
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
		
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco\nas\nasdf\nasdfdfasafds"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "aco\nas\nasdf\nasdfdfasafds"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "aco\nas\nasdf\nasdfdfasafds"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "aco\nas\nasdf\nasdfdfasafds"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "aco\nas\nasdf\nasdfdfasafds"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "Taco"));
		this.add(new MessagePanel(this, new ClientUser(messengerPanel.getMessengerFrame().getMessengerController()), "aco\nas\nasdf\nasdfdfasafds"));
		
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
