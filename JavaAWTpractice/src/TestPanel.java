import java.awt.*;
import java.awt.event.*;

public class TestPanel extends Frame {
	public TestPanel(String str) {
		super(str);
	}

	public static void main(String[] args) {
		/*
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
		*/
		TestPanel f = new TestPanel("Simple FlowLayout Example");
	      /*
	       f.setLayout(new FlowLayout());
	      Button b1 = new Button("Press Me");
	      Button b2 = new Button("Don't Press Me");
	      f.add(b1);
	      f.add(b2);
	      f.pack();
	      WindowDestroyer listener = new WindowDestroyer();  // window destroy button
	       f.addWindowListener(listener);
	      f.setVisible(true);
	      *//*
		 f.setLayout(new BorderLayout());
	     Button bn = new Button("Bn");
	     Button bs = new Button("Bs");
	     Button be = new Button("Be");
	     Button bw = new Button("Bw");
	     Button bc = new Button("Bc");
	     f.add(bn, BorderLayout.NORTH);
	     f.add(bs, BorderLayout.SOUTH);
	     f.add(be, BorderLayout.EAST);
	     f.add(bw, BorderLayout.WEST);
	     f.add(bc, BorderLayout.CENTER);
	     f.setSize(200,200);
	     //f.pack();
	     WindowDestroyer listener = new WindowDestroyer();  
	        f.addWindowListener(listener);
	     f.setVisible(true);
	     */
		f.setLayout(new GridLayout(3,2));
	    Button b1 = new Button("B1");
	    Button b2 = new Button("B2");
	    Button b3 = new Button("B3");
	    Button b4 = new Button("B4");
	    Button b5 = new Button("B5");
	    Button b6 = new Button("B6");
	    f.add(b1);  f.add(b2); f.add(b3); f.add(b4); f.add(b5); f.add(b6); f.pack();

	    WindowDestroyer listener = new WindowDestroyer();  
	      f.addWindowListener(listener);
	    f.setVisible(true);

	}
}

// p30

// 일반용 계산기를 만든다. 
// 근데 맨 위에 줄만 빼기.