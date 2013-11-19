package zmen5470;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.*;

/**
 * View for the display box in the calculator which holds the numbers we are about
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 18, 2013
 *
 */

public class DisplayBoxView extends JPanel implements CalcInterface {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5718679474563430289L;
	
	private JTextField box;
	private JScrollBar scrollBar;
	private BoundedRangeModel brm;
	
	/**
	 * Construct the view and create the layout 
	 */
	public DisplayBoxView() {
		box = new JTextField("0");
		box.setEditable(false);
		box.setHorizontalAlignment(JTextField.RIGHT);
		
		scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		
		brm = box.getHorizontalVisibility();
		scrollBar.setModel(brm);
		
		Controller.addNewView(this);
		layoutView();
	}
	
	/**
	 * Arrange the box across the top, with a scroll bar under
	 */
	private void layoutView() {
		this.setLayout(new BorderLayout());
		this.add(box, BorderLayout.CENTER);
		this.add(scrollBar, BorderLayout.SOUTH);
	}
	
	/**
	 * Update the text inside the box
	 */
	@Override
	public void updateView() {
		box.setText(Controller.getDisplayBoxString());
		
	}
}
