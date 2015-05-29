import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

// class for ball
class Ball extends Thread {
	// ball size when balls create (but not when balls create because of collision)
	public static final double INIT_XSIZE = 20.0;
	public static final double INIT_YSIZE = 20.0;
	// minimum ball size
	public static final double MIN_XSIZE = 1.0;
	public static final double MIN_YSIZE = 1.0;
	
	private Canvas box;
	// ball size
	private double xSize = INIT_XSIZE;
	private double ySize = INIT_YSIZE;
	// ball position
	private double x;
	private double y;
	// ball moving angle
	private double angle = 2 * Math.PI * Math.random();
	// ball position variation
	private double dx = 2 * Math.cos(angle);
	private double dy = 2 * Math.sin(angle);
	// ball's age
	private int age = 0;

	// constructor of Ball
	public Ball(Canvas c, double x, double y, double xSize, double ySize) {
		box = c;
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	// get Balls List
	synchronized public ArrayList<Ball> getBalls() {
		return BallAnimation.balls;
	}
	
	// check collision
	synchronized public Ball checkCollision() {
		// get its index at balls.
		int idx = getBalls().indexOf(this);
		// check collision with all another balls.
		for(int i = 0; i < getBalls().size(); i++) {
			// not check with itself.
			if(i == idx) continue;
			// get object reference of a ball.
			Ball ball = getBalls().get(i);
			// get distance between two balls' center.
			double dist = Math.sqrt((x-ball.x)*(x-ball.x) + (y-ball.y)*(y-ball.y));
			// get real distance between two balls.
			double d = dist - xSize / 2 - ball.xSize / 2;
			// if detect collision, return ball.
			if(d <= 0) {
				return ball;
			}
		}
		// if not detect collision, return null.
		return null;
	}

	// draw a ball
	public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	// move a ball
	public void move() {
		Graphics g = box.getGraphics();

		// erase current a ball.
		g.setXORMode(box.getBackground());
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		
		// update position.
		x += dx;
		y += dy;
		
		/*
		 * check collision with walls.
		 */
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + xSize >= d.width) {
			x = d.width - xSize;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + ySize >= d.height) {
			y = d.height - ySize;
			dy = -dy;
		}
		
		// draw a ball.
		g.setPaintMode();
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	synchronized public void run() {
		Ball oBall, nBall;
		draw();
		try {
			// infinite loop.
			// exit when thread is interrupted.
			while(!!!!!!!!!!!!!!!false) {
				move();
				age++;
				// split a ball when age is properly high and detected collision.
				if(age >= 200 && (oBall=checkCollision()) != null) {
					// remove itself and bumped ball.
					getBalls().remove(this);
					getBalls().remove(oBall);
					
					// erase itself and bumped ball.
					Graphics g = box.getGraphics();
					g.setXORMode(box.getBackground());
					g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
					g.fillOval((int)oBall.x, (int)oBall.y, (int)oBall.xSize, (int)oBall.ySize);
					g.dispose();
					
					// create smaller two balls
					if(xSize/2 >= Ball.MIN_XSIZE) {
						nBall = new Ball(box, x, y, xSize/2, ySize/2);
						nBall.start();
						getBalls().add(nBall);
						nBall = new Ball(box, x, y, xSize/2, ySize/2);
						nBall.start();
						getBalls().add(nBall);
					}
					// create smaller two balls
					if(oBall.xSize/2 >= Ball.MIN_XSIZE) {
						nBall = new Ball(box, oBall.x, oBall.y, oBall.xSize/2, oBall.ySize/2);
						nBall.start();
						getBalls().add(nBall);
						nBall = new Ball(box, oBall.x, oBall.y, oBall.xSize/2, oBall.ySize/2);
						nBall.start();
						getBalls().add(nBall);
					}
					
					// interrupt bumped ball thread.
					oBall.interrupt();
					
					// print log to console.
					try {
						System.out.println("[log] ball num = " + getBalls().size() + ", balls.get(0).xSize = " + getBalls().get(0).xSize);
					} catch(Exception e) {
						// Do nothing
					}
					
					// program terminate when balls are disappeared.
					if(getBalls().size() <= 1) {
						System.exit(0);
					}
					
					return;
				}
				Thread.sleep(5);
			}
		} catch (InterruptedException e) {
			return;
		}
		
	}
}
