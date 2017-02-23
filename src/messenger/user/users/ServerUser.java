package messenger.user.users;

import messenger.controller.MessengerController;
import messenger.user.User;
import messenger.util.MessengerColor;

public class ServerUser extends User {
	
	private int userID;
	
	public ServerUser(MessengerController messengerController, int userID){
		super(messengerController);
		this.userID = userID;
		super.setUserName("Server User");
		super.setUserColor(MessengerColor.BLUE);
		super.setUserImage(messengerController.getDataController().getUserIcon());
	}
	
	public int getUserID() {
		return userID;
	}
}

