package messenger.view.typepanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import messenger.util.MessengerColor;
import messenger.view.messagepanel.MessagePanel;

public class TypeArea extends JTextArea implements DocumentListener, ComponentListener, KeyListener {
	
	private TypePanel typePanel;
	
	private boolean attachmentButtonShown;
	
	public TypeArea(TypePanel typePanel){
		super();
		this.typePanel = typePanel;
		this.attachmentButtonShown = true;
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.setOpaque(false);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		
		this.addComponentListener(this);
		this.getDocument().addDocumentListener(this);
		this.addKeyListener(this);
		
		this.setFont(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getVerdanaFont());
		
		this.setCaretColor(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getUserColor().getColor());
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.updateAttachmentButtonVisiblity();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.updateAttachmentButtonVisiblity();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.updateAttachmentButtonVisiblity();
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
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			e.consume();
			
			MessagePanel messagePanel = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(), "taco", MessengerColor.BLUE, this.getText(), false);
			this.typePanel.getMessengerPanel().getMessagesPanel().addMessage(messagePanel);
			
			this.setText("");
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) { }
	
	
	public void updateAttachmentButtonVisiblity(){
		if(this.getText().isEmpty() && !attachmentButtonShown){
			attachmentButtonShown = true;
			typePanel.setAttachmentButtonShown(true);
		}else if(attachmentButtonShown){
			attachmentButtonShown = false;
			typePanel.setAttachmentButtonShown(false);
		}
	}
	
	public void updateSizing(){
		int northOffset = -(30 + this.getHeight());
		typePanel.getMessengerPanel().getSpringLayout().putConstraint(SpringLayout.NORTH, typePanel, northOffset, SpringLayout.SOUTH, typePanel.getMessengerPanel());
		typePanel.revalidate();
	}
}
