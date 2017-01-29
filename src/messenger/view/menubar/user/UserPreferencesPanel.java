package messenger.view.menubar.user;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import messenger.view.menubar.user.components.UserImageSelector;

public class UserPreferencesPanel extends JPanel {
	
	private UserPreferencesDialog userPreferencesDialog;
	
	private SpringLayout springLayout;
	private UserImageSelector userImageSelector;
	
	public UserPreferencesPanel(UserPreferencesDialog userPreferencesDialog){
		super();
		this.userPreferencesDialog = userPreferencesDialog;
		this.springLayout = new SpringLayout();
		this.userImageSelector = new UserImageSelector(this);
		
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(userImageSelector);
		this.setBackground(Color.WHITE);
	}

	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, userImageSelector, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, userImageSelector, 180, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, userImageSelector, 180, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, userImageSelector, 30, SpringLayout.WEST, this);
	}
	
	public UserPreferencesDialog getUserPreferencesDialog(){
		return userPreferencesDialog;
	}
}

