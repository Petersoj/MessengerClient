package messenger.view.typepanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AttachmentButton extends JButton implements ActionListener {
	
	private TypePanel typePanel;
	
	public AttachmentButton(TypePanel typePanel){
		this.typePanel = typePanel;
		this.addActionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
