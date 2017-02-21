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
		presentError(title, getCustomStackTrace(e));
	}
	
	private static String getCustomStackTrace(Exception e){ 
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		String message = e.getClass().getName() + " - " + e.getMessage() + "\n\n";
		for(int index = 0; index < stackTraceElements.length; index++){
			if(index > 6){ // Don't wanna print to many lines :) && don't want an indexOutOfBoundsException
				break;
			}
			StackTraceElement stackTraceElement = stackTraceElements[index];
			message += stackTraceElement.getClassName() + " " + stackTraceElement.getMethodName() + " " + stackTraceElement.getLineNumber() + "\n";
		}
		return message;
	}

}
