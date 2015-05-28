import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BallAnimation extends Frame implements ActionListener {
	private static final int BALL_NUM = 5;
	private Canvas canvas;
	private ArrayList<Ball> balls = new ArrayList<Ball>(BALL_NUM);

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

	public void actionPerformed(ActionEvent evt) {
		Graphics g = canvas.getGraphics();
		//ClearCanvas cc = new ClearCanvas(canvas);
		//cc.start();
		if (evt.getActionCommand() == "Start") {			
			Ball ball;
			for(Iterator<Ball> itr = balls.iterator(); itr.hasNext();) {
				ball = itr.next();
				ball.interrupt();
			}
			balls.clear();
			g.setColor(getBackground());
            g.clearRect(0, 0, canvas.getSize().width, canvas.getSize().height);
			for(int i = 0; i < BALL_NUM; i++) {
				double x = canvas.getSize().width / 2;
				double y = canvas.getSize().height / 2;
				balls.add(new Ball(canvas, x, y, Ball.INIT_XSIZE, Ball.INIT_YSIZE));
			}
			for(Iterator<Ball> itr = balls.iterator(); itr.hasNext();) {
				ball = itr.next();
				ball.start();
			}
		} else if (evt.getActionCommand() == "Close") {
			System.exit(0);
		}
		//cc.interrupt();
	}

	static public void main(String[] args) {
		Frame frame = new BallAnimation();
		frame.setSize(400, 300);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
