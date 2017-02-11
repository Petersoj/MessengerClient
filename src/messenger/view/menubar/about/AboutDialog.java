package messenger.view.menubar.about;

import javax.swing.JDialog;

import messenger.view.MessengerPanel;

public class AboutDialog extends JDialog {
	
	private MessengerPanel messengerPanel;
	
	private AboutPanel aboutPanel;
	
	public AboutDialog(MessengerPanel messengerPanel){
		super();
		this.messengerPanel = messengerPanel;
		this.aboutPanel = new AboutPanel(this);
		
		this.setupDialog();
	}
	
	public void setupDialog(){
		this.setContentPane(aboutPanel);
		this.setResizable(true);
		this.setAutoRequestFocus(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void prepareDialog(){
		this.setSize(500, 270);
		this.setLocationRelativeTo(messengerPanel.getMessengerFrame());
	}
	
	public MessengerPanel getMessengerPanel(){
		return messengerPanel;
	}

}
