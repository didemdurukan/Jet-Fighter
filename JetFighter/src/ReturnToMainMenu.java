import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReturnToMainMenu extends JFrame {

	JButton playAgain;
	JButton backToTheMenu;

	public ReturnToMainMenu() {

		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("resource/pg.jpg")));
		add(background);
		this.setTitle("Game Over");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		playAgain = new JButton("Play Again");
		background.add(playAgain);

		backToTheMenu = new JButton("Back to the Menu");
		background.add(backToTheMenu);

		ButtonHandler handler = new ButtonHandler();

		playAgain.addActionListener(handler);
		backToTheMenu.addActionListener(handler);

	}

	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Object source = event.getSource();

			if (source == backToTheMenu) {

				dispose();
				GameFrame menu = new GameFrame();
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menu.setSize(600, 600);
				menu.setVisible(true);

			} else if (source == playAgain) {

				dispose();
				PlayGameFrame myframe = new PlayGameFrame();
				myframe.setFocusable(true);

			}

		}

	}

}
