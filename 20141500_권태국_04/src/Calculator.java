import java.awt.*;
import java.awt.event.*;

public class Calculator {
	final int NUM0 = 0;
	final int NUM1 = 1;
	final int NUM2 = 2;
	final int NUM3 = 3;
	final int NUM4 = 4;
	final int NUM5 = 5;
	final int NUM6 = 6;
	final int NUM7 = 7;
	final int NUM8 = 8;
	final int NUM9 = 9;
	final int DOT = 10;
	final int BACKSPACE = 11;
	final int CLEAREND = 12;
	final int CLEAR = 13;
	final int SIGN = 14;
	final int ROOT = 15;
	final int FRACTION = 16;
	final int ADD = 17;
	final int SUB = 18;
	final int MUL = 19;
	final int DIV = 20;
	final int MOD = 21;
	final int CALC = 22;
	final int NONE = -1;
	final int AFTERCALC = -2;
	
	final int MAX_POINT = 10;
	final int MIN_POINT = -14;
	
	private int oper = NONE;
	private double num[] = new double[]{0,0};
	private int numIdx = 0;
	private int point[] = {-1,-1};
	
	private Frame frame;
	private Panel noticePanel;
	//private TextField progressField;
	private TextField resultField;
	private Panel controlPanel;
	
	//버튼을 누르면 실행 되는 리스너 만들기
		class ButtonListener implements ActionListener{
			private int type;
			
			public ButtonListener(int type){
				this.type = type;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//String command=e.getActionCommand();
				processCommand(type);
			}
		}
		
		class MyKeyListener implements KeyListener {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				switch(key) {
				case '0': case '1': case '2': case '3': case '4':
				case '5': case '6': case '7': case '8': case '9':
					processCommand(key-'0'+NUM0);
					break;
				case '+': processCommand(ADD); break;
				case '-': processCommand(SUB); break;
				case '*': processCommand(MUL); break;
				case '/': processCommand(DIV); break;
				case '%': processCommand(MOD); break;
				case '=': processCommand(CALC); break;
				case KeyEvent.VK_BACK_SPACE: processCommand(BACKSPACE); break;
				default:
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		}
	
		private void processCommand(int type) {
			if(NUM0 <= type && type <= NUM9){
				if(numIdx == 0 && oper == AFTERCALC) {
					num[0] = 0;
					point[0] = -1;
					oper = NONE;
				} 
				if(point[numIdx] >= MAX_POINT || point[numIdx] <= MIN_POINT);
				else if(point[numIdx] >= 0) {
					point[numIdx]++;
					num[numIdx] = num[numIdx] + Math.pow(10, -point[numIdx]) * (type - NUM0);
				} else {
					point[numIdx]--;
					num[numIdx] = num[numIdx] * 10 + type - NUM0;
				}
			} else if(type == DOT){
				if(numIdx == 0 && oper == AFTERCALC) {
					num[0] = 0;
					point[0] = -1;
					oper = NONE;
				}
				else if(point[numIdx] < 0 ) point[numIdx] = 0;
			} else if(type == BACKSPACE){
				if(numIdx == 0 && oper == AFTERCALC);
				else if(point[numIdx] == 0) {
					num[numIdx] = Math.floor(num[numIdx]);
					point[numIdx] = -1;
				} else if(point[numIdx] > 0) {
					num[numIdx] = Math.floor(num[numIdx]*Math.pow(10, point[numIdx]-1)) / Math.pow(10, point[numIdx]-1);
					//num[numIdx] = Double.parseDouble(String.format("%."+(point[numIdx]-1)+"f", num[numIdx]));
					point[numIdx]--;
				} else if(point[numIdx] == -1) {
					num[numIdx] = Math.floor(num[numIdx]/10);
				} else if(point[numIdx] < -1){
					num[numIdx] = Math.floor(num[numIdx]/10);
					point[numIdx]++;
				}
			} else if(type == CLEAREND){
				num[numIdx] = 0;
				point[numIdx] = -1;
				if(numIdx == 0) oper = NONE;
			} else if(type == CLEAR){
				num[0] = num[1] = 0;
				oper = NONE;
				numIdx = 0;
				point[0] = point[1] = -1;
			} else if(type == SIGN){
				num[numIdx] = -num[numIdx];
				if(numIdx == 0) oper = NONE;
			} else if(type == ROOT){
				num[numIdx] = Math.sqrt(num[numIdx]);
				if(numIdx == 0) oper = NONE;
			} else if(type == FRACTION){
				num[numIdx] = 1.0/num[numIdx];
				point[numIdx] = MAX_POINT;
				if(numIdx == 0) oper = NONE;
			} else if(ADD <= type && type <= MOD){
				if(numIdx == 0 || oper == NONE) {
					oper = type;
					numIdx = 1;
					num[1] = 0;
					point[1] = -1;
					if(point[0] == 0) point[0] = 1;
				} else if(numIdx == 1 && point[1] == -1 && oper != NONE && oper != AFTERCALC) {
					oper = type;
				} else {
					try {
						num[0] = calculate(num[0], num[1], oper);
						oper = type;
						num[1] = 0;
						point[1] = -1;
						numIdx = 1;
						point[0] = MAX_POINT;
					} catch (Exception e1) {
						System.out.println(e1);
					}
				}
			} else if(type == CALC){
				if(numIdx == 0) {
					if(point[0] == 0) point[0]++;
				} else {
					try {
						num[0] = calculate(num[0], num[1], oper);
						oper = AFTERCALC;
						numIdx = 0;
						point[0] = MAX_POINT;
					} catch (Exception e1) {
						System.out.println(e1);
					}
				}
			} else {
				System.out.println("huh?!");
			}
			
			String txt = "";
			String operString[] = {"+","-","*","/","%"};
			
			if(numIdx == 0) {
				if(point[0] < 0) txt += (long)num[0];
				else if(point[0] == 0) txt += (long)num[0] + ".";
				else txt += String.format("%."+point[0]+"f",num[0]);
			} else if(numIdx == 1) {
				if(point[0] < 0) txt += (long)num[0];
				else if(point[0] == 0) txt += (long)num[0] + ".";
				else txt += String.format("%."+point[0]+"f",num[0]);
				if(point[1] < 0) txt += " " + operString[oper-ADD] + " " + (long)num[1];
				else if(point[1] == 0)  txt += " " + operString[oper-ADD] + " " + (long)num[1] + ".";
				else txt += " " + operString[oper-ADD] + " " + String.format("%."+point[1]+"f",num[1]);
			} else {
				txt = "fuck?!";
			}
			resultField.setText(txt);
		}
		
