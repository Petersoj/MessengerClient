package messenger.view.messagepanel.messagepanels.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import messenger.controller.DataController;
import messenger.util.MessengerColor;
import messenger.util.Utils;
import messenger.view.messagepanel.MessagesPanel;

public class FileButton extends JButton implements ActionListener {
	
	private MessagesPanel messagesPanel;
	
	private Dimension preferredSize;
	private String fileName;
	private Color userColor;
	
	public FileButton(MessagesPanel messagesPanel, String fileName, MessengerColor userColor){
		super();
		
		this.messagesPanel = messagesPanel;
		this.preferredSize = new Dimension(0, 0);
		this.fileName = fileName;
		this.userColor = userColor.applyAlpha(130);
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.addActionListener(this);
		
		this.setRolloverEnabled(true);
		
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.preferredSize.setSize(200, 200);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		DataController dataController = messagesPanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		BufferedImage image = dataController.getFileImage();
		int size = Math.min(this.getHeight() - 10, image.getHeight());
		g2.drawImage(image, 0, 10, this.getWidth(), size, 0, 0, image.getWidth(), image.getHeight(), null);
		
		g2.setFont(dataController.getVerdanaFont());
		
		FontMetrics fontMetrics = g2.getFontMetrics();
		int x = (this.getWidth() - fontMetrics.stringWidth(fileName)) / 2;
		int y = this.getHeight() - 30;
		
		g2.setColor(Color.BLACK);
		g2.drawString(fileName, x, y);
		
		if(this.getModel().isRollover()){
			RoundRectangle2D roundRectangle = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30);
			g2.setColor(userColor);
			g2.fill(roundRectangle);
			
			g2.setColor(Color.WHITE);
			Utils.drawCenteredText(g2, "Click to Download", this.getWidth(), this.getHeight());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	
	public String getFileName(){
		return fileName;
	}

}
