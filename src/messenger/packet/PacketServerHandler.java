package messenger.packet;

import messenger.server.ClientConnection;
import messenger.util.MessengerColor;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class PacketServerHandler {
	
	private ClientConnection clientConnection;
	
	public PacketServerHandler(ClientConnection clientConnection){
		this.clientConnection = clientConnection;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		if(packetMessage.getMessage().equals("bonjour!")){
			MessagesPanel messagesPanel = clientConnection.getServerConnection().getMessengerServer().getMessengerController()
					.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			MessagePanel messagePanel = new MessagePanel(messagesPanel, "\'" + packetMessage.getUserName() + "\' has joined the chat.");
			messagesPanel.addMessage(messagePanel);
			
			clientConnection.setUsername(packetMessage.getUserName());
			
			for(ClientConnection client : clientConnection.getServerConnection().getClientConnections()){
				if(client != clientConnection){
					PacketMessage joinMessage = new PacketMessage(client.getUsername(), MessengerColor.BLUE, "server-userjoin");
					clientConnection.sendPacketMessage(joinMessage);
				}
			}
			
			PacketMessage serverUserNameMessage = new PacketMessage(clientConnection.getServerConnection().getMessengerServer()
					.getMessengerController().getDataController().getUserName(), MessengerColor.BLUE, "server-userjoin");
			clientConnection.sendPacketMessage(serverUserNameMessage);
			
			PacketMessage sendingPacketMessage = new PacketMessage(packetMessage.getUserName(), MessengerColor.BLUE, "server-userjoin");
			clientConnection.getServerConnection().sendPacketToClients(sendingPacketMessage, clientConnection);
		}else{
			MessagesPanel messagesPanel = clientConnection.getServerConnection().getMessengerServer().getMessengerController()
					.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			MessagePanel messagePanel = new MessagePanel(messagesPanel, packetMessage.getUserName(), packetMessage.getUserColor(), packetMessage.getMessage(), false);
			messagesPanel.addMessage(messagePanel);
			
			PacketMessage sendingPacketMessage = new PacketMessage(packetMessage.getUserName(), packetMessage.getUserColor(), packetMessage.getMessage());
			clientConnection.getServerConnection().sendPacketToClients(sendingPacketMessage, clientConnection);
		}
		clientConnection.setUsername(packetMessage.getUserName());
	}
}
