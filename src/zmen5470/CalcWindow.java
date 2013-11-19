package zmen5470;

import javax.swing.JFrame;

/**
 * Sets the properties of the calculator window
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class CalcWindow extends JFrame {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 8235836699759049929L;
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 360;
	
	
	/**
	 * Basic constructor for the window
	 */
	public CalcWindow (){
		super();
		setProperties("Calculator");
	}

	/**
	 * Constructor to allow window to be named
	 * @param winName
	 */
	public CalcWindow (String winName) {
		super();
		setProperties(winName);
	}

	/**
	 * Called by each constructor... sets the properties of the window
	 * @param windowName
	 */
	private void setProperties(String windowName) {
		setSize(WIDTH, HEIGHT);
		setTitle(windowName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
