package messenger.packet;

import messenger.controller.MessengerController;
import messenger.packet.packets.PacketFile;
import messenger.packet.packets.PacketMessage;
import messenger.packet.packets.PacketUser;
import messenger.packet.packets.PacketUser.PacketUserType;

public class PacketHandler {
	
	private MessengerController messengerController;
	
	public PacketHandler(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		
	}
	
	public void handlePacketFile(PacketFile packetFile){
		
	}
	
	public void handlePacketUser(PacketUser packetUser){
		PacketUserType packetUserType = packetUser.getPacketUserType();
		int userID = packetUser.getUserID();
		
		if(packetUserType == PacketUserType.USERNAME){
			
		}else if(packetUserType == PacketUserType.COLOR){
			
		}else if(packetUserType == PacketUserType.IMAGE_ICON){
			
		}else if(packetUserType == PacketUserType.LEAVE){
			
		}
	}
	
	public MessengerController getMessengerController() {
		return messengerController;
	}
}
