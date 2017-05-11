import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;

public class Bullet extends JPanel{

	private int x, y;
	private int angle;
	public static final int SIZE = 5;
	private int speed = 60;
	private Rectangle hitbox;
	private AffineTransform transform;
	private Image bullet = new ImageIcon("bullet.png").getImage();
	
	public Bullet(AffineTransform transform, int angle){
		transform.translate(90, 15);
		transform.scale(-1, 1);
		bullet = bullet.getScaledInstance(20, 5, 100);
		this.transform = transform;
//		Dogfight.panel.repaint();

	}
	
	public Bullet() {
		bullet = null;
	}

//	public Rectangle getHitbox(){
//		
//	}
	
	public void exitFrame(){
		Dogfight.removeBullet(this);
	}
	
	//Moves the bullet at a constant rate
	public void move(){
		transform.translate(speed, 0);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(bullet, transform, this);
	}

	
	
}
