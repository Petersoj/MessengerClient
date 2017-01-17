package messenger.packet.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import messenger.packet.Packet;

public class PacketUser extends Packet {
	

	public PacketUser() {
		super(PacketType.USER);
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) {
		
	}

	@Override
	public void readContent(DataInputStream dataInputStream) {
		
	}
	
	
	
	
	public enum PacketUserType {
		USERNAME, COLOR, IMAGE_ICON
	}

}
