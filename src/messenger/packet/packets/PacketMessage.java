package messenger.packet.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.packet.Packet;

public class PacketMessage extends Packet {

	private PacketMessageType packetMessageType;
	
	private int userID;
	private String message;

	public PacketMessage(PacketMessageType packetMessageType) {
		super(PacketType.MESSAGE);
		this.packetMessageType = packetMessageType;
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		dataOutputStream.writeUTF(packetMessageType.toString());
		switch(packetMessageType){
			case MESSAGE:
				dataOutputStream.writeUTF(message);
				break;
			default:
				break;
		}
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		this.packetMessageType = PacketMessageType.valueOf(dataInputStream.readUTF());
		this.userID = dataInputStream.readInt();
		switch(packetMessageType){
			case MESSAGE:
				this.message = dataInputStream.readUTF();
				break;
			default:
				break;
		}
	}
	
	
	public PacketMessageType getPacketMessageType() {
		return packetMessageType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public enum PacketMessageType {
		MESSAGE, FILE_NOTIFIER, IMAGE_NOTIFIER;
	}
}
