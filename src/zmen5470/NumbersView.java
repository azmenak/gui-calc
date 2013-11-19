package zmen5470;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class description
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class NumbersView extends JPanel implements CalcInterface  {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3290146506615239897L;
	
	private JButton[] numberButtons = new JButton[10];
	
	/**
	 * Initialize the view
	 */
	public NumbersView() {
		for (int i=0; i<10; i++) {
			numberButtons[9-i] = new JButton(Integer.toString(i));
			numberButtons[9-i].addActionListener(new ButtonListener(i));
		}
		
		Controller.addNewView(this);
		layoutView();
	}
	
	/**
	 * Add the numbers to the view in grid, as we are used to seeing
	 * on a calculator / numpad
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(4,3));
		this.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		for(JButton jbutton: numberButtons) {
			this.add(jbutton);
		}
	}

	/**
	 * Required by `CalcInterface`, does nothing here
	 */
	@Override
	public void updateView() {}
	
	/**
	 * Buttons that will launch a call to the controller on click
	 * @author Adam Zmenak
	 *
	 */
	private class ButtonListener implements ActionListener {
		private int number;
		
		public ButtonListener (int i) {
			number = i;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Controller.addToDisplayBox(number);
		}
	}
}
