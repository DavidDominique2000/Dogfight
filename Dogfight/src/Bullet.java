import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;

public class Bullet extends JPanel{

	private int x, y;
	public static final int SIZE = 5;
	private int speed = 14;
	private AffineTransform transform;
	private Image bullet;
	
	public Bullet(int x, int y,  AffineTransform transform){
		this.x = x;
		this.y = y;
		bullet = new ImageIcon("bullet.png").getImage();
		this.transform = transform;
	}
	
	
	//Moves the bullet at a constant rate
	public void move(){
		x += speed;
		transform.translate(speed, 0);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g.fillOval(x, y, SIZE, SIZE);
	}

	
	
}
