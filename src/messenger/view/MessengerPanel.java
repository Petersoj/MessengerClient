package messenger.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import messenger.view.messagepanel.MessagesPanel;
import messenger.view.messagepanel.ScrollBarUI;
import messenger.view.typepanel.TypePanel;

public class MessengerPanel extends JPanel {
	
	private MessengerFrame messengerFrame;
	
	private SpringLayout springLayout;
	
	private MessagesPanel messagesPanel;
	private JScrollPane messageScrollPane;
	private TypePanel typePanel;
	
	public MessengerPanel(MessengerFrame messengerFrame){
		super();
		this.messengerFrame = messengerFrame;
		this.springLayout = new SpringLayout();
		this.messagesPanel = new MessagesPanel(this);
		this.messageScrollPane = new JScrollPane(messagesPanel);
		this.typePanel = new TypePanel(this);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(){
		this.messageScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		this.messageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.messageScrollPane.setBorder(BorderFactory.createEmptyBorder());
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(messageScrollPane);
		this.add(typePanel);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, typePanel, -46, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, typePanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, typePanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, typePanel, 0, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, messageScrollPane, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, messageScrollPane, 0, SpringLayout.NORTH, typePanel);
		springLayout.putConstraint(SpringLayout.EAST, messageScrollPane, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, messageScrollPane, 0, SpringLayout.WEST, this);
	}

	public MessengerFrame getMessengerFrame() {
		return messengerFrame;
	}

	public SpringLayout getSpringLayout() {
		return springLayout;
	}

	public MessagesPanel getMessagesPanel() {
		return messagesPanel;
	}

	public TypePanel getTypePanel() {
		return typePanel;
	}
	
	public JScrollPane getMessageScrollPane(){
		return messageScrollPane;
	}
}
