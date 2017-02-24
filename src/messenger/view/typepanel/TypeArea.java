package messenger.view.typepanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import messenger.packet.packets.PacketMessage;
import messenger.packet.packets.PacketMessage.PacketMessageType;
import messenger.user.users.ServerUser;
import messenger.view.messagepanel.messagepanels.FilePanel;
import messenger.view.messagepanel.messagepanels.MessagePanel;

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
			if(this.getText().equals("file")){
				this.typePanel.getMessengerPanel().getMessagesPanel().addFileMessage(new FilePanel(this.typePanel.getMessengerPanel().getMessagesPanel(),
						new ServerUser(this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController(), 12), "asdf.jpg"));
				this.setText("");
			}else if(this.getText().equals("filec")){
				this.typePanel.getMessengerPanel().getMessagesPanel().addFileMessage(new FilePanel(this.typePanel.getMessengerPanel().getMessagesPanel(),
						this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser(), "asdf.jpg"));
				this.setText("");
			}else{
				MessagePanel messagePanel = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(),
						this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser(), this.getText());
				/*
				 * MessagePanel messagePanel = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(),
						this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser(), this.getText());
				 */
				
				PacketMessage packetMessage = new PacketMessage(PacketMessageType.MESSAGE);
				packetMessage.setMessage(this.getText());
				this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser().getClientUserConnection().sendPacket(packetMessage);
				
				this.typePanel.getMessengerPanel().getMessagesPanel().addMessage(messagePanel);
				this.setText("");
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
