package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import messenger.controller.DataController;
import messenger.user.users.ClientUser;

public class ClientUserConnection extends Thread {
	
	private ClientUser clientUser;
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	
	public ClientUserConnection(ClientUser clientUser){
		this.clientUser = clientUser;
	}
	
	@Override
	public void run(){
		
		DataController dataController = clientUser.getMessengerController().getDataController();
		
		try {
			this.socket = new Socket();
			this.socket.connect(new InetSocketAddress(dataController.getIPAddress(), dataController.getPort()), 1000);
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Socket connection", e);
		}
		this.dataOutputStream = new DataOutputStream(dataOutputStream);
		this.dataInputStream = new DataInputStream(dataInputStream);
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			
		}
		this.close(false);
	}
	
	public void close(boolean clientClosed){
		try{
			if(socket != null){
				dataOutputStream.close();
				dataInputStream.close();
				socket.close();
			}
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Closing Connection", e);
		}
	}
	

}
