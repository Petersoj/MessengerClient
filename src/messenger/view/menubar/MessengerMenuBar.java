package messenger.view.menubar;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import messenger.controller.DataController;
import messenger.user.ClientUser;
import messenger.view.MessengerFrame;
import messenger.view.menubar.about.AboutDialog;
import messenger.view.menubar.connect.ConnectOptionDialog;
import messenger.view.menubar.user.UserPreferencesDialog;

public class MessengerMenuBar extends JMenuBar {
	
	private MessengerFrame messengerFrame;
	
	private AboutDialog aboutDialog;
	private UserPreferencesDialog userPreferencesDialog;
	private ConnectOptionDialog connectOptionDialog;
	
	private JMenu connectMenu;
	private JMenu optionsMenu;
	private JMenu aboutMenu;

	private JMenuItem connectOptionItem;
	private JMenuItem connectItem;
	private JMenuItem serverItem;
	
	private JMenuItem userPreferencesItem;
	
	private JMenuItem aboutItem;
	private JMenuItem githubItem;
	private JMenuItem instagramItem;
	private JMenuItem websiteItem;
	
	public MessengerMenuBar(MessengerFrame messengerFrame){
		super();
		this.messengerFrame = messengerFrame;
		
		this.aboutDialog = new AboutDialog(messengerFrame.getMessengerPanel());
		this.userPreferencesDialog = new UserPreferencesDialog(messengerFrame.getMessengerPanel());
		this.connectOptionDialog = new ConnectOptionDialog(messengerFrame.getMessengerPanel());
		
		this.connectMenu = new JMenu("Connect");
		this.optionsMenu = new JMenu("Options");
		this.aboutMenu = new JMenu("About");
		
		this.connectOptionItem = new JMenuItem("Connect Options");
		this.connectItem = new JMenuItem("Connect to a Server");
		this.serverItem = new JMenuItem("Start a Server");
		
		this.userPreferencesItem = new JMenuItem("User Preferences");
		
		this.aboutItem = new JMenuItem("About");
		this.githubItem = new JMenuItem("Github");
		this.instagramItem = new JMenuItem("Instagram");
		this.websiteItem = new JMenuItem("Website");
		
		this.setupMenuBar();
		this.setupListeners();
	}
	
	private void setupMenuBar(){
		DataController dataController = this.messengerFrame.getMessengerController().getDataController();
		
		connectMenu.add(serverItem);
		connectMenu.addSeparator();
		connectMenu.add(connectItem);
		
		optionsMenu.add(connectOptionItem);
		optionsMenu.add(userPreferencesItem);
		
		aboutItem.setIcon(dataController.getAboutIcon());
		githubItem.setIcon(dataController.getGithubIcon());
		instagramItem.setIcon(dataController.getInstagramIcon());
		websiteItem.setIcon(dataController.getWebsiteIcon());
		
		aboutMenu.add(aboutItem);
		aboutMenu.addSeparator();
		aboutMenu.add(githubItem);
		aboutMenu.add(instagramItem);
		aboutMenu.addSeparator();
		
		aboutMenu.add(websiteItem);
		
		this.add(connectMenu);
		this.add(optionsMenu);
		this.add(aboutMenu);
	}
	
	private void setupListeners(){
		// Lamda expressions :D
		this.connectOptionItem.addActionListener((e) -> { connectOptionDialog.prepareDialog(); connectOptionDialog.setVisible(true); });
		this.connectItem.addActionListener((e) -> {
			
			ClientUser clientUser = connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser();
			
			if(clientUser.isConnectedToServer()){
				clientUser.disconnectFromServer();
			}else if(!clientUser.isServerOpen()){
				clientUser.connectToServer();
			}
		});
		this.serverItem.addActionListener((e) -> {
			
			ClientUser clientUser = connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser();
			
			if(clientUser.isServerOpen()){
				clientUser.stopServer();
			}else if(!clientUser.isConnectedToServer()){
				clientUser.startServer();
			}
		});
		this.userPreferencesItem.addActionListener((e) -> { userPreferencesDialog.prepareDialog(); userPreferencesDialog.setVisible(true); });
		this.aboutItem.addActionListener((e) -> { aboutDialog.prepareDialog(); aboutDialog.setVisible(true); });
		this.githubItem.addActionListener((e) -> openLink("https://github.com/Petersoj/MessengerClient"));
		this.instagramItem.addActionListener((e) -> openLink("http://www.instagram.com/jacob.lp/"));
		this.websiteItem.addActionListener((e) -> openLink("http://www.jacobpeterson.net"));
	}
	
	public void openLink(String link){
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(new URI(link));
	        } catch (Exception e) {
	            messengerFrame.getMessengerController().getDebug().presentError("Open Link", "There was a problem opening this link.");
	        }
	    }
	}
	
	public void toggleConnectMenuText(){
		if(connectItem.getText().equals("Connect to a Server")){
			connectItem.setText("Disonnect from the Server");
		}else{
			connectItem.setText("Connect to a Server");
		}
	}
	
	public void toggleServerMenuText(){
		if(serverItem.getText().equals("Start a Server")){
			serverItem.setText("Stop the Server");
		}else{
			serverItem.setText("Start a Server");
		}
	}
	
	public ConnectOptionDialog getConnectOptionDialog(){
		return connectOptionDialog;
	}
}
