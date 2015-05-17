import java.awt.*;
import java.awt.event.*;

class WindowDestroyer extends WindowAdapter
{
    public void windowClosing(WindowEvent e) 
    {
        System.exit(0);
    }
}

class MyFrame extends Frame
{
	public MyFrame(String str) 
	{
		super(str);
	}
	public void paint(Graphics g)
	{
		/*
		g.drawLine(10, 30, 400,30);        	// line
      	g.drawRect(10, 50,  80,200);      	// rectangle
      	g.fillRect(110,50,  80,200);        	// colored rectangle
		g.drawRoundRect(200,50, 80,200, 10,30); // round corner rectangle
      	g.fillRoundRect(300,50, 80,200, 30,10);  // colored rectangle
      	g.drawOval(10,30, 150,250);    // oval 
		g.drawArc(200,30, 150,250,  30, 270);  	  // arc
      	g.fillArc(400, 30, 150, 250,  30, 270);  // colored arc
      	
      	int[] x = { 30, 200,  250, 400, 450,  30}; // starting point
 	    int[] y = {140,  50,   70,  30, 140, 140}; //end point

	    g.drawPolygon(x, y, x.length-1);		// folded line
	    for (int i=0 ; i<x.length ; i++)
	    	y[i] += 150;
	 	g.drawPolygon(x, y, x.length);   // polygon
		for (int i=0 ; i<x.length ; i++)          
			y[i] += 150;          
	 	g.fillPolygon(x, y, x.length-1);  // colored polygon     
	 	
		setBackground(Color.yellow);
		g.setColor(Color.red);              // set color of g object to red
      	g.drawRect(10,30, 180,120);   // rectangle of red borders

		g.setColor(new Color(150,200,250));   // set color of g object to 
		g.fillRect(11,31, 179,119);	               // r=150, g=200, b=250
		*/
		setBackground(Color.yellow); //white background
		g.drawString("문자열 출력", 10,40);
		Font fnt = new Font("바탕체", Font.BOLD, 30);
      	g.setFont(fnt);
      	g.drawString("자바를 잡아라 !", 10,70);      
		g.setFont(new Font("바탕체", Font.BOLD + Font.ITALIC, 50));
      	String str = "JAVA Java 자바";   
      	g.drawString(str, 10,140);
	}
}

class ImageFrame extends Frame {  
	
 	Image img = Toolkit.getDefaultToolkit().getImage(
            "/Users/Public/Pictures/Sample Pictures/국화.jpg");
   	public ImageFrame(String str)
      {  	super(str);
		img = Toolkit.getDefaultToolkit().getImage(
               "C:\\Users\\Public\\Pictures\\Sample Pictures\\k.jpg"); }
   	/*
	public void paint(Graphics g) {
      	g.drawImage(img , 100, 100, 400, 150, this);  	//enlargement 
      	g.drawRect(100,100,400,150); } 	                //border line
      	*/
   	/*
	public void paint(Graphics g)
    { 	
		setBackground(Color.yellow);                
		Dimension d  = getSize();
    	Insets    in = getInsets();
    // width, height of client domain in frame
    
    int cpok = d.width - in.left - in.right;
    int cnpi = d.height - in.top - in.bottom;
          Image img = Toolkit.getDefaultToolkit().getImage(
     "C:\\Users\\Public\\Pictures\\Sample Pictures\\k.jpg");
          g.drawImage(img, 30,30, cpok,cnpi, this);
          g.drawString("d.width  : " + d.width, 10,40);
          g.drawString("d.height : " + d.height,10,60);
          g.drawString("in.left  : " + in.left,   10,80); 
          g.drawString("in.right : " + in.right,10,100);
          g.drawString("in.top   : " + in.top,  10,120);
          g.drawString("in.bottom: " + in.bottom, 10,140); 
          
          g.setColor(Color.red);
        	g.fillRect(10,10, 200,100); // red rectangle
  		g.setXORMode(Color.blue);
        	g.fillRect(100,50, 200,100);      

    }*/
   	public void paint(Graphics g)
    { 	Dimension d  = getSize();
    	Insets    in = getInsets();
		int cpok = d.width - in.left - in.right;      // width, height
		int cnpi = d.height - in.top - in.bottom;
	      	int x,y,l, R,G,B, i; 			// draw circles with random color of 500
		for (i=0 ; i<500 ; i++)
       	{	x = (int)(Math.random()*cpok);
       		y = (int)(Math.random()*cnpi);
      		l = (int)(Math.random()*30);
			R = (int)(Math.random()*255);
       		G = (int)(Math.random()*255);
       		B = (int)(Math.random()*255);
			//g.setColor( new Color(R, G, B) );
			g.setXORMode( new Color(R, G, B) );
       		g.fillOval(x,y, l,l);        
          }
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paint(g);
		
	}

}

public class Test extends Frame 
{
	public static void main(String[] args) 
	{
		ImageFrame frm = new ImageFrame("First AWT");
      	frm.addWindowListener(new WindowDestroyer());
		frm.setSize(700,400);
		frm.setVisible(true);
	}
}
