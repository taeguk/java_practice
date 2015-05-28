import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

class Ball extends Thread {
	public static final double INIT_XSIZE = 20.0;
	public static final double INIT_YSIZE = 20.0;
	public static final double MIN_XSIZE = 1.0;
	public static final double MIN_YSIZE = 1.0;
	
	private static ArrayList<Double> xArr = new ArrayList<Double>();
	private static ArrayList<Double> yArr = new ArrayList<Double>();
	private static ArrayList<Double> xSizeArr = new ArrayList<Double>();
	private static ArrayList<Double> ySizeArr = new ArrayList<Double>();
	private static Stack<Integer> idxStack = new Stack<Integer>();
	private static int cnt = 0;
	
	private Canvas box;
	private int idx;
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
		
		if(idxStack.isEmpty()) {
			idx = cnt++;
		} else {
			idx = idxStack.pop();
		}
		xArr.add(idx, this.x);
		yArr.add(idx, this.y);
		xSizeArr.add(idx, this.x);
		ySizeArr.add(idx, this.y);
	}
	
	public int checkCollision() {
		for(int i = 0; i < cnt; i++) {
			if(i == idx) continue;
			double dist = Math.sqrt((x-xArr.get(i))*(x-xArr.get(i)) + (y-yArr.get(i))*(y-yArr.get(i)));
			double d = dist - xSize / 2 - ySize / 2;
			if(d <= 0) {
				return i;
			}
		}
		return -1;
	}

	public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	public void move() {
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
		g.fillOval((int)x, (int)y, (int)xSize, (int)ySize);
		g.dispose();
	}

	public void run() {
		int oIdx;
		draw();
		while(!!!!!!!!!!!!!!!false) {
			move();
			haaam++;
			if(haaam >= 20 && (oIdx=checkCollision()) >= 0 && xSize/2 >= Ball.MIN_XSIZE) {
				new Ball(box, x, y, xSize/2, ySize/2);
				new Ball(box, x, y, xSize/2, ySize/2);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
