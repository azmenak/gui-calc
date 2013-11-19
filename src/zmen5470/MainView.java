package zmen5470;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Combined view of each view element (buttons, 
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class MainView extends JPanel {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 6662203965957584386L;
	
	// Sets up each view type that will be included in the calculator
	private OperatorView optrView;
	private NumbersView numsView;
	private DisplayBoxView dboxView;

	/**
	 * Set up the models for each view
	 */
	public MainView() {
		optrView = new OperatorView();
		numsView = new NumbersView();
		dboxView = new DisplayBoxView();
		
		layoutView();
	}

	/**
	 * Simply adds each view to the main combined view
	 */
	public void layoutView() {
		this.setLayout(new BorderLayout());
		this.add(dboxView, BorderLayout.NORTH);
		this.add(optrView, BorderLayout.EAST);
		this.add(numsView, BorderLayout.CENTER);
	}
}
