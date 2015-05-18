import java.awt.*;
import java.awt.event.*;

// Calcurator Class
public class Calculator {
	// constant for action and status
	// number 0-9
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
	// dot
	final int DOT = 10;
	// back space , clear end, clear
	final int BACKSPACE = 11;
	final int CLEAREND = 12;
	final int CLEAR = 13;
	// sign, root, fraction
	final int SIGN = 14;
	final int ROOT = 15;
	final int FRACTION = 16;
	// + - * / %
	final int ADD = 17;
	final int SUB = 18;
	final int MUL = 19;
	final int DIV = 20;
	final int MOD = 21;
	// calculate (=)
	final int CALC = 22;
	// no operation
	final int NONE = -1;
	// after calculate
	final int AFTERCALC = -2;

	// max expression range constant
	final int MAX_POINT = 10;
	final int MIN_POINT = -14;

	// operation
	private int oper = NONE;
	// operands
	private double num[] = new double[] { 0, 0 };
	// now processing operand number index
	private int numIdx = 0;
	// operands' point infomation
	private int point[] = { -1, -1 };

	// frame
	private Frame frame;
	// notice panel : panel for noticing result
	private Panel noticePanel;
	// field for printing result
	private TextField resultField;
	// panel for controlling panel
	private Panel controlPanel;

	// Button Listener
	class ButtonListener implements ActionListener {
		// button type
		private int type;

		// constructor
		public ButtonListener(int type) {
			this.type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// process command by type
			processCommand(type);
		}
	}

