package messenger.view.menubar.user.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

import messenger.controller.DataController;
import messenger.util.MessengerColor;
import messenger.util.Utils;

public class UserColorButton extends JButton implements ActionListener {
	
	private UserColorPanel userColorPanel;
	
	private MessengerColor userColor;
	private BasicStroke circleStroke;
	private Dimension prefferedSize;
	
	public UserColorButton(UserColorPanel userColorPanel, MessengerColor userColor){
		super();
		this.userColorPanel = userColorPanel;
		this.userColor = userColor;
		this.prefferedSize = new Dimension(30, 30);
		this.circleStroke = new BasicStroke(2f);
		
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
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		
		int width = this.getWidth();
		int height = this.getHeight();
		if(this.getWidth() != this.getHeight()){ // Dimensions are not square
			width = Math.min(this.getWidth(), this.getHeight());
			height = Math.min(this.getWidth(), this.getHeight());
		}
		g2.setColor(userColor.getColor());
		g2.fillOval(0, 0, width, height);
		
		DataController dataController = this.userColorPanel.getUserPreferencesPanel().getUserPreferencesDialog().getMessengerPanel()
				.getMessengerFrame().getMessengerController().getDataController();
		boolean selectedColor = dataController.getUserColor() == userColor ? true : false;
		
		if(this.getModel().isRollover() || selectedColor){
			g2.setStroke(circleStroke);
			g2.setColor(Color.WHITE);
			Shape circle = new Ellipse2D.Float(3, 3, width - 6.5f, height - 6.5f);
			g2.draw(circle);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long currentTime = System.currentTimeMillis();
		DataController dataController = this.userColorPanel.getUserPreferencesPanel().getUserPreferencesDialog().getMessengerPanel()
				.getMessengerFrame().getMessengerController().getDataController();
		if((currentTime - this.userColorPanel.getLastColorSave()) > 1000 && dataController.getUserColor() != userColor){ // This prevents spamming of the button :(
			dataController.setUserColor(userColor);
			dataController.getMessengerController().getClientUser().setUserColor(userColor);
			
			this.userColorPanel.getUserPreferencesPanel().getUserPreferencesDialog().getMessengerPanel().getTypePanel().getTypeArea().setCaretColor(userColor.getColor());
			this.userColorPanel.getUserPreferencesPanel().getNameField().setCaretColor(userColor.getColor());
			this.userColorPanel.getUserPreferencesPanel().getUserPreferencesDialog().getMessengerPanel().getMessengerFrame()
				.getMessengerMenuBar().getConnectOptionDialog().getConnectOptionPanel().setTextFieldCaretColors(userColor);
			this.userColorPanel.getUserPreferencesPanel().getUserImageSelector().setUserColor(userColor);
			
			dataController.saveData(true);
			
			this.userColorPanel.setLastColorSave(currentTime);
			this.userColorPanel.repaint();
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return prefferedSize;
	}
}
