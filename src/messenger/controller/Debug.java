package messenger.controller;

import javax.swing.JOptionPane;

public class Debug {
	
	private MessengerController messengerController;
	
	public Debug(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void presentError(String message){
		JOptionPane.showMessageDialog(messengerController.getMessengerFrame(), message); // messengerFrame may be null, but that's okay. 
	}

}
