package messenger.view;

import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import messenger.controller.MessengerController;

public class MessengerFrame extends JFrame {
	
	private MessengerController messengerController;
	
	private MessengerPanel messengerPanel;
	
	public MessengerFrame(MessengerController messengerController){
		super();
		this.messengerController = messengerController;
		this.messengerPanel = new MessengerPanel(this);
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(messengerController.getDataController().isOSX()){
			this.enableOSXFullscreen(this);
		}
		
		this.setTitle("Messenger");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null); // Centers the frame
		this.setIconImage(messengerController.getDataController().getMessengerIcon());
		
		this.setContentPane(messengerPanel); // after sizing so validate doesn't get called last seconds.
		
		this.setVisible(true);
		
		this.revalidate(); // Was having some weird issues with the typePanel not being the given size. This fixed it.
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
