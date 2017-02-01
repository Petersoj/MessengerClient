package messenger.view.menubar.user;

import java.awt.Dimension;

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
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void prepareDialog(){ // Called everytime before visible
		this.setSize(550, 230);
		this.setMaximumSize(new Dimension(650, 330));
		this.setLocationRelativeTo(messengerPanel.getMessengerFrame());
		
		this.userPreferencesPanel.getNameLabel().requestFocusInWindow(); // Prevents text field from being autoFocused
	}

	public MessengerPanel getMessengerPanel(){
		return messengerPanel;
	}
}
