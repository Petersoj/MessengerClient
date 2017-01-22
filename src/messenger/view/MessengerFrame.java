package messenger.view;

import javax.swing.JFrame;

import messenger.controller.MessengerController;

public class MessengerFrame extends JFrame {
	
	private MessengerController messengerController;
	
	private MessengerPanel messengerPanel;
	
	public MessengerFrame(MessengerController messengerController){
		super();
		this.messengerController = messengerController;
		this.messengerPanel = new MessengerPanel(this);
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Messenger");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null); // Centers the frame
		this.setIconImage(messengerController.getDataController().getMessengerIcon());
		
		this.setContentPane(messengerPanel); // after sizing so validate doesn't get called last seconds.
		
		this.setVisible(true);
	}

	
	public MessengerController getMessengerController(){
		return messengerController;
	}

	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
	
}
