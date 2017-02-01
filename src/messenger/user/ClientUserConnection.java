package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientUserConnection extends Thread {
	
	private User user;
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	
	public ClientUserConnection(User user){
		this.user = user;
		try {
			this.socket = new Socket();
			this.socket.connect(new InetSocketAddress(user.getMessengerController().getDataController().getIPAddress(), 
					user.getMessengerController().getDataController().getPort()), 1000);
		} catch (Exception e) {
			user.getMessengerController().getDebug().presentError("Socket connection", e);
		}
		this.dataOutputStream = new DataOutputStream(dataOutputStream);
		this.dataInputStream = new DataInputStream(dataInputStream);
	}
	
	@Override
	public void run(){
		while(!socket.isClosed() && socket.isConnected()){
			
		}
		this.close(false);
	}
	
	public void close(boolean clientClosed){
		if(socket != null){
			
		}
	}
	

}
