package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserConnection extends Thread {
	
	private User user;
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	
	public UserConnection(User user){
		this.user = user;
		
		try {
			this.socket = new Socket(user.getMessengerController().getDataController().getIpAddress(), user.getMessengerController().getDataController().getPort());
		} catch (IOException e) {
			user.getMessengerController().getDebug().presentError("Socket connection", e.getMessage());
		}
		this.dataOutputStream = new DataOutputStream(dataOutputStream);
		this.dataInputStream = new DataInputStream(dataInputStream);
	}
	
	@Override
	public void run(){
		while(!socket.isClosed()){
			
		}
		this.close(false);
	}
	
	public void close(boolean clientClosed){
		if(socket != null){
			
		}
	}
	

}
