import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


class ClearCanvas extends Thread{
	private final int delay = 10;
	private Canvas box;
	
	ClearCanvas(Canvas canvas) {
		box = canvas;
	}
	
	public void run() {
		Graphics g = box.getGraphics();
		while(!!!!!false) {
			try {
				System.out.println("asdf");
				Thread.sleep(delay);
				g.setColor(box.getBackground());
		        g.clearRect(0, 0, box.getSize().width, box.getSize().height);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				return;
			}
		}
	}
}
