package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) { }
			
			@Override
			public void componentResized(ComponentEvent e) {
				JScrollPane scrollPane = messengerPanel.getMessagesScrollPane();
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
			}
			
			@Override
			public void componentMoved(ComponentEvent e) { }
			@Override
			public void componentHidden(ComponentEvent e) { }
		});
	}
	
	private void setupPanel(){
		this.setLayout(boxLayout);
		this.setBackground(Color.WHITE);
	}
	
	public void addMessage(MessagePanel messagePanel){
		this.add(messagePanel);
		JScrollPane scrollPane = this.messengerPanel.getMessagesScrollPane();
		scrollPane.setViewportView(this);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public void addFileMessage(FilePanel filePanel){
		this.add(filePanel);
		JScrollPane scrollPane = this.messengerPanel.getMessagesScrollPane();
		scrollPane.setViewportView(this);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
