package messenger.controller;

import messenger.user.User;
import messenger.view.MessengerFrame;

public class MessengerController {
	
	private Debug debug;
	private DataController dataController;
	private User user;
	private MessengerFrame messengerFrame;

	
	public MessengerController(){
		this.debug = new Debug(this);
		this.dataController = new DataController(this);
		this.user = new User(this);
		this.messengerFrame = new MessengerFrame(this);
	}
	
	public void setupMessenger(){
		
	}
	
	
	
	public Debug getDebug() {
		return debug;
	}

	public DataController getDataController() {
		return dataController;
	}

	public User getUser() {
		return user;
	}
	
	public MessengerFrame getMessengerFrame() {
		return messengerFrame;
	}

}
