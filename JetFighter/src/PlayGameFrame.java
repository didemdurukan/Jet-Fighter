import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayGameFrame extends JFrame {

	GamePanel gamePanel = new GamePanel();

	int score;

	public PlayGameFrame() {
		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("resource/pg.jpg")));
		add(background);
		this.setTitle("Play Game");
		this.setSize(610, 610);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		add(gamePanel, BorderLayout.CENTER);

		// listens the key events and calls the function accordingly
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {

				if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					gamePanel.leftPressed();
				} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					gamePanel.rightPressed();
				} else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
					synchronized (GamePanel.bulletsList) {
						// creates bullets accordingly to fighterJet's position every time the space
						// pressed
						Bullet b = new Bullet(gamePanel.playerJet.getX(), gamePanel.playerJet.getY(), true, true);
						GamePanel.bulletsList.add(b);
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
