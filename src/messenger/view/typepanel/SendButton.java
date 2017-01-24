package messenger.view.typepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public class SendButton extends JButton implements ActionListener, MouseListener {
	
	private TypePanel typePanel;
	
	public SendButton(TypePanel typePanel){
		this.typePanel = typePanel;
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.addActionListener(this);
		this.addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		RoundRectangle2D backGround = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 25, 25);
		ButtonModel model = this.getModel();
		if(model.isPressed()){
			
		}else if(model.isRollover()){
			
		}else{
			
		}
		g2.fill(backGround);
		
		g2.setFont(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getVerdanaFont());
		g2.setColor(Color.BLUE);
		g2.drawString("Send", this.getWidth()/2, this.getHeight()/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
