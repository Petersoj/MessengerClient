package messenger.view.messagepanel.messagepanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.user.User;
import messenger.user.users.ClientUser;
import messenger.util.Utils;
import messenger.view.messagepanel.MessagesPanel;
import messenger.view.messagepanel.messagepanels.components.FileButton;

public class FilePanel extends JPanel {
	
	private MessagesPanel messagesPanel;

	private User user;
	private Dimension preferredSize;
	private SpringLayout springLayout;
	private JLabel nameLabel;
	private JButton fileButton;
	
	public FilePanel(MessagesPanel messagesPanel, User user, String fileName){
		this.messagesPanel = messagesPanel;
		
		this.user = user;
		this.preferredSize = new Dimension(0, 0);
		this.springLayout = new SpringLayout();
		this.nameLabel = new JLabel(user.getUserName());
		this.fileButton = new FileButton(messagesPanel, fileName, user.getUserColor());
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(){
		DataController dataController = this.messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		this.nameLabel.setFont(dataController.getVerdanaFont().deriveFont(13f));
	}
	
	private void setupPanel(){
		this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
				updateSizing();
			}
			
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		
		this.setLayout(springLayout);
		this.add(nameLabel);
		this.add(fileButton);
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, this);
		if (user instanceof ClientUser) {
			springLayout.putConstraint(SpringLayout.EAST, nameLabel, -65, SpringLayout.EAST, this);
		}else{
			springLayout.putConstraint(SpringLayout.WEST, nameLabel, 65, SpringLayout.WEST, this);
		}
		
		springLayout.putConstraint(SpringLayout.NORTH, fileButton, 10, SpringLayout.SOUTH, nameLabel);
		if(user instanceof ClientUser){
			springLayout.putConstraint(SpringLayout.SOUTH, fileButton, 200, SpringLayout.NORTH, fileButton);
			springLayout.putConstraint(SpringLayout.EAST, fileButton, 0, SpringLayout.EAST, nameLabel);
			springLayout.putConstraint(SpringLayout.WEST, fileButton, -200, SpringLayout.EAST, fileButton);
		}else{
			springLayout.putConstraint(SpringLayout.SOUTH, fileButton, 200, SpringLayout.NORTH, fileButton);
			springLayout.putConstraint(SpringLayout.WEST, fileButton, 5, SpringLayout.WEST, nameLabel);
			springLayout.putConstraint(SpringLayout.EAST, fileButton, 200, SpringLayout.WEST, fileButton);
		}
		updateSizing();
	}
	
	private void updateSizing(){
		this.preferredSize.setSize(1, this.nameLabel.getPreferredSize().getHeight() + 35 + this.fileButton.getPreferredSize().getHeight());
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
		updateSizing();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
}
