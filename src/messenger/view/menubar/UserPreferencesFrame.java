package messenger.view.menubar;

import javax.swing.JFrame;

import messenger.controller.MessengerController;

public class UserPreferencesFrame extends JFrame {
	
	private MessengerController messengerController;
	
	private UserPreferencesPanel userPreferencesPanel;
	
	public UserPreferencesFrame(MessengerController messengerController){
		super();
		
		this.messengerController = messengerController;
		
		this.userPreferencesPanel = new UserPreferencesPanel();
		
		this.setupFrame();
	}
	
	public void setupFrame(){
		super.setTitle("User Preferences");
		super.setContentPane(userPreferencesPanel);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setSize(400, 400);
	}
	
	public MessengerController getMessengerController() {
		return messengerController;
	}

}
