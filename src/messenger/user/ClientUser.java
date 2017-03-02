package messenger.user;

import messenger.controller.DataController;
import messenger.controller.MessengerController;

public class ClientUser {

	private MessengerController messengerController;
	
	private ClientUserConnection clientUserConnection;
	
	public ClientUser(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void connectToServer(){
		this.clientUserConnection = new ClientUserConnection(this);
		this.clientUserConnection.start();
	}
	
	public MessengerController getMessengerController(){
		return messengerController;
	}
	
	public DataController getDataController(){
		return messengerController.getDataController();
	}
	
	public ClientUserConnection getClientUserConnection() {
		return clientUserConnection;
	}
}
