package messenger.view.menubar.connect;

import javax.swing.JDialog;

import messenger.view.MessengerPanel;

public class ConnectDialog extends JDialog {
	
	private MessengerPanel messengerPanel;
	
	public ConnectDialog(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
	}
	
	public void prepareDialog(){ // Called before being visible
		
	}

}
