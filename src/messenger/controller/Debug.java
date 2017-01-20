package messenger.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Debug {

	private MessengerController messengerController;

	public Debug(MessengerController messengerController) {
		this.messengerController = messengerController;
	}

	public void presentError(String title, String message){
		if(messengerController.getDataController().getMessengerIcon() == null){
			JOptionPane.showMessageDialog(messengerController.getMessengerFrame(), message, title, JOptionPane.CLOSED_OPTION);
		}else{
			JOptionPane.showMessageDialog(messengerController.getMessengerFrame(), message, title, JOptionPane.CLOSED_OPTION,
					new ImageIcon(messengerController.getDataController().getMessengerIcon()));
		}
	}

}