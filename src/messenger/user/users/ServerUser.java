package messenger.user.users;

import java.awt.image.BufferedImage;

public class ServerUser {
	
	private int userID;
	private String userName;
	private BufferedImage userImage;
	
	public ServerUser(int id){
		this.userName = "User " + id;
		userImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
	}

	
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BufferedImage getUserImage() {
		return userImage;
	}

	public void setUserImage(BufferedImage userImage) {
		this.userImage = userImage;
	}
}

