package messenger.view.messagepanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.user.User;
import messenger.util.Utils;

public class MessagePanel extends JPanel {
	
	private MessagesPanel messagesPanel;
	
	private User user;
	private SpringLayout springLayout;
	private Dimension preferredSize;
	private JTextArea messageArea;
	
	public MessagePanel(MessagesPanel messagesPanel, User user, String message){
		this.messagesPanel = messagesPanel;
		this.user = user;
		
		this.springLayout = new SpringLayout();
		this.messageArea = new JTextArea();
		
		this.setupComponents(message);
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(String message){
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();

		this.messageArea.setOpaque(false);
		this.messageArea.setFont(dataController.getVerdanaFont());
		this.messageArea.setEditable(false);
		this.messageArea.setLineWrap(true);
		this.messageArea.setWrapStyleWord(true);
		this.messageArea.setText(message);
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(messageArea);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, messageArea, 25, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, messageArea, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, messageArea, -5, SpringLayout.EAST, this);
		
		this.preferredSize = new Dimension(1, (int) this.messageArea.getPreferredSize().getHeight() + 30);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);

		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();

		g2.setFont(dataController.getVerdanaFont());
		g2.drawString("asdfasdfasdf", 50, (g2.getFontMetrics().getHeight() + g2.getFontMetrics().getAscent()));
		
		g2.setColor(Color.BLUE);
		g2.fillRoundRect((int)this.messageArea.getBounds().getX(), (int)this.messageArea.getBounds().getY(), this.messageArea.getWidth() + 5, this.messageArea.getHeight() + 5, 5, 5);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	
}
