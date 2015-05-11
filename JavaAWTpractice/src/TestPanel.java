import java.awt.*;
import java.awt.event.*;

public class TestPanel extends Frame {
	public TestPanel(String str) {
		super(str);
	}

	public static void main(String[] args) {
		TestPanel fr = new TestPanel("Frame with Panel");
		Panel pan = new Panel();
		fr.setSize(200, 200);
		fr.setBackground(Color.blue);
		fr.setLayout(null); // Override default layout mgr
		pan.setSize(100, 100);
		pan.setBackground(Color.yellow);
		fr.add(pan);
		WindowDestroyer listener = new WindowDestroyer(); // window destroy
															// button
		fr.addWindowListener(listener);
		fr.setVisible(true);

	}
}

// p30

// 일반용 계산기를 만든다. 
// 근데 맨 위에 줄만 빼기.