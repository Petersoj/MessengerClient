package messenger.packet.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.packet.Packet;

public class PacketFile extends Packet {

	private int userID;
	private int fileID;
	
	public PacketFile() {
		super(PacketType.FILE);
	}
	
	@Override
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		super.writeContent(dataOutputStream);
		
	}

	@Override
	public void readContent(DataInputStream dataInputStream) throws IOException{
		
	}
	

}