	public double calculate(double num1, double num2, int oper) throws Exception {
		switch(oper) {
		case ADD:
			return num1 + num2;
		case SUB:
			return num1 - num2;
		case MUL:
			return num1 * num2;
		case DIV:
			return num1 / num2;
		case MOD:
			return num1 % num2;
		default:
			throw new Exception("Invalid code flow!");
		}
	}
		
	public Calculator() {
		prepareUI();
	}
	
	private void prepareUI() {
		frame = new Frame("계산기");
		frame.setLayout(new BorderLayout());
		frame.setSize(300,400);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		//frame.addKeyListener(new MyKeyListener());
		
		resultField = new TextField("0", 35);
		noticePanel = makeNoticePanel(300,100);
		noticePanel.add(resultField);
		
		controlPanel = makeControlPanel(300,300);
		
		frame.add(noticePanel, BorderLayout.NORTH);
		frame.add(controlPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private Panel makeNoticePanel(int width, int height) {
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setSize(width, height);
		panel.setLayout(new GridLayout(1,1));  
		
		return panel;
	}
	
	private Panel makeControlPanel(int width, int height) {
		Panel panel = new Panel();
		panel.setBackground(Color.BLACK);
		panel.setSize(width, height);
		panel.setLayout(new GridBagLayout());  
		GridBagConstraints gbc = new GridBagConstraints();
		
		Button btn;
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(3,3,3,3);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		btn = new Button("BS");
		btn.addActionListener(new ButtonListener(BACKSPACE));
		panel.add(btn,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		btn = new Button("CE");
		btn.addActionListener(new ButtonListener(CLEAREND));
		panel.add(btn,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		btn = new Button("C");
		btn.addActionListener(new ButtonListener(CLEAR));
		panel.add(btn,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		btn = new Button("±");
		btn.addActionListener(new ButtonListener(SIGN));
		panel.add(btn,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		btn = new Button("√");
		btn.addActionListener(new ButtonListener(ROOT));
		panel.add(btn,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		btn = new Button("7");
		btn.addActionListener(new ButtonListener(NUM7));
		panel.add(btn,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		btn = new Button("8");
		btn.addActionListener(new ButtonListener(NUM8));
		panel.add(btn,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		btn = new Button("9");
		btn.addActionListener(new ButtonListener(NUM9));
		panel.add(btn,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		btn = new Button("/");
		btn.addActionListener(new ButtonListener(DIV));
		panel.add(btn,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		btn = new Button("%");
		btn.addActionListener(new ButtonListener(MOD));
		panel.add(btn,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		btn = new Button("4");
		btn.addActionListener(new ButtonListener(NUM4));
		panel.add(btn,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		btn = new Button("5");
		btn.addActionListener(new ButtonListener(NUM5));
		panel.add(btn,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		btn = new Button("6");
		btn.addActionListener(new ButtonListener(NUM6));
		panel.add(btn,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		btn = new Button("*");
		btn.addActionListener(new ButtonListener(MUL));
		panel.add(btn,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		btn = new Button("1/x");
		btn.addActionListener(new ButtonListener(FRACTION));
		panel.add(btn,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		btn = new Button("1");
		btn.addActionListener(new ButtonListener(NUM1));
		panel.add(btn,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		btn = new Button("2");
		btn.addActionListener(new ButtonListener(NUM2));
		panel.add(btn,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		btn = new Button("3");
		btn.addActionListener(new ButtonListener(NUM3));
		panel.add(btn,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		btn = new Button("-");
		btn.addActionListener(new ButtonListener(SUB));
		panel.add(btn,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		btn = new Button("=");
		btn.addActionListener(new ButtonListener(CALC));
		panel.add(btn,gbc);
		gbc.gridheight = 1;
		
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		btn = new Button("0");
		btn.addActionListener(new ButtonListener(NUM0));
		panel.add(btn,gbc);
		gbc.gridwidth = 1;
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		btn = new Button(".");
		btn.addActionListener(new ButtonListener(DOT));
		panel.add(btn,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		btn = new Button("+");
		btn.addActionListener(new ButtonListener(ADD));
		panel.add(btn,gbc);
		
		return panel;
	}
	
	static public void main(String[] args) {
		Calculator calc = new Calculator();
	}
}
