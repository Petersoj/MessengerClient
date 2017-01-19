package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class UserConnection extends Thread {
	
	private User user;
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	
	public UserConnection(User user, Socket socket){
		this.user = user;
		
		this.socket = socket;
		this.dataOutputStream = new DataOutputStream(dataOutputStream);
		this.dataInputStream = new DataInputStream(dataInputStream);
	}
	
	@Override
	public void run(){
		
	}
	

}
