package messenger.packet.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.packet.Packet;

public class PacketMessage extends Packet {

	private PacketMessageType packetMessageType;
	
	private int userID;
	private String message;
	
	public PacketMessage() {
		super(PacketType.MESSAGE);
	}

	public PacketMessage(PacketMessageType packetMessageType) {
		super(PacketType.MESSAGE);
		this.packetMessageType = packetMessageType;
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		super.writeContent(dataOutputStream);
		dataOutputStream.writeUTF(packetMessageType.name());
		
		if(packetMessageType == PacketMessageType.MESSAGE){
			dataOutputStream.writeUTF(message);
		}
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		this.packetMessageType = PacketMessageType.valueOf(dataInputStream.readUTF());
		
		this.userID = dataInputStream.readInt();
		if(packetMessageType == PacketMessageType.MESSAGE){
			this.message = dataInputStream.readUTF();
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
		MESSAGE, FILE_NOTIFIER;
	}
}
