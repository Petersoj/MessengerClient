package messenger.view.messagepanel.messagepanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.user.User;
import messenger.user.users.ClientUser;
import messenger.util.Utils;
import messenger.view.messagepanel.MessagesPanel;

public class MessagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;
	
	private User user;
	private Dimension preferredSize;
	private SpringLayout springLayout;
	private JLabel nameLabel;
	private JTextArea messageArea;
	
	public MessagePanel(MessagesPanel messagesPanel, User user, String message){
		this.messagesPanel = messagesPanel;
		
		this.user = user;
		this.nameLabel = new JLabel(user.getUserName());
		this.messageArea = new JTextArea(message);
		this.springLayout = new SpringLayout();
		
		this.preferredSize = new Dimension(0, 0);
		
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupPanel(){
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		this.messageArea.setOpaque(false);
		this.messageArea.setEditable(false);
		this.messageArea.setLineWrap(true);
		this.messageArea.setWrapStyleWord(true);
		this.messageArea.setFont(dataController.getVerdanaFont());
		
		this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
				preferredSize.setSize(1, nameLabel.getPreferredSize().getHeight() + 35 + messageArea.getPreferredSize().getHeight());
				/*
				 Due to limitations in JTextArea I canot make it resize according to it's line count and size
				 I have used a ton of different methods and none of them were successfull
				 So when the screen is resized, some messages may go off the screen
				 */
			}
			
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		
		this.setLayout(springLayout);
		this.add(nameLabel);
		this.add(messageArea);
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, this);
		if (user instanceof ClientUser) {
			springLayout.putConstraint(SpringLayout.EAST, nameLabel, -65, SpringLayout.EAST, this);
		}else{
			springLayout.putConstraint(SpringLayout.WEST, nameLabel, 65, SpringLayout.WEST, this);
		}
		
		int stringWidth = messageArea.getFontMetrics(messageArea.getFont()).stringWidth(messageArea.getText());
		springLayout.putConstraint(SpringLayout.NORTH, messageArea, 10, SpringLayout.SOUTH, nameLabel);
		if(user instanceof ClientUser){
			springLayout.putConstraint(SpringLayout.EAST, messageArea, -5, SpringLayout.EAST, nameLabel);
			if(stringWidth + 50 < messagesPanel.getWidth()){
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
		this.preferredSize.setSize(1, this.nameLabel.getPreferredSize().getHeight() + 35 + this.messageArea.getPreferredSize().getHeight());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		
		BufferedImage image = Utils.drawRoundedSquareImage(user.getUserImage(), 50);
		if(user instanceof ClientUser){
			g2.drawImage(image, this.getWidth() - 5, nameLabel.getHeight(), this.getWidth() - 55, nameLabel.getHeight() + 50,
					0, 0, image.getWidth(), image.getHeight(), null);
		}else{
			g2.drawImage(image, 5, nameLabel.getHeight(), 55, nameLabel.getHeight() + 50, 0, 0, image.getWidth(), image.getHeight(), null);
		}
		
		Rectangle messageBounds = messageArea.getBounds();
		
		g2.setColor(user.getUserColor().getColor());
		g2.fill(new RoundRectangle2D.Double(messageBounds.getX() - 7, messageBounds.getY() - 7, messageBounds.getWidth() + 14, messageBounds.getHeight() + 14, 20, 20));
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
}
