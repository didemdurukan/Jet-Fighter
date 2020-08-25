import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	static ArrayList<Sprite> spriteArray = new ArrayList<>();
	Sprite playerJet;
	static ArrayList<Sprite> bullets = new ArrayList<>();
	static List<Sprite> bulletsList = Collections.synchronizedList(bullets);
	ArrayList<Sprite> lives = new ArrayList<>();
	List<Sprite> livesList = Collections.synchronizedList(lives);

	boolean gameFlag = true;
	String userPlaying;
	int enemyCount = 4;
	int score = 0;
	int lastScore;
	boolean gameOverNoEnemy;
	boolean gameOverNoLives;

	public GamePanel() {
		setFocusable(true);
		setRequestFocusEnabled(true);
		requestFocusInWindow();

		spriteArray.add(new Sprite("resource/pg.jpg", 0, 0, 616, 616));

		for (int i = 0; i < enemyCount; i++) {
			int minX = (i * (610 / enemyCount)); // sets the coordinate boundries for the enemies
			int maxX = minX + (610 / enemyCount);

			EnemyJet enemy = new EnemyJet(35 + (i * (150)), 30, minX, maxX); // places the enemies
			spriteArray.add(enemy);
		}

		playerJet = new Sprite("resource/myjet.png", 250, 490, 80, 80);
		spriteArray.add(playerJet);

		lives.add(new Sprite("resource/heart.png", 450, 500, 40, 40));
		lives.add(new Sprite("resource/heart.png", 490, 500, 40, 40));
		lives.add(new Sprite("resource/heart.png", 530, 500, 40, 40));

		Thread thread = new Thread(this);
		thread.start(); // starts the thread

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < spriteArray.size(); i++) {
			Sprite s = spriteArray.get(i); // draws the enemies and fighter jet
			g.drawImage(s.getImage().getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
		}
		synchronized (bulletsList) {
			for (int i = 0; i < bulletsList.size(); i++) { // draws the bullets
				Sprite s = bulletsList.get(i);
				g.drawImage(s.getImage().getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
			}
		}

		synchronized (livesList) {
			for (int i = 0; i < livesList.size(); i++) { // draws the lives
				Sprite s = livesList.get(i);
				g.drawImage(s.getImage().getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
			}
		}

		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.setColor(Color.BLUE); // draws the score board
		g.drawString("SCORE: " + score, 480, 30);

		if (gameOverNoEnemy) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 250, 300); // draws the game over message
			g.drawString("YOU WIN!", 250, 340);

		} else if (gameOverNoLives) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 250, 300); // draws the game over message
			g.drawString("YOU LOST!", 250, 340);

		}

		LoginFrame data = new LoginFrame();
		userPlaying = data.user_name;
		if (userPlaying != null) {

			g.drawString("Currently playin user: " + userPlaying, 30, 30); // draws the current players name to the
																			// screen

		} else {
			g.drawString("Currently no player ", 30, 30);

		}

	}

	public void rightPressed() {
		playerJet.setX(playerJet.getX() + 6); // moves the fighter jet
	}

	public void leftPressed() {
		playerJet.setX(playerJet.getX() - 6); // moves the fighter jet
	}

	@Override
	public void run() {
		while (gameFlag) {
			if (livesList.size() > 0 && enemyCount != 0) { // checks if the game is finished or not
				for (Sprite s : spriteArray) {
					if (s instanceof EnemyJet) {
						((EnemyJet) s).move(); // if the object in the sprite array belongs to enemy move the enemy
					}
				}
				synchronized (this) {
					for (int i = bulletsList.size() - 1; i >= 0; i--) {
						Bullet b = (Bullet) bulletsList.get(i); // checks the bulletList
						if (b == null) {
							System.out.println("Non-Bullet in bullet array!!!");
							continue;
						}

						b.move(); // moves the bullets

						if (b.isPlayers()) { // checks whether the bullet belongs to the enemy or not
							for (Sprite sprite : spriteArray) {
								if (sprite instanceof EnemyJet) { // checks the object in sprite array is an object of
																	// enemy
									EnemyJet e = (EnemyJet) sprite;
									if (b.checkCollision(Utils.getBounds(e))) { // checks if the bullet from player and
																				// the enemy jet collapse
										spriteArray.remove(e); // if they collapse remove the enemy jet e
										bulletsList.remove(i); // if they collapse remove the bullet
										score += 10; // increment the score
										enemyCount--; // decrement the enemyCount
										break;
									}
								}
							}
						} else { // if the bullet belongs to the enemy
							if (b.checkCollision(Utils.getBounds(playerJet))) { // checks whether the bullet collapse
																				// with the enemy jet
								livesList.remove(livesList.size() - 1); // if collapse remove one live
								bulletsList.remove(b); // remove the bullet
								break;
							}
						}
					}
				}
			} else {

				if (enemyCount == 0) { // if enemy count is zero, this means the game is over user wins.
					lastScore = score;
					gameFlag = false; // since the game is over it stops the thread
					gameOverNoEnemy = true; // if no enemies left game is over user wins
					FileOperation.writeScore(userPlaying, lastScore); // writes the final score and user to a file

				} else {
					lastScore = score;
					gameFlag = false; // since the game is over it stops the thread
					gameOverNoLives = true; // if no lives left , this means the game is over user lose.
					FileOperation.writeScore(userPlaying, lastScore); // writes the final score and the user to a file

				}

			}

			this.repaint(); // paints every time

			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
