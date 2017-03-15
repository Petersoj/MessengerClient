package messenger.server;

import messenger.controller.MessengerController;
import messenger.packet.PacketMessage;

public class MessengerServer {
	
	private MessengerController messengerController;
	
	private ServerConnection serverConnection;
	
	public MessengerServer(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void startListening(){
		this.serverConnection = new ServerConnection(this);
		this.serverConnection.start();
	}
	
	public void stopListening(){
		this.serverConnection.stopListening();
	}
	
	public boolean isServerOpen(){
		return serverConnection.isServerOpen();
	}
	
	public void sendPacketMessageToClients(PacketMessage packetMessage){
		this.serverConnection.sendPacketToClients(packetMessage, null);
	}
	
	public MessengerController getMessengerController(){
		return messengerController;
	}

}
