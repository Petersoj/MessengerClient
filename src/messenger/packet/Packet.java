package messenger.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Packet {
	
	private PacketType packetType;
	
	public Packet(PacketType packetType){
		this.packetType = packetType;
	}
	
	public abstract void writeContent(DataOutputStream dataOutputStream);
	
	public abstract void readContent(DataInputStream dataInputStream);

	public PacketType getPacketType() {
		return packetType;
	}
	
	public enum PacketType {
		MESSAGE, FILE, USER;
	}
	
}
