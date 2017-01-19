package messenger.user;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import messenger.controller.MessengerController;

public class User {
	
	private MessengerController messengerController;
	
	private UserConnection userConnection;
	private ArrayList<ServerUser> serverUsers;
	
	private String userName;
	private UserColor userColor;
	private BufferedImage userIcon;
	
	public User(MessengerController messengerController){
		this.messengerController = messengerController;
		this.serverUsers = new ArrayList<ServerUser>();
		this.userName = "Default Name";
		this.userColor = UserColor.BLUE;
	}
	
	public void connectToServer(){
		
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
	
	public String getUserName() {
		return userName;
	}

	public UserColor getUserColor() {
		return userColor;
	}

	public BufferedImage getUserIcon() {
		return userIcon;
	}
	

}
