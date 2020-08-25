import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class RegisterFrame extends JFrame{
	
	private String data;
	private String user_name;
	private String pass_word;
	private JButton Register;
	private JButton cancel;
	JLabel password;
	JLabel username;
	JTextField usernameText;
	JTextField passwordText;
	

	
	
	
	
	
	public RegisterFrame() {
		
			super("Register");
			setLayout(new BorderLayout());
			super.setSize(400,400);
			
			
			JLabel background = new JLabel(new ImageIcon(getClass().getResource("resource/background.jpg")));
			add(background);
			background.setLayout(new FlowLayout());
			
			Register = new JButton("Register");
			cancel = new JButton("Cancel");
			
			username = new JLabel("Username: ");
			password = new JLabel("Password: ");
			
			username.setFont(new Font("Times Roman", Font.BOLD,16));
			username.setForeground(Color.BLACK);
			
			password.setFont(new Font("Times Roman", Font.BOLD,16));
			password.setForeground(Color.BLACK);
			
			background.add(username, BorderLayout.NORTH); //label
			background.add(password, BorderLayout.CENTER); //label
			
			
			usernameText = new JTextField(15);
	        background.add(usernameText, BorderLayout.NORTH);
	        
	        
	        passwordText = new JTextField(15);
			background.add(passwordText, BorderLayout.CENTER);
			
			
			background.add(Register,BorderLayout.SOUTH);
			background.add(cancel,BorderLayout.SOUTH);
			
		
			username.setBounds(80, 70, 200, 30);
			password.setBounds(80, 110, 200, 30);
			usernameText.setBounds(300, 70, 200, 30);
			passwordText.setBounds(300, 110, 200, 30);
			//register.setBounds(150, 160, 100, 30);
			 
			
			
			
			ButtonHandler buttonHandler = new ButtonHandler();
			Register.addActionListener((ActionListener) buttonHandler);
			cancel.addActionListener(buttonHandler);
	}
	
	private class ButtonHandler implements ActionListener{
		
		
		public void actionPerformed(ActionEvent event) {
			
			Object source = event.getSource();
			
			if(cancel == source) {
				
				dispose();
				GameFrame frame = new GameFrame();
				frame.setSize(600,600);
				frame.setVisible(true);
				
			}
			else if (Register == source) {
				
				pass_word = passwordText.getText(); //gets the user name info
				user_name = usernameText.getText(); //gets the password info
				data = user_name + pass_word; // merges the user data
				
	
				FileOperation.writeFile(data); //writes to a file
				
				JOptionPane.showMessageDialog(null,"Data Saved","Informantion",JOptionPane.PLAIN_MESSAGE);
				dispose();
				GameFrame frame = new GameFrame();
				frame.setSize(600,600);
				frame.setVisible(true);
				
				
				
			}
			
			
		}
		
	}
	

}
