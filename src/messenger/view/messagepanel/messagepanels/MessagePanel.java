package messenger.view.messagepanel.messagepanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		
		this.springLayout = new SpringLayout();
		
		this.messageArea = new JTextArea(message);
		this.nameLabel = new JLabel(user.getUserName());
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(){
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();

		this.messageArea.setOpaque(false);
		this.messageArea.setFont(dataController.getVerdanaFont());
		this.messageArea.setEditable(false);
		this.messageArea.setLineWrap(true);
		this.messageArea.setWrapStyleWord(true);
		
		this.nameLabel.setFont(dataController.getVerdanaFont());
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(messageArea);
		this.add(nameLabel);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, nameLabel, (int) nameLabel.getPreferredSize().getHeight(), SpringLayout.NORTH, nameLabel);
		if(user instanceof ClientUser){
			springLayout.putConstraint(SpringLayout.EAST, nameLabel, -60, SpringLayout.EAST, this);
			springLayout.putConstraint(SpringLayout.WEST, nameLabel, -(int) nameLabel.getPreferredSize().getWidth(), SpringLayout.EAST, nameLabel);
		}else{
			springLayout.putConstraint(SpringLayout.WEST, nameLabel, 60, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, nameLabel, (int) nameLabel.getPreferredSize().getWidth(), SpringLayout.WEST, nameLabel);
		}
		
		springLayout.putConstraint(SpringLayout.NORTH, messageArea, 0, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, messageArea, 0, SpringLayout.WEST, nameLabel);
		
		this.preferredSize = new Dimension(1, (int) messageArea.getPreferredSize().getHeight() + (int)this.nameLabel.getPreferredSize().getHeight());
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);

		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();

		BufferedImage userImage = user.getUserImage();
		
		int widthHeight = Math.min(this.getHeight(), 50);
		g2.drawImage(userImage, 5, (int)nameLabel.getHeight(), widthHeight, widthHeight, 0, 0, userImage.getWidth(), userImage.getHeight(), null);
		
		Rectangle messageAreaBounds = this.messageArea.getBounds();
		RoundRectangle2D roundedRect = new RoundRectangle2D.Double(messageAreaBounds.getX() - 3, messageAreaBounds.getY() - 3,
				messageAreaBounds.getWidth() + 6, messageAreaBounds.getHeight() + 6, 10, 10);
		g2.setColor(user.getUserColor().getColor());
		g2.fill(roundedRect);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	
}
