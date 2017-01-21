package messenger.packet.packets;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.packet.Packet;

public class PacketUser extends Packet {
	
	private PacketUserType packetUserType;
	
	private int userID;
	private String userName;
	private Color userColor;
	private byte[] userIconBytes;
	
	public PacketUser(PacketUserType packetUserType) {
		super(PacketType.USER);
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		super.writeContent(dataOutputStream);
		
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		super.readContent(dataInputStream);
		
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

	public Color getUserColor() {
		return userColor;
	}

	public void setUserColor(Color userColor) {
		this.userColor = userColor;
	}

	public byte[] getUserIconBytes() {
		return userIconBytes;
	}

	public void setUserIconBytes(byte[] userIconBytes) {
		this.userIconBytes = userIconBytes;
	}
	
	public enum PacketUserType {
		USERNAME, COLOR, IMAGE_ICON;
	}
	
}
