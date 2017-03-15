package messenger.user;

import messenger.controller.DataController;
import messenger.controller.MessengerController;
import messenger.server.MessengerServer;

public class ClientUser {

	private MessengerController messengerController;
	
	private ClientUserConnection clientUserConnection;
	
	private MessengerServer messengerServer;
	
	public ClientUser(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void connectToServer(){
		this.clientUserConnection = new ClientUserConnection(this);
		this.clientUserConnection.start();
	}
	
	public void disconnectFromServer(){
		if(clientUserConnection != null){
			this.clientUserConnection.close();
		}
	}
	
	public boolean isConnectedToServer(){
		return clientUserConnection == null ? false : clientUserConnection.isConnected();
	}
	
	public void startServer(){
		this.messengerServer = new MessengerServer(messengerController);
		this.messengerServer.startListening();
	}
	
	public void stopServer(){
		if(messengerServer != null){
			this.messengerServer.stopListening();
		}
	}
	
	public boolean isServerOpen(){
		return messengerServer == null ? false : messengerServer.isServerOpen();
	}
	
	public ClientUserConnection getClientUserConnection(){
		return clientUserConnection;
	}
	
	public MessengerServer getMessengerServer(){
		return messengerServer;
	}
	
	public MessengerController getMessengerController(){
		return messengerController;
	}
	
	public DataController getDataController(){
		return messengerController.getDataController();
	}
}
