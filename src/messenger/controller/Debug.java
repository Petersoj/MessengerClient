package messenger.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Debug {

	private MessengerController messengerController;

	public Debug(MessengerController messengerController) {
		this.messengerController = messengerController;
	}

	public void presentError(String title, String message){
		SwingUtilities.invokeLater(() -> {
			if(messengerController.getDataController() == null){
				JOptionPane.showMessageDialog(null, message, title, JOptionPane.CLOSED_OPTION);
			}else{
				JOptionPane.showMessageDialog(messengerController.getMessengerFrame(), message, title, JOptionPane.CLOSED_OPTION,
						new ImageIcon(messengerController.getDataController().getMessengerIcon()));
			}
		});
	}
	
	public void presentError(String title, Exception e){
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		String message = e.getMessage() + "\n";
		for(int index = 0; index < stackTraceElements.length; index++){
			if(index > 6){ // Don't wanna print to many lines :)
				break;
			}
			StackTraceElement stackTraceElement = stackTraceElements[index];
			message += stackTraceElement.getClassName() + " " + stackTraceElement.getMethodName() + " " + stackTraceElement.getLineNumber() + "\n";
		}
		this.presentError(title, message);
	}

}
