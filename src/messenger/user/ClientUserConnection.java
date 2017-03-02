package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import messenger.controller.DataController;
import messenger.packet.PacketHandler;
import messenger.packet.PacketMessage;

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
			PacketMessage packetMessage = new PacketMessage(dataController.getUserName(), dataController.getUserColor(), "bonjour!");
			try {
				packetMessage.writeContent(dataOutputStream); // send our initial data to the server.
			}catch(Exception e) {
				clientUser.getMessengerController().getDebug().presentError("Giving Info", e);
			}
		}
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			try{
				
				PacketMessage packetMessage = new PacketMessage(); // create new black Packet message
				packetMessage.readContent(dataInputStream); // read input stream, but also block till recieving data
				packetHandler.handlePacketMessage(packetMessage);
				
			}catch(Exception e){
				clientUser.getMessengerController().getDebug().presentError("Reading inside while", e);
				this.close();
			}
		}
		this.close();
	}
	
	public void sendPacketMessage(PacketMessage packetMessage){
		try {
			packetMessage.writeContent(dataOutputStream);
		}catch(Exception e) {
			clientUser.getMessengerController().getDebug().presentError("Sending packet", e);
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
