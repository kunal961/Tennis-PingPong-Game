package pingPong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle {

	
	final double freeMove=0.5;
	double y, yVel;
	int player, x;
	boolean upAcc, downAcc; 
	
	
	public HumanPaddle(int player) {
		upAcc=false; downAcc=false;
		y=210; yVel=0;
		if(player==1) {
			x=20;
		}
		else if(player==2) {
			x=660;
		}
	}
	
	public void setUpAcc(boolean input) {
		upAcc=input;
	}
	public void setDownAcc(boolean input) {
		downAcc=input;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,(int)y, 20,150);
		
	}

	
	public void move() {
		if(upAcc) {
			yVel -=1;
		}
		else if(downAcc) {
			yVel +=1;
		}
		else yVel *= freeMove;
		
		y=y+yVel;
		
		if(y<0)y=0;
		if(y>350)y=350;
		
	}

	
	public int getY() {
		return (int)y;
	}
}