	// key listener! but not using.
	class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// get key char
			char key = e.getKeyChar();
			switch (key) {
			// when key is number
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				processCommand(key - '0' + NUM0);
				break;
			// when key is + - * / %
			case '+':
				processCommand(ADD);
				break;
			case '-':
				processCommand(SUB);
				break;
			case '*':
				processCommand(MUL);
				break;
			case '/':
				processCommand(DIV);
				break;
			case '%':
				processCommand(MOD);
				break;
			// when key is =
			case '=':
				processCommand(CALC);
				break;
			// when key is back space
			case KeyEvent.VK_BACK_SPACE:
				processCommand(BACKSPACE);
				break;
			default:
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	// process command by type
	private void processCommand(int type) {
		// when command type is NUMBER(0-9)
		if (NUM0 <= type && type <= NUM9) {
			// when after calculate finished
			if (numIdx == 0 && oper == AFTERCALC) {
				num[0] = 0;
				point[0] = -1;
				oper = NONE;
			}
			// expression range check
			if (point[numIdx] >= MAX_POINT || point[numIdx] <= MIN_POINT)
				;
			// process if real number
			else if (point[numIdx] >= 0) {
				point[numIdx]++;
				num[numIdx] = num[numIdx] + Math.pow(10, -point[numIdx])
						* (type - NUM0);
			} else {	// process if integer
				point[numIdx]--;
				num[numIdx] = num[numIdx] * 10 + type - NUM0;
			}
		} else if (type == DOT) {		// when command type is DOT(.)
			// when after calculate finished
			if (numIdx == 0 && oper == AFTERCALC) {
				num[0] = 0;
				point[0] = -1;
				oper = NONE;
			} else if (point[numIdx] < 0)	// process if integer
				point[numIdx] = 0;
		} else if (type == BACKSPACE) {		// when command type is
			// when after calculate finished
			if (numIdx == 0 && oper == AFTERCALC)
				;
			// process if dot
			else if (point[numIdx] == 0) {
				num[numIdx] = Math.floor(num[numIdx]);
				point[numIdx] = -1;
			} else if (point[numIdx] > 0) {		// process if real number
				num[numIdx] = Math.floor(num[numIdx]
						* Math.pow(10, point[numIdx] - 1))
						/ Math.pow(10, point[numIdx] - 1);
				point[numIdx]--;
			} else if (point[numIdx] == -1) {	// process if zero
				num[numIdx] = Math.floor(num[numIdx] / 10);
			} else if (point[numIdx] < -1) {	// process if integer
				num[numIdx] = Math.floor(num[numIdx] / 10);
				point[numIdx]++;
			}
		} else if (type == CLEAREND) {		// when command type is clear end
			num[numIdx] = 0;
			point[numIdx] = -1;
			if (numIdx == 0)
				oper = NONE;
		} else if (type == CLEAR) {			// when command type is clear
			num[0] = num[1] = 0;
			oper = NONE;
			numIdx = 0;
			point[0] = point[1] = -1;
		} else if (type == SIGN) {			// when command type is sign
			num[numIdx] = -num[numIdx];
			if (numIdx == 0)
				oper = NONE;
		} else if (type == ROOT) {			// when command type is root
			num[numIdx] = Math.sqrt(num[numIdx]);
			if (numIdx == 0)
				oper = NONE;
		} else if (type == FRACTION) {		// when command type is fraction
			num[numIdx] = 1.0 / num[numIdx];
			point[numIdx] = MAX_POINT;
			if (numIdx == 0)
				oper = NONE;
		} else if (ADD <= type && type <= MOD) {	// when command type is in +-*/%
			// if operand # is 1 or operation is none
			if (numIdx == 0 || oper == NONE) {
				oper = type;
				numIdx = 1;
				num[1] = 0;
				point[1] = -1;
				if (point[0] == 0)
					point[0] = 1;
			} else if (numIdx == 1 && point[1] == -1 && oper != NONE
					&& oper != AFTERCALC) {		// when operand 2 is zero
				oper = type;
			} else {		// normal case
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
		} else if (type == CALC) {		// when command type is CALC
			// operand # is 1
			if (numIdx == 0) {
				if (point[0] == 0)
					point[0]++;
			} else {	// calculate
				try {
					num[0] = calculate(num[0], num[1], oper);
					oper = AFTERCALC;
					numIdx = 0;
					point[0] = MAX_POINT;
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		} else {	// never going this. If go to this, it is bug.
			System.out.println("huh?!");
		}

		/* code for making text field */
		String txt = "";
		String operString[] = { "+", "-", "*", "/", "%" };

		// when operand # is 1
		if (numIdx == 0) {
			if (point[0] < 0)
				txt += (long) num[0];
			else if (point[0] == 0)
				txt += (long) num[0] + ".";
			else
				txt += String.format("%." + point[0] + "f", num[0]);
		} else if (numIdx == 1) {		// when operand # is 2
			// apply operand 1 to text
			if (point[0] < 0)
				txt += (long) num[0];
			else if (point[0] == 0)
				txt += (long) num[0] + ".";
			else
				txt += String.format("%." + point[0] + "f", num[0]);
			// apply operation and operand 2 to text
			if (point[1] < 0)
				txt += " " + operString[oper - ADD] + " " + (long) num[1];
			else if (point[1] == 0)
				txt += " " + operString[oper - ADD] + " " + (long) num[1] + ".";
			else
				txt += " " + operString[oper - ADD] + " "
						+ String.format("%." + point[1] + "f", num[1]);
		} else {		// never going this. If go to this, it is bug.
			txt = "what?!";
		}
		// set text
		resultField.setText(txt);
	}

	// calculate
	public double calculate(double num1, double num2, int oper)
			throws Exception {
		switch (oper) {
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

	// contructor
	public Calculator() {
		prepareUI();
	}

	// making UI using AWT
	private void prepareUI() {
		// make Frame
		frame = new Frame("계산기");
		frame.setLayout(new BorderLayout());	// using border layout
		frame.setSize(300, 400);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// frame.addKeyListener(new MyKeyListener());

		// make notice panel and result field
		resultField = new TextField("0", 35);
		noticePanel = makeNoticePanel(300, 100);
		noticePanel.add(resultField);

		// make control panel
		controlPanel = makeControlPanel(300, 300);

		// add panels to frame
		frame.add(noticePanel, BorderLayout.NORTH);
		frame.add(controlPanel, BorderLayout.CENTER);

		// set frame visible = true
		frame.setVisible(true);
	}

	// make notice panel
	private Panel makeNoticePanel(int width, int height) {
		// make panel
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);	// background color is white
		panel.setSize(width, height);
		panel.setLayout(new GridLayout(1, 1));	// using grid layout

		return panel;
	}

	// make control panel
	private Panel makeControlPanel(int width, int height) {
		// make panel
		Panel panel = new Panel();
		panel.setBackground(Color.BLACK);	// background color is black
		panel.setSize(width, height);
		panel.setLayout(new GridBagLayout());	// using grid bag layout
		GridBagConstraints gbc = new GridBagConstraints();

		Button btn;

		// setting for grid cells
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(3, 3, 3, 3);	// cell spacing

		// make backspace button and add event handler and apply to panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		btn = new Button("BS");
		btn.addActionListener(new ButtonListener(BACKSPACE));
		panel.add(btn, gbc);

		// make clear end button and add event handler and apply to panel
		gbc.gridx = 1;
		gbc.gridy = 0;
		btn = new Button("CE");
		btn.addActionListener(new ButtonListener(CLEAREND));
		panel.add(btn, gbc);

		// make clear button and add event handler and apply to panel
		gbc.gridx = 2;
		gbc.gridy = 0;
		btn = new Button("C");
		btn.addActionListener(new ButtonListener(CLEAR));
		panel.add(btn, gbc);

		// make sign button and add event handler and apply to panel
		gbc.gridx = 3;
		gbc.gridy = 0;
		btn = new Button("±");
		btn.addActionListener(new ButtonListener(SIGN));
		panel.add(btn, gbc);

		// make root button and add event handler and apply to panel
		gbc.gridx = 4;
		gbc.gridy = 0;
		btn = new Button("√");
		btn.addActionListener(new ButtonListener(ROOT));
		panel.add(btn, gbc);

		// make number 7 button and add event handler and apply to panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		btn = new Button("7");
		btn.addActionListener(new ButtonListener(NUM7));
		panel.add(btn, gbc);

		// make number 8 button and add event handler and apply to panel
		gbc.gridx = 1;
		gbc.gridy = 1;
		btn = new Button("8");
		btn.addActionListener(new ButtonListener(NUM8));
		panel.add(btn, gbc);

		// make number 9 button and add event handler and apply to panel
		gbc.gridx = 2;
		gbc.gridy = 1;
		btn = new Button("9");
		btn.addActionListener(new ButtonListener(NUM9));
		panel.add(btn, gbc);

		// make div button and add event handler and apply to panel
		gbc.gridx = 3;
		gbc.gridy = 1;
		btn = new Button("/");
		btn.addActionListener(new ButtonListener(DIV));
		panel.add(btn, gbc);

		// make mod button and add event handler and apply to panel
		gbc.gridx = 4;
		gbc.gridy = 1;
		btn = new Button("%");
		btn.addActionListener(new ButtonListener(MOD));
		panel.add(btn, gbc);

		// make number 4 button and add event handler and apply to panel
		gbc.gridx = 0;
		gbc.gridy = 2;
		btn = new Button("4");
		btn.addActionListener(new ButtonListener(NUM4));
		panel.add(btn, gbc);

		// make number 5 button and add event handler and apply to panel
		gbc.gridx = 1;
		gbc.gridy = 2;
		btn = new Button("5");
		btn.addActionListener(new ButtonListener(NUM5));
		panel.add(btn, gbc);
		
		// make number 6 button and add event handler and apply to panel
		gbc.gridx = 2;
		gbc.gridy = 2;
		btn = new Button("6");
		btn.addActionListener(new ButtonListener(NUM6));
		panel.add(btn, gbc);

		// make mul button and add event handler and apply to panel
		gbc.gridx = 3;
		gbc.gridy = 2;
		btn = new Button("*");
		btn.addActionListener(new ButtonListener(MUL));
		panel.add(btn, gbc);

		// make fraction button and add event handler and apply to panel
		gbc.gridx = 4;
		gbc.gridy = 2;
		btn = new Button("1/x");
		btn.addActionListener(new ButtonListener(FRACTION));
		panel.add(btn, gbc);

		// make number 1 button and add event handler and apply to panel
		gbc.gridx = 0;
		gbc.gridy = 3;
		btn = new Button("1");
		btn.addActionListener(new ButtonListener(NUM1));
		panel.add(btn, gbc);

		// make number 2 button and add event handler and apply to panel
		gbc.gridx = 1;
		gbc.gridy = 3;
		btn = new Button("2");
		btn.addActionListener(new ButtonListener(NUM2));
		panel.add(btn, gbc);

		// make number 3 button and add event handler and apply to panel
		gbc.gridx = 2;
		gbc.gridy = 3;
		btn = new Button("3");
		btn.addActionListener(new ButtonListener(NUM3));
		panel.add(btn, gbc);

		// make sub button and add event handler and apply to panel
		gbc.gridx = 3;
		gbc.gridy = 3;
		btn = new Button("-");
		btn.addActionListener(new ButtonListener(SUB));
		panel.add(btn, gbc);

		// make calc button and add event handler and apply to panel
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		btn = new Button("=");
		btn.addActionListener(new ButtonListener(CALC));
		panel.add(btn, gbc);
		gbc.gridheight = 1;

		// make number 0 button and add event handler and apply to panel
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		btn = new Button("0");
		btn.addActionListener(new ButtonListener(NUM0));
		panel.add(btn, gbc);
		gbc.gridwidth = 1;

		// make dot button and add event handler and apply to panel
		gbc.gridx = 2;
		gbc.gridy = 4;
		btn = new Button(".");
		btn.addActionListener(new ButtonListener(DOT));
		panel.add(btn, gbc);

		// make add button and add event handler and apply to panel
		gbc.gridx = 3;
		gbc.gridy = 4;
		btn = new Button("+");
		btn.addActionListener(new ButtonListener(ADD));
		panel.add(btn, gbc);

		// return panel
		return panel;
	}

	// main function
	static public void main(String[] args) {
		Calculator calc = new Calculator();
	}
}