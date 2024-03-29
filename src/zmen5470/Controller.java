package zmen5470;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;


/**
 * Main controller for the application
 * holds methods to modify calculator state, update view
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public final class Controller {
	public static final Rational ZERO = new Rational(0);
	
	
	// Will hold all the views of the app, allows for easy update 
	private static ArrayList<CalcInterface> views = new ArrayList<CalcInterface>();

	// Value being displayed in the box of the calculator
	private static String displayBoxString = "0";
	// Last entered value, calculator only allows for current and last values
	private static Rational valueInMemory = new Rational(0);
	private static Rational valueFromLastOperation = new Rational(0);
	// Rational value of what is in the display
	private static Rational currentValue = new Rational(0);
	

	// Used to determine what the operator in memory is
	private static boolean readyForNewInput = true;
	private static char lastOperator = '~';
	private static char operator = '~';
	private static boolean operatorInMemory = false;
	
	/**
	 * Returns the length of the current string in the box
	 * @return
	 */
	public static int length() {
		return displayBoxString.length();
	}

	//  ============
	//  = Mutators =
	//  ============

	/**
	 * New view to be updated and controller by the controller
	 * @param newCalcView an instance of `CalcInterface` which implements `updateView`
	 */
	public static void addNewView(CalcInterface newCalcView) {
		views.add(newCalcView);
	}

	/**
	 * Accessor method to get the value in the display box
	 * @return String currently in the display box
	 */
	public static String getDisplayBoxString() {
		return displayBoxString;
	}

	/**
	 * Settor method to set the displayBoxString
	 * @param  newDisplayBoxString new string to set in the display box
	 * @return                     returns the new string
	 */
	public String updateDisplayBoxString(String newDisplayBoxString) {
		displayBoxString = newDisplayBoxString;
		updateViews();
		return displayBoxString;
	}
	
	/**
	 * Returns weather the current value on screen is 0
	 * @return
	 */
	public static boolean currentIsZero() {
		return currentValue.equals(ZERO);
	}
	
	/**
	 * Returns weather the current value in memory is 0
	 * @return
	 */
	public static boolean inMemoryIsZero() {
		return valueInMemory.equals(ZERO);
	}
	
	/**
	 * Validates that operations can be performed on the string
	 * @return boolean 
	 */
	public static boolean canOperate() {
		if (displayBoxString.charAt(length()-1) == '|') {
			return false;
		} else {
			return true;
		}
	}

	//  ======================
	//  = Controller Actions =
	//  ======================
	
	/**
	 * Used to delegate arithmetic operators
	 * will eval when appropriate
	 * @param o symbol of operation to perform
	 */
	public static void arithEval(char o) {
		
		// Look for an operator in memory, clear it
		if(operatorInMemory) {
			if(canOperate()) {
				equals();
				operator = o;
				operatorInMemory = true;
				readyForNewInput = true;
			}
			
		} else {
			valueInMemory = new Rational(currentValue);
			 operator = o;
			 operatorInMemory = true;
			 readyForNewInput = true;
		}
	}
	
	/**
	 * Clear the view and reset values
	 * @return void
	 */
	
	public static void clear() {	
		if(currentIsZero()) { //Clear All
			valueInMemory = ZERO;
			currentValue = ZERO;
		} else { // Clear screen
			currentValue = ZERO;
		}
		
		displayBoxString = "0";
		operator = '~';
		lastOperator = '~';
		operatorInMemory = false;
		updateViews();
	}
	
	/**
	 * Delete the last character in the display box
	 */
	public static void delete() {
		if(!currentIsZero()) {
			String newVal = displayBoxString.substring(0, length()-1);
			displayBoxString = newVal;
			
			updateViews();
		}
	}
	
	/**
	 * invert the sign of the current value
	 */
	public static void negate() {
		if(!currentIsZero()) {
			Rational negated = currentValue.mul(new Rational(-1));
			currentValue = negated;
			displayBoxString = currentValue.toString();
			updateViews();
		}
	}
	
	/**
	 * normalize the current value
	 */
	public static void normalize() {
		currentValue = new Rational(currentValue);
		displayBoxString = currentValue.toString();
		updateViews();
	}

	/**
	 * Add a value to the display box
	 * @param strToAppend
	 * @return the new value of the display box
	 */
	public static String addToDisplayBox(String strToAppend) {
		try {
			int num = Integer.parseInt(strToAppend);
			addNumToDisplayBox(num);
		} catch (NumberFormatException e) {
			try {
				if (strToAppend.equals("|")) {
					if(!displayBoxString.contains("|") && !currentIsZero() && !readyForNewInput) {
						displayBoxString += strToAppend;
					}
				} else {
					throw new Exception();
				}
			} catch (Exception ex) {
				showError("Invalid string appended");
			}
		}
		updateViews();
		return displayBoxString;
	}

	/**
	 * Add integer value to display box
	 * @param intToAppend
	 * @return new value of the display box
	 */
	public static String addToDisplayBox(int intToAppend) {

		System.out.println("addToDisplayBox");
		
		if (readyForNewInput || currentIsZero()) {
			displayBoxString = Integer.toString(intToAppend);
			valueInMemory = new Rational(currentValue);
			currentValue = new Rational(intToAppend);
			readyForNewInput = false;
		} else {
			addNumToDisplayBox(intToAppend);
			currentValue = new Rational(displayBoxString);
		}
		updateViews();
		return displayBoxString;
	}

	private static String addNumToDisplayBox(int i) {
		displayBoxString += Integer.toString(i);
		return displayBoxString;
	}

	/**
	 * Calculate the result of the value on screen and in memory
	 */
	public static void equals() {
		
		// Do nothing 
		if(!canOperate() || (!operatorInMemory && lastOperator == '~'))
			return;
		
		try {
			Rational result = null;
			char op = (operatorInMemory) ? operator : lastOperator;
			Rational val = new Rational( (operatorInMemory) ? valueInMemory : valueFromLastOperation );

			switch(op) {
			case '+':
				result = val.add(currentValue);
				break;
			case '-':
				result = val.sub(currentValue);
				break;
			case '*':
				result = val.mul(currentValue);
				break;
			case '/':
				if (currentIsZero() && !val.equals(ZERO)) {
					throw new IllegalArgumentException("Argument 'divisor' is 0");
				}
				result = val.div(currentValue);
				break;
			}

			if (operatorInMemory) {
				valueFromLastOperation = new Rational(currentValue);
			}
			currentValue = new Rational(result);

			valueInMemory = ZERO;
			displayBoxString = currentValue.toString();
			readyForNewInput = true;
			
			if(operatorInMemory) {
				operator = '~';
				operatorInMemory = false;
			}
			lastOperator = op;
			
		} catch (IllegalArgumentException ex) {
			showError("Cannot divide by 0");
		} catch (Exception ex){
			showError("An unknown error has occured: " + ex.toString());
		}
		
		updateViews();
	}

	/**
	 * Iterate through and update each view that is available
	 * @return  void
	 */
	private static void updateViews() {
		Iterator<CalcInterface> viewsIterator = views.iterator();

		while (viewsIterator.hasNext()) {
			viewsIterator.next().updateView();
		}
		
		System.out.println("Current val: " + currentValue.toString());
		System.out.println("Memory val: " + valueInMemory.toString());
		System.out.println("Operator: " + operator);
		System.out.println("Last Op: " + lastOperator);
		System.out.println("Op In Memeory: " + operatorInMemory);
		System.out.println();
	}


	/**
	 * Show an error message
	 * @param message to show
	 * @return void
	 */
	public static void showError(String message){
		JOptionPane.showMessageDialog(null, message,"Error!", JOptionPane.ERROR_MESSAGE);
	}
}
