package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import messenger.view.MessengerPanel;

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
				JScrollBar scrollBar = messengerPanel.getMessagesScrollPane().getVerticalScrollBar();
				scrollBar.setValue(scrollBar.getMaximum());
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

	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}