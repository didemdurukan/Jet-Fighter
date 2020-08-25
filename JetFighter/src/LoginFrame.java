import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	JLabel passwordLabel;
	JLabel usernameLabel;
	JTextField usernameText;
	JTextField passwordText;
	

	public static String user_name;
	private String pass_word;
	private JButton Login;
	private JButton cancel;
	private String userData;
	private String fileData;

	public LoginFrame() {

		super("Login");
		setLayout(new BorderLayout());
		super.setSize(100,80);

		JLabel background = new JLabel(new ImageIcon(getClass().getResource("resource/background.jpg")));
		add(background);
		background.setLayout(new FlowLayout());

		Login = new JButton("Login");
		cancel = new JButton("Cancel");
		
		background.setBounds(100,30,400,30);

		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");

		usernameLabel.setFont(new Font("Times Roman", Font.BOLD, 16));
		usernameLabel.setForeground(Color.BLACK);

		passwordLabel.setFont(new Font("Times Roman", Font.BOLD, 16));
		passwordLabel.setForeground(Color.BLACK);

		background.add(usernameLabel); // label
		background.add(passwordLabel); // label

		usernameText = new JTextField(10);
		background.add(usernameText);

		passwordText = new JTextField(10);
		background.add(passwordText);

		background.add(Login, BorderLayout.SOUTH);
		background.add(cancel, BorderLayout.SOUTH);

		usernameLabel.setBounds(80, 700, 200, 30);
		passwordLabel.setBounds(80, 110, 200, 30);
		usernameText.setBounds(300, 70, 200, 30);
		passwordText.setBounds(300, 110, 200, 30);

		ButtonHandler buttonHandler = new ButtonHandler();
		Login.addActionListener((ActionListener) buttonHandler);
		cancel.addActionListener(buttonHandler);

	}

	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Object source = event.getSource();

			if (source == cancel) {
				dispose();
				GameFrame frame = new GameFrame();
				frame.setSize(600, 600);
				frame.setVisible(true);
			} else if (source == Login) {

				user_name = usernameText.getText(); //gets the user's user name info
				pass_word = passwordText.getText(); //gets the user's password info
				userData = user_name + pass_word; //merges the userData

				
					try {
						fileData = FileOperation.readFile(); //reads the file
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(fileData);
					
					if(fileData.toLowerCase().contains(userData.toLowerCase())) { //checks if the userData is a subString of the FileData
						JOptionPane.showMessageDialog(null,"Login Succesfull","Informantion",JOptionPane.PLAIN_MESSAGE);
						dispose();
						GameFrame frame = new GameFrame();
						frame.setSize(600,600);
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"No User Found","Informantion",JOptionPane.PLAIN_MESSAGE);
					}

				

			}

		}

	}

}
