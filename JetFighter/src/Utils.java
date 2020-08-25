import java.awt.*;

public class Utils {
    public static Rectangle getBounds(Sprite s) { //the method that sets the rectangle
        return new Rectangle(s.getX(),s.getY(),s.getWidth(),s.getHeight());
    }

    public static boolean checkCollisions(Rectangle r1,Rectangle r2) {  //the method thats checks the collision
        if(r1.intersects(r2))
            return true;
        else
            return false;
    }
}
