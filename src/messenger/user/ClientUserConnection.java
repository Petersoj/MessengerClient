package messenger.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import messenger.controller.DataController;
import messenger.packet.PacketClientHandler;
import messenger.packet.PacketMessage;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class ClientUserConnection extends Thread {
	
	private ClientUser clientUser;
	
	private PacketClientHandler packetHandler;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientUserConnection(ClientUser clientUser){
		this.clientUser = clientUser;
		this.packetHandler = new PacketClientHandler(clientUser.getMessengerController());
	}
	
	@Override
	public void run(){
		DataController dataController = clientUser.getMessengerController().getDataController();
		
		try {
			this.socket = new Socket();
			this.socket.connect(new InetSocketAddress(dataController.getIPAddress(), dataController.getPort()), 1000);
			this.dataInputStream = new DataInputStream(socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		}catch(UnknownHostException e){
			clientUser.getMessengerController().getDebug().presentError("Could not connect", "Cannot determine IP Address of host.");
			this.close();
		}catch(ConnectException e){
			clientUser.getMessengerController().getDebug().presentError("Could not connect", "There was a problem connecting to the server!");
			this.close();
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Socket connection", e);
			this.close();
		}
		
		if(isConnected()){
			
			SwingUtilities.invokeLater(() -> {
				clientUser.getMessengerController().getMessengerFrame().getMessengerMenuBar().toggleConnectMenuText();
			});
			
			MessagesPanel messagesPanel = clientUser.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
			messagesPanel.clearAllMessages();
			MessagePanel successMessage = new MessagePanel(messagesPanel, "Successfully connected to the server.");
			messagesPanel.addMessage(successMessage);
			
			PacketMessage packetMessage = new PacketMessage(dataController.getUserName(), dataController.getUserColor(), "bonjour!");
			try {
				packetMessage.writeContent(dataOutputStream); // send our initial data to the server.
			}catch(Exception e) {
				clientUser.getMessengerController().getDebug().presentError("Giving Info", e);
				this.close();
			}
		}

		while(isConnected()){
			try{
				
				PacketMessage packetMessage = new PacketMessage(); // create new black Packet message
				packetMessage.readContent(dataInputStream); // read input stream, but also block till receiving data
				packetHandler.handlePacketMessage(packetMessage);
				
			}catch(SocketException e){
				MessagesPanel messagesPanel = clientUser.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
				messagesPanel.clearAllMessages();
				MessagePanel socketMessage = new MessagePanel(messagesPanel, "Socket closed.");
				messagesPanel.addMessage(socketMessage);
				this.close();
			}catch(EOFException e){
				MessagesPanel messagesPanel = clientUser.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
				messagesPanel.clearAllMessages();
				MessagePanel eofMessage = new MessagePanel(messagesPanel, "The server closed the connection.");
				messagesPanel.addMessage(eofMessage);
				this.close();
			}catch(Exception e){
				clientUser.getMessengerController().getDebug().presentError("Reading inside while", e);
				this.close();
			}
		}
	}
	
	public boolean isConnected(){
		return socket != null && !socket.isClosed() && socket.isConnected();
	}
	
	public void sendPacketMessage(PacketMessage packetMessage){
		try {
			packetMessage.writeContent(dataOutputStream);
		}catch(Exception e) {
			MessagesPanel messagePanel = clientUser.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
			MessagePanel socketMessage = new MessagePanel(messagePanel, "There was an internal error when sending a message.");
			messagePanel.addMessage(socketMessage);
			this.close();
		}
	}
	
	public void close(){
		try{
			if(isConnected()){
				dataOutputStream.close();
				dataInputStream.close();
				socket.close();
				
				SwingUtilities.invokeLater(() -> {
					clientUser.getMessengerController().getMessengerFrame().getMessengerMenuBar().toggleConnectMenuText();
				});
			}
		}catch(Exception e){
			clientUser.getMessengerController().getDebug().presentError("Closing Connection", e);
		}
	}
}
