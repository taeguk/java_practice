import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * This is a class for ball animation
 */
public class BallAnimation extends Frame implements ActionListener {
	// the number of balls when animation starts
	private static final int BALL_NUM = 5;
	// width and height of frame
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	private Canvas canvas;
	// ArrayList for storing balls
	public static ArrayList<Ball> balls = new ArrayList<Ball>(BALL_NUM);

	// constructor of BallAnimation
	// Create UI
	public BallAnimation() {
		canvas = new Canvas();
		add("Center", canvas);
		Panel p = new Panel();
		Button s = new Button("Start");
		Button c = new Button("Close");
		p.add(s);
		p.add(c);
		s.addActionListener(this);
		c.addActionListener(this);
		add("South", p);
	}

	// event handler for start and close
	public void actionPerformed(ActionEvent evt) {
		// for clearing canvas periodically
		// now this function is not opened
		ClearCanvas cc = new ClearCanvas(canvas);
		cc.start();
		cc.setPriority(Thread.MAX_PRIORITY);
		
		// when command is a start
		if (evt.getActionCommand() == "Start") {
			Ball ball;
			// interrupt all ball threads.
			for(Iterator<Ball> itr = balls.iterator(); itr.hasNext();) {
				ball = itr.next();
				ball.interrupt();
			}
			
			// delete all balls.
			balls.clear();
			
			// clear canvas
			Graphics g = canvas.getGraphics();
			g.setColor(getBackground());
            g.clearRect(0, 0, canvas.getSize().width, canvas.getSize().height);
            g.dispose();
            
            // create balls at center of canvas
			for(int i = 0; i < BALL_NUM; i++) {
				double x = canvas.getSize().width / 2;
				double y = canvas.getSize().height / 2;
				balls.add(new Ball(canvas, x, y, Ball.INIT_XSIZE, Ball.INIT_YSIZE));
			}
			// start ball threads
			for(Iterator<Ball> itr = balls.iterator(); itr.hasNext();) {
				ball = itr.next();
				ball.start();
			}
		} else if (evt.getActionCommand() == "Close") {
			// program exits when command is a close.
			System.exit(0);
		}
		//cc.interrupt();
	}

	// main function
	static public void main(String[] args) {
		// create ballAnimation
		Frame frame = new BallAnimation();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
