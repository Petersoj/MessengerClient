package messenger.controller;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import messenger.util.MessengerColor;
import messenger.util.Utils;

public class DataController {

	private MessengerController messengerController;
	
	private boolean errorOccured;
	private boolean isOSX;
	
	private String dataFilePath;
	private String ipAddress;
	private int port;
	private String userName;
	private MessengerColor userColor;
	
	private Font verdanaFont;
	private BufferedImage messengerIcon;
	private BufferedImage attachmentIcon;
	
	private BufferedImage fileImage;
	
	private ImageIcon aboutIcon;
	private ImageIcon githubIcon;
	private ImageIcon instagramIcon;
	private ImageIcon websiteIcon;

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
		}catch(Exception e) {
			if(e instanceof FileAlreadyExistsException){ // File already exists
				if(!(new File(dataFilePath + "/textData.txt").exists())){ // textData does not exist
					this.copyDefaultsToDataDirectory();
				}
			}else{
				this.errorOccured = true;
				messengerController.getDebug().presentError("Setting up Data Folder", e);
			}
		}
	}
	
	private void parseDataIntoMembers(){ // I know I should use some markup language with its respective parser, but nah.
		try{
			String stringTextData = new String(Files.readAllBytes(Paths.get(dataFilePath + "/textData.txt"))); // Text Data
			String[] textData = stringTextData.split(",");
			if(textData.length < 3){
				throw new ParseException("Could not parse TextData", textData.length);
			}
			for(String data : textData){
				String[] splitData = data.split("=");
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
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Parsing Data", e);
		}
	}
	
	private void loadOtherAssets(){
		try {
			this.messengerIcon = ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/messengerIcon.png"));
			this.attachmentIcon = ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/attachmentIcon.png"));
			
			this.fileImage = ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/fileImage.png"));
			
			this.aboutIcon = new ImageIcon(Utils.getScaledImage(ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/about.png")), 20, 20));
			this.githubIcon = new ImageIcon(Utils.getScaledImage(ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/github.png")), 20, 20));
			this.instagramIcon = new ImageIcon(Utils.getScaledImage(ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/instagram.png")), 20, 20));
			this.websiteIcon = new ImageIcon(Utils.getScaledImage(ImageIO.read(this.getClass().getResourceAsStream("/messenger/assets/website.png")), 20, 20));
		} catch (Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Load Assets", e);
		}
		this.verdanaFont = new Font("Verdana", Font.PLAIN, 15);
	}
	
	public void saveData(){
		try{
			FileWriter fileWriter = new FileWriter(dataFilePath + "/textData.txt", false);
			String data = "ip=" + this.ipAddress + ",port=" + String.valueOf(this.port) + ",name=" + this.userName + ",color=" + this.userColor.toString();
			fileWriter.write(data);
			fileWriter.close();
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Load Assets", e);
		}
	}
	
	public void copyDefaultsToDataDirectory(){
		try{
			Files.copy(this.getClass().getResourceAsStream("/messenger/assets/textData.txt"), Paths.get(dataFilePath + "/textData.txt"), StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			this.errorOccured = true;
			messengerController.getDebug().presentError("Copying Defaults", e);
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
	
	public MessengerController getMessengerController(){
		return messengerController;
	}

	public boolean errorOccured() {
		return errorOccured;
	}
	
	public boolean isOSX() {
		return isOSX;
	}

	public String getIPAddress() {
		return ipAddress;
	}

	public void setIPAddress(String ipAddress) {
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
	
	public Font getVerdanaFont() {
		return verdanaFont;
	}

	public BufferedImage getMessengerIcon(){
		return messengerIcon;
	}

	public BufferedImage getAttachmentIcon() {
		return attachmentIcon;
	}
	
	public BufferedImage getFileImage(){
		return fileImage;
	}
	
	public ImageIcon getAboutIcon() {
		return aboutIcon;
	}
	
	public ImageIcon getGithubIcon() {
		return githubIcon;
	}

	public ImageIcon getInstagramIcon() {
		return instagramIcon;
	}

	public ImageIcon getWebsiteIcon() {
		return websiteIcon;
	}
	
}
