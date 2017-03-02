package messenger.packet;

import messenger.controller.MessengerController;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class PacketHandler {
	
	private MessengerController messengerController;
	
	public PacketHandler(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		MessagesPanel messagesPanel = messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
		MessagePanel messagePanel = new MessagePanel(messagesPanel, packetMessage.getUserName(),
				packetMessage.getUserColor(), packetMessage.getMessage(), false);
		messagesPanel.addMessage(messagePanel);
	}
	
	public MessengerController getMessengerController() {
		return messengerController;
	}
}
