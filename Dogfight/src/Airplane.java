import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class Airplane{

	private Image plane;
	private boolean isP1 = true;
	private int speed = 6;
	private int x, y;
	private int angle;
	private int health;
	private AffineTransform transform;
	//private int bombs = 3;


	public Airplane(Image plane, boolean isP1){
		this.plane = plane.getScaledInstance(80, 25, 100);
		transform = new AffineTransform();
		this.isP1 = isP1;
		if(isP1 == true){
			transform.translate(80, 300);
			transform.scale(-1, 1);
			x = 300;
			y = 300;
			angle = 0;
		}
		else{
			transform.translate(920, 300);
			x = 925;
			y = 300;
			angle = 180;
		}
	}

	//Changes the speed without going over or under by too much
	public void changeSpeed(int change){
		if(speed + change <= 8 && speed + change > 2){
			speed += change;
		}
	}

	//Moves the plane based on speed
	public void move(){
		transform.translate(-(2 * speed), 0);
		Dogfight.panel.repaint();
	}

	public void rotate(int rotation){
		angle += rotation;
		transform.rotate(Math.toRadians(rotation));

		Dogfight.panel.repaint();
	}

	//Creates a new bullet
	public void shoot(){
		Bullet bullet = new Bullet(x + 10, y + 20, transform);
		Dogfight.addBullet(bullet);
	}

	public void setAngle(int angle){
		this.angle = angle;
		transform.rotate(Math.toRadians(this.angle - angle));

	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public Image getImage(){
		return plane;
	}


	public void setImage(Image image){
		plane = image;
	}
	
	public void draw(Graphics g, ImageObserver i){
		Graphics2D g2d = (Graphics2D)g;


		if(isP1 == true){
			g2d.drawImage(plane, transform, i);
		}
		else{
			g2d.drawImage(plane, transform, i);
		}
	}

}
