package messenger.view.menubar.about;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import messenger.controller.DataController;
import messenger.util.Utils;

public class AboutPanel extends JPanel {
	
	private AboutDialog aboutDialog;
	
	private SpringLayout springLayout;
	private JLabel titleLabel;
	private JTextArea aboutTextArea;
	
	public AboutPanel(AboutDialog aboutDialog){
		super();
		this.aboutDialog = aboutDialog;
		
		this.springLayout = new SpringLayout();
		this.titleLabel = new JLabel();
		this.aboutTextArea = new JTextArea();
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
	}
	
	private void setupComponents(){
		DataController dataController = aboutDialog.getMessengerPanel().getMessengerFrame().getMessengerController().getDataController();
		
		this.titleLabel.setFont(dataController.getVerdanaFont());
		this.titleLabel.setText("Messenger");
		this.titleLabel.setIcon(new ImageIcon(Utils.getScaledImage(dataController.getMessengerIcon(), 100, 100)));
		this.titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
		this.titleLabel.setHorizontalTextPosition(JLabel.CENTER);
		this.titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String aboutText = "This application was developed by Jacob Peterson (me) for the 2017 AP Computer Science Create task. "
				+ "You can send and receive messages, pictures that render in frame, and other files that can be downloaded. "
				+ "This is super awesome. Yeah I know.";

		this.aboutTextArea.setOpaque(false);
		this.aboutTextArea.setEditable(false);
		this.aboutTextArea.setFont(dataController.getVerdanaFont());
		this.aboutTextArea.setLineWrap(true);
		this.aboutTextArea.setWrapStyleWord(true);
		this.aboutTextArea.setText(aboutText);
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(titleLabel);
		this.add(aboutTextArea);
		this.revalidate();
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 2, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, titleLabel, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, titleLabel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, aboutTextArea, 20, SpringLayout.SOUTH, titleLabel);
		springLayout.putConstraint(SpringLayout.EAST, aboutTextArea, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, aboutTextArea, 10, SpringLayout.WEST, this);
	}

}
