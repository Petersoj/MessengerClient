package messenger.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.SwingUtilities;

import messenger.packet.PacketMessage;
import messenger.packet.PacketServerHandler;
import messenger.util.MessengerColor;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class ClientConnection extends Thread {
	
	private ServerConnection serverConnection;
	
	private String clientUsername;

	private PacketServerHandler packetHandler;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientConnection(ServerConnection serverConnection, Socket socket){
		this.serverConnection = serverConnection;
		this.socket = socket;
		this.packetHandler = new PacketServerHandler(this);
	}
	
	@Override
	public void run(){
		try{
			this.dataInputStream = new DataInputStream(socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e){
			serverConnection.getMessengerServer().getMessengerController().getDebug().presentError("Setting up Data streams", e);
		}
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			try{
				
				PacketMessage packetMessage = new PacketMessage();
				packetMessage.readContent(dataInputStream);
				packetHandler.handlePacketMessage(packetMessage);
				
			}catch(SocketException e){
				this.closeConnection(true);
			}catch(EOFException e){
				this.closeConnection(true);
			}catch(Exception e){
				serverConnection.getMessengerServer().getMessengerController().getDebug().presentError("While loop", e);
				this.closeConnection(true);
			}
		}
	}

	public void sendPacketMessage(PacketMessage packetMessage){
		try {
			packetMessage.writeContent(dataOutputStream);
		}catch(Exception e) {
			serverConnection.getMessengerServer().getMessengerController().getDebug().presentError("Sending packet", e);
		}
	}

	public void closeConnection(boolean remove){
		try{
			if(socket != null && socket.isConnected()){
				dataInputStream.close();
				dataOutputStream.close();
				socket.close();
			}
		}catch(Exception e){
			serverConnection.getMessengerServer().getMessengerController().getDebug().presentError("Closing connection", e);
		}
		
		MessagesPanel messagesPanel = serverConnection.getMessengerServer().getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
		MessagePanel leftMessage = new MessagePanel(messagesPanel, "\'" + clientUsername + "\' has left the chat.");
		SwingUtilities.invokeLater(() -> {
			messagesPanel.addMessage(leftMessage);
		});
		
		if(remove){
			serverConnection.getClientConnections().remove(this);
		}
		
		PacketMessage leaveMessage = new PacketMessage(clientUsername, MessengerColor.BLUE, "server-userleave");
		serverConnection.sendPacketToClients(leaveMessage, this);
	}
	
	public ServerConnection getServerConnection(){
		return serverConnection;
	}
	
	public void setUsername(String userName){
		this.clientUsername = userName;
	}
	
	public String getUsername(){
		return clientUsername;
	}
}