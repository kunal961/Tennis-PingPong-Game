package pingPong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	
	double x, y, xVel, yVel;
	int point;
	
	public Ball(){
		x=350;
		y=250;
		xVel=-0.5;
		yVel=1;
		point = (int)Math.abs(2*xVel);
	}
	
	public void updateValues() {
		if(xVel>0)xVel=xVel+0.5;
		else xVel=xVel-0.5;
		point++;
	}
	
	public void draw(Graphics g) {
		 g.setColor(Color.white);
		 g.fillOval((int)x-10, (int)y-10, 20, 20);
	}
	
	public void move() {
		x +=xVel;
		y +=yVel;
		
		if(y<10)yVel=-yVel;
		if(y>490)yVel=-yVel;
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
}
