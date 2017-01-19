package messenger.view;

import javax.swing.JFrame;

import messenger.controller.MessengerController;

public class MessengerFrame extends JFrame {
	
	private MessengerController messengerController;
	
	public MessengerFrame(MessengerController messengerController){
		this.messengerController = messengerController;
		this.setupFrame();
	}
	
	private void setupFrame(){
		
	}

	
	public MessengerController getMessengerController(){
		return messengerController;
	}
	
}
