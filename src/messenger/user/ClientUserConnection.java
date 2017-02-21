package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import messenger.controller.DataController;
import messenger.packet.Packet.PacketType;
import messenger.packet.packets.PacketMessage;
import messenger.user.users.ClientUser;

public class ClientUserConnection extends Thread {
	
	private ClientUser clientUser;
	
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientUserConnection(ClientUser clientUser){
		this.clientUser = clientUser;
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
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			try{
				PacketType packetType = PacketType.valueOf(dataInputStream.readUTF()); // The PacketType is always sent first
				switch(packetType){
					case MESSAGE:
						PacketMessage packetMessage = new PacketMessage();
						packetMessage.readContent(dataInputStream);
						break;
					case FILE:
						
						break;
					case USER:
						
						break;
				}
			}catch(Exception e){
				clientUser.getMessengerController().getDebug().presentError("Reading inside while", e);
			}
		}
		this.close(false);
	}
	
	public void close(boolean clientClosed){
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
