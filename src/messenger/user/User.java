package messenger.user;

import java.util.ArrayList;

public class User {
	
	private UserConnection userConnection;
	
	private ArrayList<ServerUser> serverUsers;
	
	public User(){
		this.serverUsers = new ArrayList<ServerUser>();
	}
	
	public void connectToServer(){
		
	}

}
