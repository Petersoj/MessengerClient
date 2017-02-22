package messenger.view.menubar.user;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

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
		this.setAutoRequestFocus(true);
		
		this.getRootPane().registerKeyboardAction(e -> this.dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
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
