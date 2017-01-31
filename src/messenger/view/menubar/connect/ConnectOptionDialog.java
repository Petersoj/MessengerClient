package messenger.view.menubar.connect;

import java.awt.Dimension;

import javax.swing.JDialog;

import messenger.view.MessengerPanel;

public class ConnectOptionDialog extends JDialog {
	
	private MessengerPanel messengerPanel;
	
	private ConnectOptionPanel connectOptionPanel;
	
	public ConnectOptionDialog(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.connectOptionPanel = new ConnectOptionPanel(this);
		
		this.setupDialog();
	}
	
	private void setupDialog(){
		this.setContentPane(connectOptionPanel);
		this.setResizable(true);
	}
	
	public void prepareDialog(){ // Called everytime before visible
		this.setSize(550, 230);
		this.setMaximumSize(new Dimension(650, 400));
		this.setLocationRelativeTo(messengerPanel.getMessengerFrame());
		
		//this.connectOptionPanel.getNameLabel().requestFocusInWindow(); // Prevents text field from being autoFocused
	}

	public MessengerPanel getMessengerPanel(){
		return messengerPanel;
	}

}
