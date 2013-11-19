package zmen5470;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class description
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class OperatorView extends JPanel implements CalcInterface {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -9050180433992750990L;
	
	private JButton[] operatorButtons;
	private JButton clearButton;

	/**
	 * Empty contrustor
	 */
	public OperatorView() {
		String[] supportedOperations = "+ - * / # | = ± < C".split("\\s");
		// + => add
		// - => subtract
		// * => multiply
		// / => divide
		// # => normalize
		// | => denominator delimitor
		// ± => negate
		// < => backspace
		// C => clear
		
		// count = 9 operations

		operatorButtons = new JButton[supportedOperations.length];
		for(int i=0; i<supportedOperations.length; i++) {
			String label = "";
			switch (supportedOperations[i]) {
			case "*":
				label = "×";
				break;
			case "/":
				label = "÷";
				break;
			case "#":
				label = "3/6 ▸ 1/2";
				break;
			case "|":
				label = "x/y";
				break;
			case "<":
				label = "delete";
				break;
			case "C":
				label = "all clear";
				break;
			default:
				label = supportedOperations[i];
				break;
			}
			operatorButtons[i] = new JButton(label);
			
			if (supportedOperations[i].equals("C"))
				clearButton = operatorButtons[i];
				
			operatorButtons[i].addActionListener(new ButtonListener(supportedOperations[i].charAt(0)));
		}
		
		Controller.addNewView(this);
		layoutView(); // Initialize the layout
	}
	
	/**
	 * Lays out the operator buttons
	 * @return void
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(8,1));
		for(JButton jbutton: operatorButtons) {
			this.add(jbutton);
		}
	}
	
	/**
	 * Implementing `updateView` from CalcInterface
	 * required here, but buttons don't need to change ever so does nothing
	 */
	@Override
	public void updateView() {
		if((!Controller.inMemoryIsZero() && Controller.currentIsZero()) || (Controller.inMemoryIsZero() && Controller.currentIsZero())) {
			clearButton.setText("all clear");
		} else {
			clearButton.setText("clear");
		}
	}
	
	/**
	 * Button that will delegate to the Controller
	 * @author Adam Zmenak
	 *
	 */
	private class ButtonListener implements ActionListener {
		private char operation;
		
		public ButtonListener(char oprn) {
			operation = oprn;
		}
		
		public void actionPerformed(ActionEvent event) {
			switch (operation) {
			case '+':
				Controller.arithEval('+');
				break;
			case '-':
				Controller.arithEval('-');
				break;
			case '*':
				Controller.arithEval('*');
				break;
			case '/':
				Controller.arithEval('/');
				break;
			case '#':
				Controller.normalize();
				break;
			case '|':
				Controller.addToDisplayBox("|");
				break;
			case '±':
				Controller.negate();
				break;
			case '<':
				Controller.delete();
				break;
			case 'C':
				Controller.clear();
				break;
			case '=':
				Controller.equals();
				break;
			}
		}
	}
}
