import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Airplane{

	private Image plane;
	private boolean isP1 = true;
	private int speed = 6;
	private int x, y;
	private int angle, angleChange;
	private int health;
	//private int bombs = 3;


	public Airplane(Image plane, boolean isP1){
		this.plane = plane;
		plane = plane.getScaledInstance(100, 50, 100);
		this.isP1 = isP1;
		if(isP1 == true){
			x = 75;
			y = 300;
			angle = 0;
			angleChange = 0;
		}
		else{
			x = 925;//Change this to match size of window
			y = 300;
			angle = 180;
			angleChange = 180;
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
	}

	public void shoot(){
		Bullet bullet = new Bullet(x + 10, y + 20, angle);
		Dogfight.bullets.add(bullet);
	}

	public void setAngle(int angle){
		angleChange = this.angle - angle;
		this.angle = angle;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void rotate(int amount){
		angleChange = amount;
	}

	public Image getImage(){
		return plane;
	}

	public int getAngleChange(){
		return angleChange;
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

	public void setAngleChange(int change){
		angleChange = change;
	}

	public void draw(Graphics g, ImageObserver i){
		Graphics2D g2d = (Graphics2D)g;

		if(angleChange != 0){
			g2d.rotate(Math.toRadians(angleChange));
			if(isP1 == true){
				angleChange = 0;
			}
			else{
				angleChange = 180;
			}
		}
		if(isP1 == true){
			g2d.drawImage(plane, x, y, -75, 20, i);
		}
		else{
			g2d.drawImage(plane, x, y, 75, 20, i);
		}
	}

}
