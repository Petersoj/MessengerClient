package messenger.view.menubar.user.components;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import messenger.util.MessengerColor;
import messenger.view.menubar.user.UserPreferencesPanel;

public class UserColorPanel extends JPanel {
	
	private UserPreferencesPanel userPreferencesPanel;
	
	private BoxLayout boxLayout;
	private long lastColorSave;
	
	public UserColorPanel(UserPreferencesPanel userPreferencesPanel){
		super();
		this.userPreferencesPanel = userPreferencesPanel;
		
		this.boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.lastColorSave = System.currentTimeMillis();
		
		this.setupPanel();
	}
	
	private void setupPanel(){
		this.setLayout(boxLayout);
		
		for(MessengerColor messengerColor : MessengerColor.values()){ // Populate the array with instances of our custom JButtons.
			UserColorButton userColorButton = new UserColorButton(this,  messengerColor);
			this.add(userColorButton);
			this.add(Box.createHorizontalStrut(10)); // Special static method that allows spacing between components in BoxLayout.
		}
		
		this.setBackground(Color.WHITE);
	}
	
	public long getLastColorSave(){
		return lastColorSave;
	}
	
	public void setLastColorSave(long time){
		this.lastColorSave = time;
	}
	
	public UserPreferencesPanel getUserPreferencesPanel(){
		return userPreferencesPanel;
	}

}
