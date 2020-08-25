import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class GameFrame extends JFrame {
	
		private JButton playgame;
		private static JButton register;
		private JButton login;
		private JButton scoretable;
		private String username;
		private String password;
		
		public GameFrame() {

			super("Fighter Jet");
			setLayout(new FlowLayout());
			
			JLabel background = new JLabel(new ImageIcon(getClass().getResource("resource/background.jpg")));
			add(background);
			background.setLayout(new FlowLayout());
			
			playgame = new JButton("Play Game");
			background.add(playgame);
			
			register = new JButton("Register");
			background.add(register);
			
			login = new JButton("Login");
			background.add(login);
			
			scoretable = new JButton("Score Table");
			background.add(scoretable);
			
			ButtonHandler handler = new ButtonHandler();
			
			playgame.addActionListener(handler);
			register.addActionListener(handler);
			login.addActionListener(handler);
			scoretable.addActionListener(handler);
			
			
		}
		
		private  class ButtonHandler implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) {
				
				Object source = event.getSource(); 
				
				if(source == register) {
					
					dispose();
					RegisterFrame registerwindow = new RegisterFrame();
					registerwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					registerwindow.setSize(400,400);
					registerwindow.setVisible(true);
				
					

				}
				else if(source == playgame) {
					
					dispose();
					PlayGameFrame myframe = new PlayGameFrame();
					myframe.setFocusable(true);
					
					
					
				}
				else if(source == login) {
					
					dispose();
					LoginFrame login = new LoginFrame();
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.setSize(400,400);
					login.setVisible(true);
					
					
				}
				
				else if(source == scoretable) {
					
					dispose();
					ScoreTableFrame scoreTable = new ScoreTableFrame();
					scoreTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					scoreTable.setSize(400,400);
					scoreTable.setVisible(true);
					
					
					
					
				}
				
			}
		
			 
		}
}


		
		


	
	


