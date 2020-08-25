import java.awt.Rectangle;

public class Bullet extends Sprite {

    private boolean isPlayers; //boolean value which determines the bullet belongs to the fighter jet or the enemy
    private boolean up; //boolean value which determines the bullet goes up or down

    public Bullet(int x,int y, boolean up, boolean players) {
        super("resource/mybullet.png",x,y,30,30);

        this.up = up;
        isPlayers = players;
    }

    public void move() {
        setY((up ? getY() - 10 : getY() + 10));  // if the bullet goes up increment the Y coordinate with 10 other wise decrement it by 10
    }

    public boolean checkCollision(Rectangle rect) {
        Rectangle r = new Rectangle(getX(), getY(), getWidth(), getHeight());  //checks the collision
        return r.intersects(rect);
    }

    public boolean isPlayers() {
        return isPlayers;	//returns true if the bullet belongs to the fighter jet
    }
}
