package messenger.user;

import java.util.ArrayList;

import messenger.controller.MessengerController;

public class User {
	
	private MessengerController messengerController;
	
	private UserConnection userConnection;
	private ArrayList<ServerUser> serverUsers;
	
	public User(MessengerController messengerController){
		this.messengerController = messengerController;
		this.serverUsers = new ArrayList<ServerUser>();
	}
	
	public void connectToServer(){
		this.userConnection = new UserConnection(this);
	}

	
	public MessengerController getMessengerController() {
		return messengerController;
	}
	
	public UserConnection getUserConnection() {
		return userConnection;
	}

	public ArrayList<ServerUser> getServerUsers() {
		return serverUsers;
	}
}
