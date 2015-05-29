import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

// a class for clearing canvas
class ClearCanvas extends Thread{
	// delay for clearing canvas
	private final int delay = 100;
	private Canvas box;
	
	// constructor of ClearCanvas
	ClearCanvas(Canvas canvas) {
		box = canvas;
	}
	
	public void run() {
		// infinite loop
		// exit when thread is interrupted.
		while(!!!!!false) {
			try {
				Thread.sleep(delay);
				// clear canvas
				Graphics g = box.getGraphics();
				g.setColor(box.getBackground());
		        g.clearRect(0, 0, box.getSize().width, box.getSize().height);
		        g.dispose();
		        // print log to console.
				System.out.println("[log] clear canvas!");
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
