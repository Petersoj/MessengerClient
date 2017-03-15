package messenger.view.typepanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.packet.PacketMessage;
import messenger.user.ClientUser;
import messenger.view.messagepanel.MessagePanel;

public class TypeArea extends JTextArea implements ComponentListener, KeyListener {
	
	private TypePanel typePanel;
	
	private long messageTimer;
	
	public TypeArea(TypePanel typePanel){
		super();
		this.typePanel = typePanel;
		this.messageTimer = System.currentTimeMillis();
		
		this.setupComponent();
	}
	
	private void setupComponent(){
		this.setOpaque(false);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		
		this.addComponentListener(this);
		this.addKeyListener(this);
		
		this.setFont(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getVerdanaFont());
		
		this.setCaretColor(typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController().getUserColor().getColor());
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
				
				// message cannot equal "bonjour!"
				if(this.getText().equals("bonjour!")){
					this.setText("");
					return;
				}

				MessagePanel messagePanel = new MessagePanel(this.typePanel.getMessengerPanel().getMessagesPanel(), 
						dataController.getUserName(), dataController.getUserColor(), this.getText(), true);
				this.typePanel.getMessengerPanel().getMessagesPanel().addMessage(messagePanel);
				
				ClientUser clientUser = this.typePanel.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser();
				
				if(clientUser.isConnectedToServer()){ // Client
					PacketMessage packetMessage = new PacketMessage(dataController.getUserName(), dataController.getUserColor(), this.getText());
					clientUser.getClientUserConnection().sendPacketMessage(packetMessage);
				}else if(clientUser.isServerOpen()){ // Server
					PacketMessage packetMessage = new PacketMessage(dataController.getUserName(), dataController.getUserColor(), this.getText());
					clientUser.getMessengerServer().sendPacketMessageToClients(packetMessage);
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
	
	public void updateSizing(){
		int northOffset = -(30 + this.getHeight());
		typePanel.getMessengerPanel().getSpringLayout().putConstraint(SpringLayout.NORTH, typePanel, northOffset, SpringLayout.SOUTH, typePanel.getMessengerPanel());
		typePanel.revalidate();
	}
}
