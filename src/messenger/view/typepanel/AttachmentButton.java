package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import messenger.controller.DataController;
import messenger.util.Utils;

public class AttachmentButton extends JButton implements ActionListener {
	
	private TypePanel typePanel;
	
	public AttachmentButton(TypePanel typePanel){
		super();
		this.typePanel = typePanel;
		
		this.setupComponent();
	}
	
	public void setupComponent(){
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
		
		DataController dataController = typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		Color color = dataController.getUserColor().getColor();
		if(this.getModel().isRollover()){
			color = color.darker();
		}
		g2.setColor(color);
		g2.fillOval(0, 0, this.getWidth(), this.getHeight());
		
		BufferedImage attachmentImage = dataController.getAttachmentIcon();
		g2.drawImage(attachmentImage, 2, 2, this.getWidth() - 4, this.getHeight() - 4, 0, 0, attachmentImage.getWidth(), attachmentImage.getHeight(), null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int value = fileChooser.showOpenDialog(this.typePanel.getMessengerPanel());
		if(value == JFileChooser.APPROVE_OPTION){
			this.typePanel.getTypeArea().setText(fileChooser.getSelectedFile().getAbsolutePath());
		}else{
			this.typePanel.getTypeArea().setText("NO FILE SELECTED :(");
		}
	}
}
