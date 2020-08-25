import java.util.Random;

public class EnemyJet extends Sprite {
	private int minX, maxX;
	private long shootTime = 3000;
	private long lastShotTime = 0;
	private int velocity;
	private int direction = 1;

	public EnemyJet(int x, int y, int minX, int maxX) {
		super("resource/enemy.png", x, y, 80, 80);

		this.minX = minX;
		this.maxX = maxX - getWidth();

		Random random = new Random();
		this.velocity = random.nextInt(3) + 2; // this generates a random number for velocity
	}

	public void move() {

		randomMove();

		// checks if the last shoot time and my shoot times total equals to current
		// time. This generates 1 shoot per 3 seconds
		if (lastShotTime + shootTime < System.currentTimeMillis()) {
			Random rand = new Random();
			int num = rand.nextInt(10);

			// if random number is zero,add to the array,last 2 parameters are false because
			// they are enemies bullets
			if (num == 0) {
				lastShotTime = System.currentTimeMillis();
				GamePanel.bullets.add(new Bullet(getX(), getY(), false, false));
			}
		}
	}

	private void randomMove() {
		Random rand = new Random();
		int randomNum = rand.nextInt(30); // generates random number for changing the direction
		if (randomNum == 0) {
			direction *= -1;
		}

		int moveStep = getX() + (velocity * direction);
		if (moveStep < minX) {
			direction = 1; // if the moveStep is smaller from the min value that the enemyJet can go ,
							// change the direction
			moveStep = minX;
		} else if (moveStep > maxX) {
			direction = -1; // if the moveStep is bigger from the max value that the enemyJet can go, change
							// direction
			moveStep = maxX;
		}

		setX(moveStep); // set the x coordinate
	}

}
