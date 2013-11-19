package zmen5470;

/**
 * Class description
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class Calculator {

	/**
	 * Main entry point
	 * @param args
	 */
	public static void main(String[] args) {
		MainView view = new MainView();
		CalcWindow frame = new CalcWindow();
		
		frame.setContentPane(view);
		frame.setVisible(true);
	}

}
