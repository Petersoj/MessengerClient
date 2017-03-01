package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import messenger.controller.DataController;
import messenger.packet.Packet;
import messenger.packet.Packet.PacketType;
import messenger.packet.PacketHandler;
import messenger.packet.packets.PacketFile;
import messenger.packet.packets.PacketMessage;
import messenger.packet.packets.PacketUser;
import messenger.packet.packets.PacketUser.PacketUserType;
import messenger.user.users.ClientUser;
import messenger.util.Utils;

public class ClientUserConnection extends Thread {
	
	private ClientUser clientUser;
	
	private PacketHandler packetHandler;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientUserConnection(ClientUser clientUser){
		this.clientUser = clientUser;
		this.packetHandler = new PacketHandler(clientUser.getMessengerController());
	}
	
	@Override
	public void run(){
		DataController dataController = clientUser.getMessengerController().getDataController();
		
		try {
			this.socket = new Socket();
			this.socket.connect(new InetSocketAddress(dataController.getIPAddress(), dataController.getPort()), 1000);
			this.dataInputStream = new DataInputStream(socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Socket connection", e);
		}
		
		if(socket != null && !socket.isClosed() && socket.isConnected()){
			PacketUser userNamePacket = new PacketUser(PacketUserType.USERNAME);
			userNamePacket.setUserName(clientUser.getUserName());
			
			PacketUser userColorPacket = new PacketUser(PacketUserType.COLOR);
			userColorPacket.setUserColor(clientUser.getUserColor());
			
			PacketUser userImagePacket = new PacketUser(PacketUserType.IMAGE_ICON);
			userImagePacket.setUserImage(Utils.getNewResizedImage(clientUser.getUserImage(), 50, 50)); // The client will only render images at 50x50
			
			// !!! Order is Important !!!
			this.sendPacket(userNamePacket);
			this.sendPacket(userColorPacket);
			this.sendPacket(userImagePacket);
		}
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			try{
				PacketType packetType = PacketType.valueOf(dataInputStream.readUTF()); // The PacketType is always sent first
				switch(packetType){
					case MESSAGE:
						PacketMessage packetMessage = new PacketMessage();
						packetMessage.readContent(dataInputStream);
						packetHandler.handlePacketMessage(packetMessage);
						break;
					case FILE:
						PacketFile packetFile = new PacketFile();
						packetFile.readContent(dataInputStream);
						packetHandler.handlePacketFile(packetFile);
						break;
					case USER:
						PacketUser packetUser = new PacketUser();
						packetUser.readContent(dataInputStream);
						packetHandler.handlePacketUser(packetUser);
						break;
				}
			}catch(Exception e){
				clientUser.getMessengerController().getDebug().presentError("Reading inside while", e);
				this.close();
			}
		}
		this.close();
	}
	
	public void sendPacket(Packet packet){
		try{
			packet.writeContent(dataOutputStream);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		try{
			if(socket != null && socket.isConnected()){
				dataOutputStream.close();
				dataInputStream.close();
				socket.close();
			}
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Closing Connection", e);
		}
	}
}
