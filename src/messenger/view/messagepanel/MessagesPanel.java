package messenger.view.messagepanel;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import messenger.view.MessengerPanel;
import messenger.view.messagepanel.messagepanels.FilePanel;
import messenger.view.messagepanel.messagepanels.MessagePanel;

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
	
	public void addMessage(MessagePanel messagePanel){
		this.add(messagePanel);
		JScrollPane scrollPane = this.messengerPanel.getMessagesScrollPane();
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		scrollPane.setViewportView(this);
	}
	
	public void addFileMessage(FilePanel filePanel){
		this.add(filePanel);
		JScrollPane scrollPane = this.messengerPanel.getMessagesScrollPane();
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		scrollPane.setViewportView(this);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
