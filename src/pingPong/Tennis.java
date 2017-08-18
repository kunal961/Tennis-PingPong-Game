package pingPong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int ht=500, wd=700;
	int score, strikes;
	Thread thread;
	HumanPaddle hp,hp1, hp2;
	Ball b;
	boolean startGame;
	
	public void init() {
		this.resize(wd, ht);
		startGame=false;
		score=0;
		strikes=0;
		this.addKeyListener(this);
		thread = new Thread(this);
		thread.start();
		hp1=new HumanPaddle(1);
		hp2=new HumanPaddle(2);
		b=new Ball();
		hp=hp1;
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, wd, ht);
		hp1.draw(g);
		hp2.draw(g);
		b.draw(g);
		
		if(!startGame) {
			g.setColor(Color.GREEN);
			g.drawString("TENNIS", 330, 50);
			g.drawString("Press ENTER to start the game", 270, 100);
		}
		else if(b.getX()<-10 || b.getX()>710) {
			startGame=false;
			g.setColor(Color.GREEN);
			g.drawString("TENNIS", 330, 50);
			g.drawString("GAME OVER", 320, 100);
			g.drawString("YOUR SCORE :"+score, 310, 150);
		}
		else {
			g.drawString("LEVEL "+b.point,320, 100);
			g.drawString("CURRENT SCORE : "+score, 290, 200);
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	
	public void run() {
		
		
		while(true) {
			
			if(startGame) {
				try {
					hp1.move();
					hp2.move();
					b.move();
					checkCollision(hp1,hp2,b);
					}catch(NullPointerException e) {
						
					}
				
				repaint();
			}
			
			
			
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void checkCollision(HumanPaddle p1, HumanPaddle p2, Ball b) {
		
		if(b.getX()==50 && p1.getY()<=b.getY() && p1.getY()+150>=b.getY()) {
			b.xVel=-b.xVel;
			hp=hp2;
			score +=b.point;
			strikes++;
			if(strikes%2==0)b.updateValues();
		}
		
		else if(b.getX()==650 && p2.getY()<=b.getY() && p2.getY()+150>=b.getY()) {
			b.xVel=-b.xVel;
			hp=hp1;
			score +=b.point;
			strikes++;
			if(strikes%2==0)
				b.updateValues();
		}
		
	}
		
	
	

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_UP) {
			hp.setUpAcc(true);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			hp.setDownAcc(true);
		}else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			startGame=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			hp.setUpAcc(false);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			hp.setDownAcc(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
