package messenger.view.messagepanel.messagepanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import messenger.controller.DataController;
import messenger.user.User;
import messenger.user.users.ClientUser;
import messenger.util.Utils;
import messenger.view.messagepanel.MessagesPanel;

public class MessagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;
	
	private User user;
	private Dimension preferredSize;
	private Font userNameFont;
	
	private String message;
	
	public MessagePanel(MessagesPanel messagesPanel, User user, String message){
		this.messagesPanel = messagesPanel;
		
		this.user = user;
		this.message = message;
		this.userNameFont = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController()
				.getDataController().getVerdanaFont().deriveFont(13f);
		
		this.preferredSize = new Dimension(1, 50);
		this.setBackground(Color.WHITE);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		// User Name
		g2.setFont(userNameFont);
		FontMetrics userNameFontMetrics = g2.getFontMetrics();
		String userName = user.getUserName();
		
		if(user instanceof ClientUser){
			g2.drawString(userName, this.getWidth() - userNameFontMetrics.stringWidth(userName) - 70, userNameFontMetrics.getHeight() + 5);
		}else{
			g2.drawString(userName, 70, userNameFontMetrics.getHeight() + 5);
		}
		
		g2.setFont(dataController.getVerdanaFont());
		
		// User Image
		BufferedImage image = Utils.drawRoundedSquareImage(user.getUserImage(), 50);
		int nameLabelHeight = userNameFontMetrics.getHeight() + 5;
		
		if(user instanceof ClientUser){
			g2.drawImage(image, this.getWidth() - 5, nameLabelHeight, this.getWidth() - 55, nameLabelHeight + 50,
					0, 0, image.getWidth(), image.getHeight(), null);
		}else{
			g2.drawImage(image, 5, nameLabelHeight, 55, nameLabelHeight + 50, 0, 0, image.getWidth(), image.getHeight(), null);
		}
		
		Rectangle messageBounds = null;
		
		// Message Area
		FontMetrics fontMetrics = g2.getFontMetrics();
		int messageWidth = fontMetrics.stringWidth(message);
		int fontHeight = fontMetrics.getAscent();
		
		if(messageWidth < this.getWidth() - 150){
			if(user instanceof ClientUser){
				messageBounds = new Rectangle(this.getWidth() - messageWidth - 70, userNameFontMetrics.getHeight() + 20, messageWidth, fontHeight);
			}else{ // Server User
				messageBounds = new Rectangle(70, userNameFontMetrics.getHeight() + 25, messageWidth, fontHeight);
			}
			this.drawMessageBackground(g2, messageBounds);
			g2.setColor(Color.WHITE);
			g2.drawString(message, (int) messageBounds.getX(), (int) (messageBounds.getY() + fontHeight - 2));
		}else{
			messageBounds = new Rectangle(4, 4, 4, 4);
		}
		
		this.preferredSize.setSize(1, nameLabelHeight + messageBounds.getHeight() + 35);
		System.out.println(nameLabelHeight + messageBounds.getHeight() + 25);
	}
	
	private void drawMessageBackground(Graphics2D g2, Rectangle messageBounds){
		g2.setColor(user.getUserColor().getColor());
		g2.fill(new RoundRectangle2D.Double(messageBounds.getX() - 7, messageBounds.getY() - 7, messageBounds.getWidth() + 14, messageBounds.getHeight() + 14, 20, 20));
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	
}
