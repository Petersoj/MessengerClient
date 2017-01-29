package messenger.view.menubar.user;

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
		this.setContentPane(userPreferencesPanel);
		this.setResizable(true);
	}
	
	public void prepareDialog(){
		this.setSize(600, 230);
		this.setLocationRelativeTo(messengerPanel.getMessengerFrame());
	}

	public MessengerPanel getMessengerPanel(){
		return messengerPanel;
	}
}
