package messenger.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DataController {

	private MessengerController messengerController;
	
	private boolean errorOccured;
	
	private String dataFilePath;
	private String ipAddress;
	private int port;

	public DataController(MessengerController messengerController) {
		this.messengerController = messengerController;

		this.setupDataFolder();
		this.parseDataIntoMembers();
	}
	
	private void setupDataFolder(){
		dataFilePath = this.getDefaultDataDirectory() + "/messenger";
		try{
			Files.createDirectory(Paths.get(dataFilePath));
			this.copyDefaultsToDataDirectory();
		}catch(IOException e) {
			if(e instanceof FileAlreadyExistsException){ // File already exists
				if(!(new File(dataFilePath + "/textData.txt").exists())){ // textData does not exist
					this.copyDefaultsToDataDirectory();
				}
			}else{
				messengerController.getDebug().presentError(e.getMessage());
			}
		}
	}
	
	private void parseDataIntoMembers(){
		String textDataString = "";
		try{
			byte[] textData = Files.readAllBytes(Paths.get(dataFilePath + "/textData.txt"));
			
		}catch(IOException e) {
			messengerController.getDebug().presentError(e.getMessage());
		}
	}
	
	public void saveData(){
		
	}
	
	private void copyDefaultsToDataDirectory(){
		try{
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/textData.txt"), Paths.get(dataFilePath + "/textData.txt"), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/defaultUser.png"), Paths.get(dataFilePath + "/defaultUser.png"), StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			messengerController.getDebug().presentError(e.getMessage());
		}
	}
	
	// Totally didn't have to look this up :P
	private String getDefaultDataDirectory() {
		String os = System.getProperty("os.name").toUpperCase();
		if (os.contains("WIN")) {
			return System.getenv("APPDATA");
		}else if(os.contains("MAC")) {
			return System.getProperty("user.home") + "/Library/Application Support";
		}else if(os.contains("NUX")) {
			return System.getProperty("user.home");
		}
		return System.getProperty("user.dir");
	}

	public boolean errorOccured() {
		return errorOccured;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
