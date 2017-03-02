package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.util.MessengerColor;
import messenger.util.Utils;

public class MessagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;
	
	private Dimension preferredSize;
	private SpringLayout springLayout;
	
	private boolean clientUser;
	private MessengerColor userColor;
	private boolean isUserMessage;
	
	private JLabel nameLabel;
	private JTextArea messageArea;
	
	public MessagePanel(MessagesPanel messagesPanel, String userName, MessengerColor userColor, String message, boolean clientUser){
		this.messagesPanel = messagesPanel;

		this.preferredSize = new Dimension(0, 0);
		this.springLayout = new SpringLayout();
		
		this.clientUser = clientUser;
		this.userColor = userColor;
		this.isUserMessage = true;
		
		this.nameLabel = new JLabel(userName);
		this.messageArea = new JTextArea(message);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	public MessagePanel(MessagesPanel messagesPanel, String systemMessage){
		this.messagesPanel = messagesPanel;

		this.preferredSize = new Dimension(0, 0);
		this.springLayout = new SpringLayout();
		this.isUserMessage = false;
		
		this.messageArea = new JTextArea(systemMessage);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(){
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		if(isUserMessage){
			this.nameLabel.setFont(dataController.getVerdanaFont().deriveFont(13f));
			
			this.messageArea.setOpaque(false);
			this.messageArea.setEditable(false);
			this.messageArea.setLineWrap(true);
			this.messageArea.setWrapStyleWord(true);
			this.messageArea.setFont(dataController.getVerdanaFont());
		}else{
			this.messageArea.setOpaque(false);
			this.messageArea.setEditable(false);
			this.messageArea.setForeground(Color.GRAY);
			this.messageArea.setFont(dataController.getVerdanaFont().deriveFont(13f));
		}
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		
		this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {
				updateSizing();
				/*
				 Due to limitations in JTextArea, I cannot make it resize according to it's line count and size
				 I have used a ton of different methods and none of them were successfull
				 So when the screen is resized, some messages may go off the screen
				 */
			}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		
		
		if(isUserMessage){
			this.add(nameLabel);
		}
		this.add(messageArea);
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		if(isUserMessage){ //  Name Label constraints
			springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, this);
			if(clientUser) {
				springLayout.putConstraint(SpringLayout.EAST, nameLabel, -10, SpringLayout.EAST, this);
			}else{
				springLayout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, this);
			}
		}
		
		if(isUserMessage){ // text area constraints
			int stringWidth = messageArea.getFontMetrics(messageArea.getFont()).stringWidth(messageArea.getText());
			springLayout.putConstraint(SpringLayout.NORTH, messageArea, 10, SpringLayout.SOUTH, nameLabel);
			if(clientUser){
				springLayout.putConstraint(SpringLayout.EAST, messageArea, -5, SpringLayout.EAST, nameLabel);
				if(stringWidth + 150 < messagesPanel.getWidth()){
					springLayout.putConstraint(SpringLayout.WEST, messageArea, -stringWidth, SpringLayout.EAST, messageArea);
				}else{
					springLayout.putConstraint(SpringLayout.WEST, messageArea, 50, SpringLayout.WEST, this);		
				}
			}else{
				springLayout.putConstraint(SpringLayout.WEST, messageArea, 5, SpringLayout.WEST, nameLabel);
				if(stringWidth + 50 < messagesPanel.getWidth()){
					springLayout.putConstraint(SpringLayout.EAST, messageArea, stringWidth, SpringLayout.WEST, messageArea);
				}else{
					springLayout.putConstraint(SpringLayout.EAST, messageArea, -50, SpringLayout.EAST, this);
				}
			}
		}else{ // small system message
			springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, messageArea, 0, SpringLayout.VERTICAL_CENTER, this);
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageArea, 0, SpringLayout.HORIZONTAL_CENTER, this);
		}
		updateSizing();
	}
	
	public void updateSizing(){
		if(isUserMessage){
			this.preferredSize.setSize(1, this.nameLabel.getPreferredSize().getHeight() + 35 + this.messageArea.getPreferredSize().getHeight());
		}else{
			this.preferredSize.setSize(1, 25 + this.messageArea.getPreferredSize().getHeight());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(isUserMessage && userColor != null){
			Graphics2D g2 = Utils.getChangedGraphics2D(g);
			Rectangle messageBounds = messageArea.getBounds();
			
			g2.setColor(userColor.getColor());
			g2.fill(new RoundRectangle2D.Double(messageBounds.getX() - 7, messageBounds.getY() - 7, 
					messageBounds.getWidth() + 14, messageBounds.getHeight() + 14, 20, 20));
		}
		updateSizing();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
}
