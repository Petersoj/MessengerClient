package messenger.view.menubar.connect;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

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
		this.setAutoRequestFocus(true);
		
		this.getRootPane().registerKeyboardAction(e -> this.dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void prepareDialog(){ // Called everytime before visible
		this.setSize(400, 170);
		this.setMaximumSize(new Dimension(450, 250));
		this.setLocationRelativeTo(messengerPanel.getMessengerFrame());
		
		this.connectOptionPanel.getIPAddressLabel().requestFocusInWindow(); // Prevents text field from being autoFocused
	}

	public MessengerPanel getMessengerPanel(){
		return messengerPanel;
	}
	
	public ConnectOptionPanel getConnectOptionPanel(){
		return connectOptionPanel;
	}

}
