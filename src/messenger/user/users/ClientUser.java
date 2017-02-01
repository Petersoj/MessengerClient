package messenger.user.users;

import java.util.ArrayList;

import messenger.controller.DataController;
import messenger.controller.MessengerController;
import messenger.user.ClientUserConnection;
import messenger.user.User;

public class ClientUser extends User {

	private ClientUserConnection clientUserConnection;
	private ArrayList<ServerUser> serverUsers;
	
	public ClientUser(MessengerController messengerController){
		super(messengerController);
		
		DataController dataController = messengerController.getDataController();
		super.setUserName(dataController.getUserName());
		super.setUserColor(dataController.getUserColor());
		super.setUserImage(dataController.getUserIcon());

		this.serverUsers = new ArrayList<ServerUser>();
	}
	
	public void connectToServer(){
		this.clientUserConnection = new ClientUserConnection(this);
		this.clientUserConnection.start();
	}
	

	public ClientUserConnection getClientUserConnection() {
		return clientUserConnection;
	}

	public ArrayList<ServerUser> getServerUsers() {
		return serverUsers;
	}
}
