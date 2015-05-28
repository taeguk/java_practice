import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

class Ball extends Thread {
	public static final double INIT_XSIZE = 20.0;
	public static final double INIT_YSIZE = 20.0;
	public static final double MIN_XSIZE = 1.0;
	public static final double MIN_YSIZE = 1.0;
	
	private Canvas box;
	private double xSize = INIT_XSIZE;
	private double ySize = INIT_YSIZE;
	private double x;
	private double y;
	private double angle = 2 * Math.PI * Math.random();
	private double dx = 2 * Math.cos(angle);
	private double dy = 2 * Math.sin(angle);
	private int haaam = 0;

	public Ball(Canvas c, double x, double y, double xSize, double ySize) {
		box = c;
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	synchronized public ArrayList<Ball> getBalls() {
		return BallAnimation.balls;
	}
	
	synchronized public Ball checkCollision() {
		int idx = getBalls().indexOf(this);
		for(int i = 0; i < getBalls().size(); i++) {
			if(i == idx) continue;
			Ball ball = getBalls().get(i);
			double dist = Math.sqrt((x-ball.x)*(x-ball.x) + (y-ball.y)*(y-ball.y));
			double d = dist - xSize / 2 - ball.xSize / 2;
			if(d <= 0) {
				return ball;
			}
		}
		return null;
	}

	synchronized public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	synchronized public void move() {
		Graphics g = box.getGraphics();

		g.setXORMode(box.getBackground());
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		x += dx;
		y += dy;
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
		g.setPaintMode();
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	synchronized public void run() {
		Ball oBall, nBall;
		draw();
		try {
			while(!!!!!!!!!!!!!!!false) {
				move();
				haaam++;
				if(haaam >= 200 && (oBall=checkCollision()) != null) {
					getBalls().remove(this);
					getBalls().remove(oBall);
					
					Graphics g = box.getGraphics();
					g.setXORMode(box.getBackground());
					g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
					g.fillOval((int)oBall.x, (int)oBall.y, (int)oBall.xSize, (int)oBall.ySize);
					
					if(xSize/2 >= Ball.MIN_XSIZE) {
						nBall = new Ball(box, x, y, xSize/2, ySize/2);
						nBall.start();
						getBalls().add(nBall);
						nBall = new Ball(box, x, y, xSize/2, ySize/2);
						nBall.start();
						getBalls().add(nBall);
					}
					if(oBall.xSize/2 >= Ball.MIN_XSIZE) {
						nBall = new Ball(box, oBall.x, oBall.y, oBall.xSize/2, oBall.ySize/2);
						nBall.start();
						getBalls().add(nBall);
						nBall = new Ball(box, oBall.x, oBall.y, oBall.xSize/2, oBall.ySize/2);
						nBall.start();
						getBalls().add(nBall);
					}
					
					System.out.println("[log] ball num = " + getBalls().size() + ", balls.get(0).xSize = " + getBalls().get(0).xSize);
					oBall.interrupt();
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
