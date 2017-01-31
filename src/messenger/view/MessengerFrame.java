package messenger.view;

import java.awt.Dimension;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.UIManager;

import messenger.controller.MessengerController;
import messenger.view.menubar.MessengerMenuBar;

public class MessengerFrame extends JFrame {
	
	private MessengerController messengerController;
	
	private MessengerPanel messengerPanel;
	private MessengerMenuBar messengerMenuBar;
	
	public MessengerFrame(MessengerController messengerController){
		super();
		this.messengerController = messengerController;
		this.setupProperties();
		this.messengerPanel = new MessengerPanel(this);
		this.messengerMenuBar = new MessengerMenuBar(this);
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Messenger");
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(250, 200));
		this.setLocationRelativeTo(null); // Centers the frame
		this.setIconImage(messengerController.getDataController().getMessengerIcon());
		
		this.setJMenuBar(messengerMenuBar);
		
		this.setContentPane(messengerPanel); // after sizing so validate doesn't get called last seconds.
		
		this.setVisible(true);
		
		this.revalidate(); // Was having some weird issues with the typePanel not being the given size. This fixed it.
	}
	
	
	
	private void setupProperties(){
		if(messengerController.getDataController().isOSX()){
			this.enableOSXFullscreen(this);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				messengerController.getDebug().presentError("Properties setup", e.getMessage());
			}
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Messenger"); // Does not work sometimes!
		}
	}
	
	// Really didn't have to look this up :P
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void enableOSXFullscreen(Window window) {
		try {
			Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class params[] = new Class[]{Window.class, Boolean.TYPE};
			Method method = util.getMethod("setWindowCanFullScreen", params);
			method.invoke(util, window, true);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public MessengerController getMessengerController(){
		return messengerController;
	}

	public MessengerPanel getMessengerPanel() {
		return messengerPanel;
	}
}
