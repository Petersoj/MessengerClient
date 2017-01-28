package messenger.view.userpreferences.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import messenger.util.Utils;
import messenger.view.userpreferences.UserPreferencesPanel;

public class UserImageSelector extends JComponent implements MouseListener {
	
	private UserPreferencesPanel userPreferencesPanel;
	
	private boolean hovering;
	
	public UserImageSelector(UserPreferencesPanel userPreferencesPanel){
		this.userPreferencesPanel = userPreferencesPanel;
		this.hovering = false;
		
		this.setupImageSelector();
	}
	
	private void setupImageSelector(){
		this.addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Utils.getChangedGraphics2D(g);
		if(hovering){
			
		}else{
			//g2.draw
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.hovering = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.hovering = false;
		repaint();
	}

}
