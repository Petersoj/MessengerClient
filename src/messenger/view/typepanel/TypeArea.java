package messenger.view.typepanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import messenger.controller.DataController;
import messenger.packet.PacketMessage;
import messenger.user.ClientUserConnection;
import messenger.view.messagepanel.MessagePanel;

public class TypeArea extends JTextArea implements DocumentListener, ComponentListener, KeyListener {
	
	private TypePanel typePanel;
	
	private boolean attachmentButtonShown;
	private long messageTimer;
	
	public TypeArea(TypePanel typePanel){
		super();
		this.typePanel = typePanel;
		this.attachmentButtonShown = true;
		this.messageTimer = System.currentTimeMillis();
		
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
			
			e.consume(); // cancles the key pressed
			
			if(System.currentTimeMillis() - messageTimer > 300 && !this.getText().equals("")){ // Only let the client send message 300 ms apart
				
				DataController dataController = this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();

				MessagePanel messagePanel = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(), 
						dataController.getUserName(), dataController.getUserColor(), this.getText(), true);
				this.typePanel.getMessengerPanel().getMessagesPanel().addMessage(messagePanel);
				
				
				ClientUserConnection clientUserConnection = this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController()
						.getClientUser().getClientUserConnection();
				
				if(clientUserConnection != null && clientUserConnection.isConnected()){
					PacketMessage packetMessage = new PacketMessage(dataController.getUserName(), dataController.getUserColor(), this.getText());
					clientUserConnection.sendPacketMessage(packetMessage);
				}else{
					MessagePanel notSentMessage = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(), "Your message was not sent.");
					this.typePanel.getMessengerPanel().getMessagesPanel().addMessage(notSentMessage);
				}
				
				this.setText("");
				
				messageTimer = System.currentTimeMillis();
			}
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
