import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class Airplane{

	private Image plane;
	private boolean isP1 = true;
	private int speed = 6;
	private int[] xs, ys;
	private double[] src;
	double[] dst = new double[8];
	private int[] xTrans, yTrans;
	private int angle;
	private Polygon hitbox;
	private int health;
	private AffineTransform transform;
	private AffineTransform t;

	//private int bombs = 3;


	public Airplane(Image plane, boolean isP1){
		this.plane = plane.getScaledInstance(80, 25, 100);
		transform = new AffineTransform();
		this.isP1 = isP1;
		if(isP1 == true){
			transform.translate(80, 300);
			transform.scale(-1, 1);
			xs = new int[]{0, 80, 80, 0};
			ys = new int[] {300, 300, 325, 325};
			angle = 0;
		}
		else{
			transform.translate(920, 300);
			xs = new int[]{920, 1000, 1000, 920};
			ys = new int[]{300, 300, 325, 325};
			angle = 180;
		}
		t = new AffineTransform(transform);
//		t = AffineTransform.getRotateInstance(0.1, 5, 5);
		hitbox = new Polygon(xs, ys, 4);
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
		src = new double[]{
		        xs[0], ys[0],
		        xs[1], ys[1],
		        xs[2], ys[2],
		        xs[3], ys[3]};
		t = new AffineTransform(transform);
		t.transform(src, 0, dst, 0, 4);
		xTrans = new int[]{(int)dst[0], (int)dst[2],(int)dst[4],(int)dst[6]};
		yTrans = new int[]{(int)dst[1], (int)dst[3],(int)dst[5],(int)dst[7]};
		hitbox = new Polygon(xTrans, yTrans, 4);		
		Dogfight.panel.repaint();
	}

	public void rotate(int rotation){
		transform.rotate(Math.toRadians(rotation));
		src = new double[]{
		        xs[0], ys[0],
		        xs[1], ys[1],
		        xs[2], ys[2],
		        xs[3], ys[3]};
		t = new AffineTransform(transform);
		t.transform(src, 0, dst, 0, 4);
		xTrans = new int[]{(int)dst[0], (int)dst[2],(int)dst[4],(int)dst[6]};
		yTrans = new int[]{(int)dst[1], (int)dst[3],(int)dst[5],(int)dst[7]};
		hitbox = new Polygon(xTrans, yTrans, 4);
		Dogfight.panel.repaint();
	}

	//Creates a new bullet
	public void shoot(){
		Bullet bullet = new Bullet(new AffineTransform(transform), angle);
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
		g2d.setColor(Color.RED);
		g2d.drawPolygon(hitbox);
	}

}
