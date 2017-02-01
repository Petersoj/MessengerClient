package messenger.view.menubar.connect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.util.MessengerColor;

public class ConnectOptionPanel extends JPanel {
	
	private ConnectOptionDialog connectOptionDialog;
	
	private SpringLayout springLayout;
	
	private JLabel ipAddressLabel;
	private JLabel portLabel;
	private JTextField ipAddressField;
	private JTextField portField;
	private JButton connectButton;
	
	public ConnectOptionPanel(ConnectOptionDialog connectOptionDialog){
		super();
		this.connectOptionDialog = connectOptionDialog;
		this.springLayout = new SpringLayout();
		
		this.ipAddressLabel = new JLabel("IP Address:");
		this.portLabel = new JLabel("Port:");
		this.ipAddressField = new JTextField();
		this.portField = new JTextField();
		this.connectButton = new JButton("Connect");
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void setupComponents(){
		DataController dataController = this.connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		Font verdanaFont = dataController.getVerdanaFont();
		Color color = dataController.getUserColor().getColor();
		
		this.ipAddressLabel.setFont(verdanaFont);
		this.portLabel.setFont(verdanaFont);
		
		this.ipAddressField.setFont(verdanaFont);
		this.portField.setFont(verdanaFont);
		this.ipAddressField.setCaretColor(color);
		this.portField.setCaretColor(color);
		this.ipAddressField.setText(dataController.getIPAddress());
		this.portField.setText(String.valueOf(dataController.getPort()));
		
		this.connectButton.setFont(verdanaFont);
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(ipAddressLabel);
		this.add(ipAddressField);
		this.add(portLabel);
		this.add(portField);
		this.add(connectButton);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, ipAddressLabel, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, ipAddressLabel, 30, SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, ipAddressField, 0, SpringLayout.VERTICAL_CENTER, ipAddressLabel);
		springLayout.putConstraint(SpringLayout.WEST, ipAddressField, 20, SpringLayout.EAST, ipAddressLabel);
		springLayout.putConstraint(SpringLayout.EAST, ipAddressField, -40, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, portLabel, 15, SpringLayout.SOUTH, ipAddressLabel);
		springLayout.putConstraint(SpringLayout.WEST, portLabel, 0, SpringLayout.WEST, ipAddressLabel);
		
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, portField, 0, SpringLayout.VERTICAL_CENTER, portLabel);
		springLayout.putConstraint(SpringLayout.WEST, portField, 0, SpringLayout.WEST, ipAddressField);
		springLayout.putConstraint(SpringLayout.EAST, portField, -160, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.EAST, connectButton, 0, SpringLayout.EAST, ipAddressField);
		springLayout.putConstraint(SpringLayout.NORTH, connectButton, 10, SpringLayout.SOUTH, portField);
	}
	
	private void setupListeners(){
		this.ipAddressField.addActionListener((e) -> {
			changeIPAddress();
		});
		this.ipAddressField.addFocusListener(new FocusListener() {  // Used if they close the dialog without hitting return
			@Override
			public void focusLost(FocusEvent e) {
				changeIPAddress();
			}
			@Override
			public void focusGained(FocusEvent e) { } // unused
		});
		
		this.portField.addActionListener((e) -> {
			changePort();
		});
		this.portField.addFocusListener(new FocusListener() { // Used if they close the dialog without hitting return
			@Override
			public void focusLost(FocusEvent e) {
				changePort();
			}
			@Override
			public void focusGained(FocusEvent e) { } // unused
		});
		
		this.connectButton.addActionListener((e) -> {
			connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getClientUser().connectToServer();
		});
	}
	
	private void changeIPAddress(){
		DataController dataController = this.connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		String typedInIP = this.ipAddressField.getText();
		if(!(typedInIP.equals(dataController.getIPAddress()))){ // Only change the username if changed at all
			if(typedInIP.contains(",")){
				typedInIP = typedInIP.replaceAll(",", ""); // If they type in ',', They will cause a parsing error on the next startup.
			}
			if(typedInIP.contains("=")){
				typedInIP = typedInIP.replaceAll("=", ""); // If they type in ':', They will cause a parsing error on the next startup.
			}
			if(typedInIP.length() > 100){
				typedInIP = typedInIP.substring(0, 100);
			}
			this.ipAddressField.setText(typedInIP);
			
			dataController.setIPAddress(typedInIP);
			dataController.saveData(true);
			
			this.ipAddressLabel.requestFocusInWindow(); // Makes Text field un-focused
		}
	}
	
	private void changePort(){
		DataController dataController = this.connectOptionDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		String typedInPort = this.portField.getText();
		int portNumber = dataController.getPort();
		try {
			portNumber = Integer.parseInt(typedInPort);
		} catch (Exception e){
			this.portField.setText("Error");
		}
		if(portNumber != dataController.getPort()){
			if(portNumber > 25 || portNumber < 65535){
				dataController.setPort(portNumber);
				dataController.saveData(true);
				System.out.println("saved");
				this.ipAddressLabel.requestFocusInWindow(); // Makes Text field un-focused
			}
		}
	}
	
	public JLabel getIPAddressLabel(){
		return ipAddressLabel;
	}
	
	public void setTextFieldCaretColors(MessengerColor messengerColor){
		this.ipAddressField.setCaretColor(messengerColor.getColor());
		this.portField.setCaretColor(messengerColor.getColor());
	}

}
