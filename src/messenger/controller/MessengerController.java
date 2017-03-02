package messenger.controller;

import javax.swing.SwingUtilities;

import messenger.user.ClientUser;
import messenger.view.MessengerFrame;

public class MessengerController {
	
	private Debug debug;
	private DataController dataController;
	private ClientUser clientUser;
	private MessengerFrame messengerFrame;

	
	public MessengerController(){
		this.debug = new Debug(this);
		this.dataController = new DataController(this);
		if(!this.dataController.errorOccured()){
			this.clientUser = new ClientUser(this);
			SwingUtilities.invokeLater(() -> {
				this.messengerFrame = new MessengerFrame(this);
			});
		}else{ // problem loading files!
			this.debug.presentError("Fatal Error", "Program cannot open! Attempting to copy\ndefaults to app data directory");
			if(dataController != null){
				this.dataController.copyDefaultsToDataDirectory();
			}
		}
	}
	
	public void setupMessenger(){
		
	}
	
	public Debug getDebug() {
		return debug;
	}

	public DataController getDataController() {
		return dataController;
	}

	public ClientUser getClientUser() {
		return clientUser;
	}
	
	public MessengerFrame getMessengerFrame() {
		return messengerFrame;
	}

}
