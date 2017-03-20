package messenger.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;

import messenger.packet.PacketMessage;
import messenger.view.messagepanel.MessagePanel;
import messenger.view.messagepanel.MessagesPanel;

public class ServerConnection extends Thread {
	
	private MessengerServer messengerServer;
	
	private ServerSocket serverSocket;
	private ArrayList<ClientConnection> clientConnections;
	
	public ServerConnection(MessengerServer messengerServer){
		this.messengerServer = messengerServer;
		this.clientConnections = new ArrayList<ClientConnection>();
	}
	
	@Override
	public void run(){
		try {
			this.serverSocket = new ServerSocket(messengerServer.getMessengerController().getDataController().getPort());
		}catch(Exception e){
			messengerServer.getMessengerController().getDebug().presentError("Inside while", e);
			this.stopListening();
		}
		
		if(isServerOpen()){
			logWithMessage("Started listening on port " + messengerServer.getMessengerController().getDataController().getPort());
			
			SwingUtilities.invokeLater(() -> {
				messengerServer.getMessengerController().getMessengerFrame().getMessengerMenuBar().toggleServerMenuText();
			});
			
			Enumeration<NetworkInterface> networkInterfaces;
			try {
				networkInterfaces = NetworkInterface.getNetworkInterfaces();
				while(networkInterfaces.hasMoreElements()){
					NetworkInterface network = networkInterfaces.nextElement();
					Enumeration<InetAddress> addresses = network.getInetAddresses();
					while(addresses.hasMoreElements()){
						InetAddress ip = addresses.nextElement();
						String hostAddress  = ip.getHostAddress();
						if(!ip.isLoopbackAddress() && hostAddress.length() < 20){
							logWithMessage("You have an IP Address of: " + hostAddress);
						}
					}
				}
			}catch(SocketException ex){
				messengerServer.getMessengerController().getDebug().presentError("Gathering Network interfaces!", ex);
				this.stopListening();
			}
		}
		
		while(isServerOpen()){
			try {
				
				Socket clientSocket = serverSocket.accept();
				ClientConnection clientConnection = new ClientConnection(this, clientSocket);
				this.clientConnections.add(clientConnection);
				clientConnection.start();
				
			}catch(SocketException e){
				this.stopListening();
			}catch(Exception e){
				messengerServer.getMessengerController().getDebug().presentError("Inside while", e);
				this.stopListening();
			}
		}
	}
	
	public void stopListening(){
		try{
			if(isServerOpen()){
				for(ClientConnection client : clientConnections){
					client.closeConnection(false);
				}
				clientConnections.clear();
				serverSocket.close();
			}
			SwingUtilities.invokeLater(() -> {
				messengerServer.getMessengerController().getMessengerFrame().getMessengerMenuBar().toggleServerMenuText();
			});
			messengerServer.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel().clearAllMessages();
		}catch(Exception e){
			messengerServer.getMessengerController().getDebug().presentError("Closing server!", e);
		}
	}
	
	public void sendPacketToClients(PacketMessage packetMessage, ClientConnection excluded){
		for(ClientConnection client : clientConnections){
			if(client != excluded){
				client.sendPacketMessage(packetMessage);
			}
		}
	}
	
	public ArrayList<ClientConnection> getClientConnections(){
		return clientConnections;
	}
	
	public void logWithMessage(String log){
		SwingUtilities.invokeLater(() -> {
			MessagesPanel messagesPanel = messengerServer.getMessengerController().getMessengerFrame().getMessengerPanel().getMessagesPanel();
			MessagePanel messagePanel = new MessagePanel(messagesPanel, log);
			messagesPanel.addMessage(messagePanel);
		});
	}
	
	public boolean isServerOpen(){
		return serverSocket == null ? false : !serverSocket.isClosed() && serverSocket.isBound();
	}
	
	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
