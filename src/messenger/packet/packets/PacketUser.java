package messenger.packet.packets;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import messenger.packet.Packet;
import messenger.util.MessengerColor;

public class PacketUser extends Packet {
	
	private PacketUserType packetUserType;
	
	private int userID;
	private String userName;
	private MessengerColor userColor;
	private BufferedImage userImage;
	
	public PacketUser() {
		super(PacketType.USER);
		this.userName = "";
		this.userColor = MessengerColor.BLUE;
	}
	
	public PacketUser(PacketUserType packetUserType) {
		super(PacketType.USER);
		this.packetUserType = packetUserType;
		this.userName = "";
		this.userColor = MessengerColor.BLUE;
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		super.writeContent(dataOutputStream);
		
		dataOutputStream.writeUTF(packetUserType.name());
		
		if(packetUserType == PacketUserType.USERNAME){
			dataOutputStream.writeUTF(userName);
		}else if(packetUserType == PacketUserType.COLOR){
			dataOutputStream.writeUTF(userColor.name());
		}else if(packetUserType == PacketUserType.IMAGE_ICON){
			ImageIO.write(userImage, "png", dataOutputStream);
		}
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		this.packetUserType = PacketUserType.valueOf(dataInputStream.readUTF());
		
		this.userID = dataInputStream.readInt();

		if(packetUserType == PacketUserType.USERNAME){
			this.userName = dataInputStream.readUTF();
		}else if(packetUserType == PacketUserType.COLOR){
			this.userColor = MessengerColor.valueOf(dataInputStream.readUTF());
		}else if(packetUserType == PacketUserType.IMAGE_ICON){
			this.userImage = ImageIO.read(dataInputStream);
		}
	}
	
	public PacketUserType getPacketUserType() {
		return packetUserType;
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

	public MessengerColor getUserColor() {
		return userColor;
	}

	public void setUserColor(MessengerColor userColor) {
		this.userColor = userColor;
	}
	
	public BufferedImage getUserImage() {
		return userImage;
	}

	public void setUserImage(BufferedImage userImage) {
		this.userImage = userImage;
	}

	public enum PacketUserType {
		USERNAME, COLOR, IMAGE_ICON, LEAVE;
	}
	
}
