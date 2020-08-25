import javax.swing.ImageIcon;

public class Sprite { // this is a class which stores the imageIcon and it's x , y coordinate and it's
						// width,height

	private ImageIcon image;
	private int x, y;
	private int Width;
	private int Height;

	public Sprite(String string, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.Width = width;
		this.Height = height;

		try {

			image = new ImageIcon(getClass().getResource(string));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	public int getX() {
		return x;
	}

	public ImageIcon getImage() {
		return image;
	}

	public int getY() {
		return y;
	}

}
