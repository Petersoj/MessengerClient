package messenger.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MessengerRunner {
	
	public static void main(String[] args) {
		
		try {
			Files.createDirectories(Paths.get(System.getProperty("user.home") + "/Desktop/poop/poop/poop"));
			
			Files.copy(MessengerRunner.class.getResourceAsStream("/messenger/assets/defaultUser.png"), 
					Paths.get(System.getProperty("user.home") + "/Desktop/poop/poop/poop/poop.png"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * MessengerController controller = new MessengerController();
		controller.setupMessenger();
		 */
	}

}
