package messenger.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import messenger.user.UserColor;

public class DataController {

	private MessengerController messengerController;
	
	private String dataFilePath;
	
	private String ipAddress;
	private int port;
	private String userName;
	private UserColor userColor;

	public DataController(MessengerController messengerController) {
		this.messengerController = messengerController;

		this.setupDataFolder();
		this.parseDataIntoMembers();
	}
	
	private void setupDataFolder(){
		dataFilePath = this.getDefaultDataDirectory() + "/messenger"; 
	
		File dataFile = new File(dataFilePath);
		if(dataFile.mkdir()){ // directory was created.
			this.copyDefaultsToDataDirectory();
		}else{ // directory already existed.
			
		}
	}
	
	private void parseDataIntoMembers(){
		String textDataString = "";
		
		try{
			byte[] textData = Files.readAllBytes(Paths.get(dataFilePath + "/textData.txt"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
//		try {
//			Files.readAllBytes(Paths.get(""));
//			File textDataFile = new File(dataFilePath);
//			FileInputStream fileInput = new FileInputStream(textDataFile);
//			
//			byte[] data = new byte[(int) textDataFile.length()];
//			
//			fileInput.read(data);
//			fileInput.close();
//			
//			textData = new String(data, "UTF-8");
//		}catch(IOException e) {
//			messengerController.getDebug().presentError(e.getMessage());
//		}
		if(!textData.equals("")){ // parse the data using .split
			String[] splitData = textData.split("-");
			
		}else{ // Prevent the program from running because we cannot get their data.
			
		}
	}
	
	public void saveData(){
		
	}
	
	private void copyDefaultsToDataDirectory(){
		try{
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/textData.txt"), Paths.get(dataFilePath + "/textData.txt"), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/defaultUser.png"), Paths.get(dataFilePath + "/defaultUser.png"), StandardCopyOption.REPLACE_EXISTING);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

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

	public String getUserName() {
		return userName;
	}

	public UserColor getUserColor() {
		return userColor;
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
