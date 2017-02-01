package messenger.user;

import java.awt.image.BufferedImage;

import messenger.controller.MessengerController;
import messenger.util.MessengerColor;

public abstract class User {
	
	private MessengerController messengerController;
	
	private String userName;
	private MessengerColor userColor;
	private BufferedImage userImage;
	
	public User(MessengerController messengerController){
		this.messengerController = messengerController;
	}

	
	public MessengerController getMessengerController() {
		return messengerController;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MessengerColor getUserColor() {
		return userColor;
	}

	public void setUserColor(MessengerColor userColor) {
		this.userColor = userColor;
	}

	public BufferedImage getUserImage() {
		return userImage;
	}

	public void setUserImage(BufferedImage userImage) {
		this.userImage = userImage;
	}
}
