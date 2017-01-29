package messenger.view.menubar;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import messenger.controller.DataController;
import messenger.view.MessengerFrame;
import messenger.view.menubar.about.AboutDialog;
import messenger.view.menubar.user.UserPreferencesDialog;

public class MessengerMenuBar extends JMenuBar {
	
	private MessengerFrame messengerFrame;
	
	private AboutDialog aboutDialog;
	private UserPreferencesDialog userPreferencesDialog;
	
	private JMenu connectMenu;
	private JMenu userMenu;
	private JMenu aboutMenu;

	private JMenuItem connectionOptionItem;
	private JMenuItem connectItem;
	
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
		
		this.connectMenu = new JMenu("Connect");
		this.userMenu = new JMenu("User");
		this.aboutMenu = new JMenu("About");
		
		this.connectionOptionItem = new JMenuItem("Connection Options");
		this.connectItem = new JMenuItem("Connect");
		
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
		
		connectMenu.add(connectionOptionItem);
		connectMenu.addSeparator();
		connectMenu.add(connectItem);
		
		userMenu.add(userPreferencesItem);
		
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
		this.add(userMenu);
		this.add(aboutMenu);
	}
	
	private void setupListeners(){
		 // Lamda expression :D
		this.userPreferencesItem.addActionListener((ActionEvent) -> { userPreferencesDialog.prepareDialog(); userPreferencesDialog.setVisible(true); });
		this.aboutItem.addActionListener((ActionEvent e) -> { aboutDialog.prepareDialog(); aboutDialog.setVisible(true); });
		this.githubItem.addActionListener((ActionEvent e) -> openLink("https://github.com/Petersoj/MessengerClient"));
		this.instagramItem.addActionListener((ActionEvent e) -> openLink("http://www.instagram.com/jacob.lp/"));
		this.websiteItem.addActionListener((ActionEvent e) -> openLink("http://www.jacobpeterson.net"));
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
}
