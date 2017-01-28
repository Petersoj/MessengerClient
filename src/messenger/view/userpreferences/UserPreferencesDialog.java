package messenger.view.userpreferences;

import javax.swing.JDialog;

import messenger.view.MessengerPanel;

public class UserPreferencesDialog extends JDialog {
	
	private MessengerPanel messengerPanel;
	
	private UserPreferencesPanel userPreferencesPanel;
	
	public UserPreferencesDialog(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.userPreferencesPanel = new UserPreferencesPanel(this);
		
		this.setupDialog();
	}
	
	private void setupDialog(){
		
	}

}
