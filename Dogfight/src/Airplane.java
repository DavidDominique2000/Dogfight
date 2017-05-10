import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Airplane{

	private Image plane;
	private boolean isP1 = true;
	private int speed = 6;
	private int x, y;
	private int angle, prevAngle;
	private int health;
	//private int bombs = 3;


	public Airplane(Image plane, boolean isP1){
		this.plane = plane;
		plane = plane.getScaledInstance(100, 50, 100);
		this.isP1 = isP1;
		if(isP1 == true){
			x = 300;
			y = 300;
			angle = 0;
			prevAngle = angle;
		}
		else{
			x = 925;
			y = 300;
			angle = 180;
			prevAngle = angle;
		}
	}

	public void changeSpeed(int change){
		if(speed + change <= 8 && speed + change > 2){
			speed += change;
		}
	}

	public void move(){
		while(angle > 360){
			angle -= 360;
		}
		while(angle < -360){
			angle += 360;
		}
		if(angle >= 0 && angle < 90){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 90 && angle < 180){
			y -= Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 180 && angle < 270){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 270 && angle < 360){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x -= Math.cos(Math.toRadians(angle))  * speed;
		}
		Dogfight.panel.repaint();
	}

	public void rotate(int rotation){
		prevAngle = angle;
		angle += rotation;
		Dogfight.panel.repaint();
	}

	public void shoot(){
		Bullet bullet = new Bullet(x + 10, y + 20, angle);
		Dogfight.bullets.add(bullet);
	}

	public void setAngle(int angle){
		prevAngle = this.angle;
		this.angle = angle;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public Image getImage(){
		return plane;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public boolean getIsP1(){
		return isP1;
	}

	public void setImage(Image image){
		plane = image;
	}
	
	public void draw(Graphics g, ImageObserver i){
		Graphics2D g2d = (Graphics2D)g;

		if(angle != prevAngle){
			System.out.println(angle);
			System.out.println(prevAngle);
			g2d.rotate(Math.toRadians(angle - prevAngle));
		}
		if(isP1 == true){
			g2d.drawImage(plane, x, y, -75, 20, i);
		}
		else{
			g2d.drawImage(plane, x, y, 75, 20, i);
		}
	}

}
