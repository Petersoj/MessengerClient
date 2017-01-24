package messenger.view.typepanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class TypeArea extends JTextArea implements ComponentListener {
	
	private TypePanel typePanel;
	
	public TypeArea(TypePanel typePanel){
		super();
		this.typePanel = typePanel;
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.setOpaque(false);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		
		this.addComponentListener(this);
		
		this.setFont(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getVerdanaFont());
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		this.updateSizing();
	}

	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
	
	
	public void updateSizing(){
		int northOffset = -(28 + this.getHeight());
		typePanel.getMessengerPanel().getSpringLayout().putConstraint(SpringLayout.NORTH, typePanel, northOffset, SpringLayout.SOUTH, typePanel.getMessengerPanel());
		typePanel.revalidate();
	}
}
