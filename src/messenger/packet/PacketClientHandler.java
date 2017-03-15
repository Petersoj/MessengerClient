package messenger.packet;

import messenger.controller.MessengerController;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class PacketClientHandler {
	
	private MessengerController messengerController;
	
	public PacketClientHandler(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		MessagesPanel messagesPanel = messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
		MessagePanel messagePanel;
		
		String message = packetMessage.getMessage();
		
		if(message.equals("server-userjoin")){
			messagePanel = new MessagePanel(messagesPanel, "\'" + packetMessage.getUserName() + "\' has joined the chat.");
		}else if(message.equals("server-userleave")){
			messagePanel = new MessagePanel(messagesPanel, "\'" + packetMessage.getUserName() + "\' has left the chat.");
		}else{
			messagePanel = new MessagePanel(messagesPanel, packetMessage.getUserName(),
					packetMessage.getUserColor(), packetMessage.getMessage(), false);
		}
		messagesPanel.addMessage(messagePanel);
	}

	public MessengerController getMessengerController() {
		return messengerController;
	}
}
