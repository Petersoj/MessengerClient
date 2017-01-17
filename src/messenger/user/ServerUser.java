package messenger.user;

import java.awt.image.BufferedImage;

public class ServerUser {
	
	private int userID;
	private String userName;
	private BufferedImage userImage;
	
	public ServerUser(int id){
		this.userName = "User " + id;
		userImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
	}

}
