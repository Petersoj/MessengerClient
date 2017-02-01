package messenger.view.menubar.user;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.view.menubar.user.components.UserColorPanel;
import messenger.view.menubar.user.components.UserImageSelector;

public class UserPreferencesPanel extends JPanel {
	
	private UserPreferencesDialog userPreferencesDialog;
	
	private SpringLayout springLayout;
	private UserImageSelector userImageSelector;
	private JLabel nameLabel;
	private JTextField nameField;
	private UserColorPanel userColorPanel;
	
	public UserPreferencesPanel(UserPreferencesDialog userPreferencesDialog){
		super();
		this.userPreferencesDialog = userPreferencesDialog;
		this.springLayout = new SpringLayout();
		this.userImageSelector = new UserImageSelector(this);
		this.nameLabel = new JLabel("Name:");
		this.nameField = new JTextField();
		this.userColorPanel = new UserColorPanel(this);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void setupComponents(){
		DataController dataController = this.userPreferencesDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		this.nameLabel.setFont(dataController.getVerdanaFont());
		this.nameLabel.setHorizontalTextPosition(JLabel.CENTER);
		
		this.nameField.setText(dataController.getUserName());
		this.nameField.setFont(dataController.getVerdanaFont());
		this.nameField.setCaretColor(dataController.getUserColor().getColor());
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(userImageSelector);
		this.add(nameLabel);
		this.add(nameField);
		this.add(userColorPanel);
		this.setBackground(Color.WHITE);
	}

	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, userImageSelector, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, userImageSelector, 180, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, userImageSelector, 180, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, userImageSelector, 30, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 30, SpringLayout.NORTH, userImageSelector);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 50, SpringLayout.EAST, userImageSelector);
		springLayout.putConstraint(SpringLayout.EAST, nameLabel, 65, SpringLayout.WEST, nameLabel);
		
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, nameField, 0, SpringLayout.VERTICAL_CENTER, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.EAST, nameLabel);
		springLayout.putConstraint(SpringLayout.EAST, nameField, 200, SpringLayout.EAST, nameLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, userColorPanel, -60, SpringLayout.SOUTH, userImageSelector);
		springLayout.putConstraint(SpringLayout.SOUTH, userColorPanel, -30, SpringLayout.SOUTH, userImageSelector);
		springLayout.putConstraint(SpringLayout.EAST, userColorPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, userColorPanel, 0, SpringLayout.WEST, nameLabel);
	}
	
	private void setupListeners(){
		this.nameField.addActionListener((e) -> {
			changeName();
		});
		this.nameField.addFocusListener(new FocusListener() {  // Used if they close the dialog without hitting return
			@Override
			public void focusLost(FocusEvent e) {
				changeName();
			}
			@Override
			public void focusGained(FocusEvent e) { } // unused
		});
	}
	
	private void changeName(){
		DataController dataController = this.userPreferencesDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		String typedInName = this.nameField.getText();
		if(!(typedInName.equals(dataController.getUserName()))){ // Only change the username if changed at all
			if(typedInName.contains(",")){
				typedInName = typedInName.replaceAll(",", ""); // If they type in ',', They will cause a parsing error on the next startup.
			}
			if(typedInName.contains("=")){
				typedInName = typedInName.replaceAll("=", ""); // If they type in ':', They will cause a parsing error on the next startup.
			}
			if(typedInName.length() > 20){
				typedInName = typedInName.substring(0, 20);
			}
			this.nameField.setText(typedInName);

			dataController.setUserName(typedInName);
			dataController.saveData(true);
			
			this.nameLabel.requestFocusInWindow(); // Makes Text field un-focused
		}
	}
	
	public UserPreferencesDialog getUserPreferencesDialog(){
		return userPreferencesDialog;
	}
	
	public JLabel getNameLabel(){
		return nameLabel;
	}
	
	public JTextField getNameField(){
		return nameField;
	}
	
	
}

