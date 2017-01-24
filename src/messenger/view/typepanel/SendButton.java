package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class SendButton extends JButton implements ActionListener {
	
	private TypePanel typePanel;
	
	public SendButton(TypePanel typePanel){
		this.typePanel = typePanel;
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.addActionListener(this);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		RoundRectangle2D backGround = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 25, 25);
		g2.setColor(Color.BLUE);
		g2.fill(backGround);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
