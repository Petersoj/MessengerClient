package messenger.controller;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import messenger.util.MessengerColor;

public class DataController {

	private MessengerController messengerController;
	
	private boolean errorOccured;
	private boolean isOSX;
	
	private String dataFilePath;
	private String ipAddress;
	private int port;
	private String userName;
	private MessengerColor userColor;
	private BufferedImage userIcon;
	
	private Font verdanaFont;
	private BufferedImage messengerIcon;
	private BufferedImage attachmentIcon;

	public DataController(MessengerController messengerController) {
		this.messengerController = messengerController;
		this.errorOccured = false; // I know it is false by default, but just want to make sure lol.

		this.setupDataFolder();
		this.parseDataIntoMembers();
		this.loadOtherAssets();
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
				this.errorOccured = true;
				messengerController.getDebug().presentError("Setting up Data Folder", e.getMessage());
			}
		}
	}
	
	private void parseDataIntoMembers(){ // I know I should use some markup language with its respective parser, but nah.
		try{
			String textData = new String(Files.readAllBytes(Paths.get(dataFilePath + "/textData.txt"))); // Text Data
			String[] textDatas = textData.split("-");
			for(String data : textDatas){
				String[] splitData = data.split(":");
				String key = splitData[0];
				String value = splitData[1];
				if(key.equalsIgnoreCase("ip")){
					this.ipAddress = value;
				}else if(key.equalsIgnoreCase("port")){
					this.port = Integer.valueOf(value);
				}else if(key.equalsIgnoreCase("name")){
					this.userName = value;
				}else if(key.equalsIgnoreCase("color")){
					this.userColor = MessengerColor.valueOf(value);
				}
			}
			this.userIcon = ImageIO.read(new File(dataFilePath + "/userIcon.png"));
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Parsing Data", e.getMessage());
		}
	}
	
	private void loadOtherAssets(){
		try {
			this.messengerIcon = ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/messengerIcon.png"));
			this.attachmentIcon = ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/attachmentIcon.png"));
		} catch (Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Load Assets", e.getMessage());
		}
		this.verdanaFont = new Font("Verdana", Font.PLAIN, 15);
	}
	
	public void saveData(boolean saveOnlyText){
		try{
			if(!saveOnlyText){
				ImageIO.write(this.userIcon, "png", new File(dataFilePath + "/userIcon.png"));
			}
			FileWriter fileWriter = new FileWriter(dataFilePath + "/textData.txt", false);
			String data = "ip:" + this.ipAddress + "-port:" + String.valueOf(this.port) + "-name:" + this.userName + "-color:" + this.userColor.toString();
			fileWriter.write(data);
			fileWriter.close();
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Load Assets", e.getMessage());
		}
	}
	
	private void copyDefaultsToDataDirectory(){
		try{
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/textData.txt"), Paths.get(dataFilePath + "/textData.txt"), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/userIcon.png"), Paths.get(dataFilePath + "/userIcon.png"), StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Copying Defaults", e.getMessage());
		}
	}
	
	// Totally didn't have to look this up :P
	private String getDefaultDataDirectory() {
		String os = System.getProperty("os.name").toUpperCase();
		if (os.contains("WIN")) {
			return System.getenv("APPDATA");
		}else if(os.contains("MAC")) {
			this.isOSX = true;
			return System.getProperty("user.home") + "/Library/Application Support";
		}else if(os.contains("NUX")) {
			return System.getProperty("user.home");
		}
		return System.getProperty("user.dir");
	}

	public boolean errorOccured() {
		return errorOccured;
	}
	
	public boolean isOSX() {
		return isOSX;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MessengerColor getUserColor() {
		return userColor;
	}

	public void setUserColor(MessengerColor userColor) {
		this.userColor = userColor;
	}

	public BufferedImage getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(BufferedImage userIcon) {
		this.userIcon = userIcon;
	}

	public Font getVerdanaFont() {
		return verdanaFont;
	}

	public BufferedImage getMessengerIcon(){
		return messengerIcon;
	}

	public BufferedImage getAttachmentIcon() {
		return attachmentIcon;
	}
}
