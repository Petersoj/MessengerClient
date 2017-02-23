package messenger.view.menubar.about;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

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
		this.setResizable(false);
		this.setAutoRequestFocus(true);
		
		this.getRootPane().registerKeyboardAction(e -> this.dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
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
