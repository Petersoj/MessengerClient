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
	public void writeContent(DataOutputStream dataOutputStream) {
		try{
			dataOutputStream.writeByte(packetMessageType.getTypeCode());
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readContent(DataInputStream dataInputStream) {
		try{
			this.packetMessageType = PacketMessageType.getTypeFromCode(dataInputStream.readByte());
			switch(packetMessageType){
				case MESSAGE:
					this.userID = dataInputStream.readInt();
					this.message = dataInputStream.readUTF();
					break;
				default:
					break;
			}
		}catch(IOException e) {
			e.printStackTrace();
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
		MESSAGE(0), FILE_NOTIFIER(1), IMAGE_NOTIFIER(2);
		
		private int type;
		
		private PacketMessageType(int typeCode){
			type = typeCode;
		}
		
		public int getTypeCode(){
			return type;
		}
		
		public static PacketMessageType getTypeFromCode(int typeCode){
			for(PacketMessageType type : values()){
				if(type.getTypeCode() == typeCode){
					return type;
				}
			}
			return null;
		}
	}
}
