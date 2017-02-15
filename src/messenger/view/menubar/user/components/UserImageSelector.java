package messenger.view.menubar.user.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import messenger.controller.DataController;
import messenger.controller.MessengerController;
import messenger.util.MessengerColor;
import messenger.util.Utils;
import messenger.view.menubar.user.UserPreferencesPanel;

public class UserImageSelector extends JButton implements ActionListener {
	
	private UserPreferencesPanel userPreferencesPanel;
	
	private Color userColor;
	
	public UserImageSelector(UserPreferencesPanel userPreferencesPanel){
		this.userPreferencesPanel = userPreferencesPanel;
		this.userColor = userPreferencesPanel.getUserPreferencesDialog().getMessengerPanel()
				.getMessengerFrame().getMessengerController().getClientUser().getUserColor().applyAlpha(150);
		this.setupImageSelector();
	}
	
	private void setupImageSelector(){
		this.addActionListener(this);
		
		this.setRolloverEnabled(true);
		
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		
		DataController dataController = userPreferencesPanel.getUserPreferencesDialog().getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		BufferedImage userIcon = Utils.drawRoundedSquareImage(dataController.getUserIcon(), 150);
		g2.drawImage(userIcon, 0, 0, this.getWidth(), this.getHeight(), 0, 0, userIcon.getWidth(), userIcon.getHeight(), null);
		
		if(this.getModel().isRollover()){
			g2.setColor(userColor);
			g2.fillOval(0, 0, this.getWidth(), this.getHeight());
			
			g2.setColor(Color.WHITE);
			g2.setFont(dataController.getVerdanaFont());
			Utils.drawCenteredText(g2, "Click to Change", this.getWidth(), this.getHeight());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose an Image");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		
		int option = fileChooser.showOpenDialog(this.userPreferencesPanel.getUserPreferencesDialog());
		
		if(option == JFileChooser.APPROVE_OPTION){
			MessengerController messengerController = userPreferencesPanel.getUserPreferencesDialog().getMessengerPanel().getMessengerFrame().getMessengerController();
			
			File selectedFile = fileChooser.getSelectedFile();
			String fileName = selectedFile.getName();
			
			if(fileName.endsWith(".jpg") || fileName.endsWith(".png")){
				try {
					BufferedImage userImage = ImageIO.read(selectedFile);
					messengerController.getClientUser().setUserImage(userImage);
					messengerController.getDataController().setUserIcon(userImage);
					messengerController.getDataController().saveData(false);
					repaint();
				} catch (Exception error) {
					messengerController.getDebug().presentError("Error choosing a file!", error);
				}
			}
		}
	}
	
	public void setUserColor(MessengerColor color){
		this.userColor = color.applyAlpha(150);
	}
}
