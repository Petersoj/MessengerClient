package messenger.packet.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.packet.Packet;

public class PacketUser extends Packet {
	

	public PacketUser() {
		super(PacketType.USER);
	}

	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		
	}
	
	
	public enum PacketUserType {
		USERNAME, COLOR, IMAGE_ICON;
	}
}
