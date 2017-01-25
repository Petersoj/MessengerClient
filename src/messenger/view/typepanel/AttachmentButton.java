package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonModel;
import javax.swing.JButton;

import messenger.controller.DataController;

public class AttachmentButton extends JButton implements ActionListener, MouseListener {
	
	private TypePanel typePanel;
	
	private boolean hoveredOver;
	
	public AttachmentButton(TypePanel typePanel){
		this.typePanel = typePanel;
		this.hoveredOver = false;
		
		this.setupComponent();
	}
	
	public void setupComponent(){
		this.addActionListener(this);
		this.addMouseListener(this);
		
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		DataController dataController = typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		Color color = dataController.getUserColor().getColor();
		ButtonModel model = (ButtonModel) this.getModel();
		if(hoveredOver){
			color = color.darker();
		}
		g2.setColor(color);
		g2.fillOval(0, 0, this.getWidth(), this.getHeight());
		
		BufferedImage attachmentImage = dataController.getAttachmentIcon();
		g2.drawImage(attachmentImage, 2, 2, this.getWidth() - 4, this.getHeight() - 4, 0, 0, attachmentImage.getWidth(), attachmentImage.getHeight(), null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.hoveredOver = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.hoveredOver = false;
		repaint();
	}

}
