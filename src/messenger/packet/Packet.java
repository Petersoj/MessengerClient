package messenger.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
	
	private PacketType packetType;
	
	public Packet(PacketType packetType){
		this.packetType = packetType;
	}
	
	public abstract void writeContent(DataOutputStream dataOutputStream) throws IOException;
	
	public abstract void readContent(DataInputStream dataInputStream) throws IOException;
	
	public PacketType getPacketType() {
		return packetType;
	}
	
	public enum PacketType {
		MESSAGE(0), FILE(1), USER(2);
		
		private int type;
		
		private PacketType(int typeCode){
			type = typeCode;
		}
		
		public int getTypeCode(){
			return type;
		}
		
		public static PacketType getTypeFromCode(int typeCode){
			for(PacketType type : values()){
				if(type.getTypeCode() == typeCode){
					return type;
				}
			}
			return null;
		}
	}
	
}
