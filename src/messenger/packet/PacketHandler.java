package messenger.packet;

import messenger.controller.MessengerController;
import messenger.packet.packets.PacketFile;
import messenger.packet.packets.PacketMessage;
import messenger.packet.packets.PacketMessage.PacketMessageType;
import messenger.packet.packets.PacketUser;
import messenger.packet.packets.PacketUser.PacketUserType;
import messenger.user.users.ServerUser;
import messenger.view.messagepanel.MessagesPanel;
import messenger.view.messagepanel.messagepanels.FilePanel;
import messenger.view.messagepanel.messagepanels.MessagePanel;

public class PacketHandler {
	
	private MessengerController messengerController;
	
	public PacketHandler(MessengerController messengerController){
		this.messengerController = messengerController;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		PacketMessageType packetMessageType = packetMessage.getPacketMessageType();
		
		ServerUser serverUser = messengerController.getClientUser().getServerByID(packetMessage.getUserID());
		
		if(serverUser == null){
			MessagesPanel messagesPanel = this.messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			messagesPanel.addMessage(new MessagePanel(messagesPanel, null, packetMessage.getMessage()));
			return;
		}
		
		if(packetMessageType == PacketMessageType.MESSAGE){
			MessagesPanel messagesPanel = this.messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			messagesPanel.addMessage(new MessagePanel(messagesPanel, serverUser, packetMessage.getMessage()));
		}else if(packetMessageType == PacketMessageType.FILE_NOTIFIER){
			MessagesPanel messagesPanel = this.messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			messagesPanel.addFileMessage(new FilePanel(messagesPanel, serverUser, packetMessage.getMessage()));
		}
	}
	
	public void handlePacketFile(PacketFile packetFile){
		
	}
	
	public void handlePacketUser(PacketUser packetUser){
		PacketUserType packetUserType = packetUser.getPacketUserType();
		
		ServerUser serverUser = messengerController.getClientUser().getServerByID(packetUser.getUserID());
		
		if(packetUserType == PacketUserType.USERNAME){
			String userName = packetUser.getUserName();
			if(serverUser == null){
				serverUser = new ServerUser(messengerController, packetUser.getUserID());
				this.messengerController.getClientUser().addServerUser(serverUser);
				
				MessagesPanel messagesPanel = this.messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
				messagesPanel.addMessage(new MessagePanel(messagesPanel, null, userName + " has joined the chat."));
			}
			serverUser.setUserName(userName);
		}
		
		if(serverUser == null){
			return;
		}
		
		if(packetUserType == PacketUserType.COLOR){
			serverUser.setUserColor(packetUser.getUserColor());
		}else if(packetUserType == PacketUserType.IMAGE_ICON){
			serverUser.setUserImage(packetUser.getUserImage());
		}else if(packetUserType == PacketUserType.LEAVE){
			this.messengerController.getClientUser().removeServerUser(serverUser);
			MessagesPanel messagesPanel = this.messengerController.getMessengerFrame().getMessengerPanel().getMessagesPanel();
			messagesPanel.addMessage(new MessagePanel(messagesPanel, null, serverUser.getUserName() + " has left the chat."));
		}
	}
	
	public MessengerController getMessengerController() {
		return messengerController;
	}
}
