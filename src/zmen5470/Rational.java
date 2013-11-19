package zmen5470;

import java.util.StringTokenizer;
import java.math.BigInteger;

/**
 * Class description
 * 
 * @author Adam Zmenak
 *        100495470
 *        zmen5470@wlu.ca
 * @version Nov 17, 2013
 *
 */

public class Rational {
	private int n;
	private int d;

	/**
	 * constructor for empty Rational
	 */
	Rational() {
		n = 0; d = 0;
	}

	/**
	 * constructor for simple integer Rational
	 * @param r some integer
	 */
	Rational(int r) {
		n = r;
		d = 1;
		normalize();
	}

	/**
	 * constructor for simple fraction Rational
	 * @param r numerator of rational number
	 * @param o denominator of rational number
	 */
	Rational(int r, int o) {
		n = r;
		d = o;
		normalize();
	}
	
	/**
	 * Constructor for making `Rational` from other `Rational`
	 * @param that
	 */
	Rational(Rational that) {
		n = that.n;
		d = that.d;
	}

	/**
	 * String constructor for Rational, takes strings in format </br>
	 * <code>a|b</code>
	 * or
	 * <code>a</code>
	 * @param r properly formated string of a rational number
	 */
	Rational(String r) {
		StringTokenizer ST = new StringTokenizer(r,"|");
		String s;
		s = ST.nextToken();
		
		try {
			n = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			Controller.showError("That number is too big for this calculator to handle, please clear the calcualtor");
		}

		if (ST.hasMoreTokens()) {
			s = ST.nextToken();
			d = Integer.parseInt(s);
		} else {
			d = 1;
		}
		normalize();
	}

	/**
	 * returns pretty printed string representation of an object of class Rational
	 * @return	string representation of the rational
	 * 			number class
	 */
	public String toString () {
		String str;
		if (this.d == 1) {
			str = String.valueOf( this.n);
		} else {
			str = String.valueOf(this.n) + '|' + String.valueOf(this.d); 
		}
		return str;
	}

	/**
	 * Take a rational number and normalize it; this
	 * will return reduces greatest common divisor 
	 * in the numerator and denominator and
	 * and make the denominator positive
	 * @return normalized Rational number class
	 */
	public Rational normalizer() {
		Rational res = new Rational();

		// find the greatest common divisor 
		// using Euclid's algorithm 
		int gdc,b,t;
		gdc = n; b = d;
		while (b != 0) {
			t = b;
			b = gdc % t;
			gdc = t;
		}

		// divide the rational number by gdc
		gdc = Math.abs(gdc);
		res.n = n/gdc;
		res.d = d/gdc;

		if (gdc > 1) {
			// ensure the result is always either
			// positive, or
			// has a negative in the numerator
			if ((res.n * res.d) < 0) {
				res.n = -Math.abs(res.n);
				res.d = Math.abs(res.d);
			} else {
				res.n = Math.abs(res.n);
				res.d = Math.abs(res.d);
			}
		}

		return res;
	}

	/**
	 * Utility method to normalize self 
	 * will normalize or do nothing 
	 */
	private void normalize() {
		Rational norm = this.normalizer();
		n = norm.n;
		d = norm.d;
	}

	/**
	 * returns result of addition of this and other object of class Rational
	 * @param num a rational number to be added
	 * @return sum of both numbers as Rational
	 */
	public Rational add (Rational num) {
		Rational res = new Rational();
		if (n == 0) res = num;
		else {
			if (num.d == d) {
				res.n = num.n + n;
				res.d = d;
			} else {
				res.d = d * num.d;
				res.n = (n*num.d) + (num.n*d);
			}
		}
		return res.normalizer();
	}

	/**
	 * returns result of subtraction of this and other object of class Rational
	 * @param num a rational number to subtract
	 * @return Rational number less the given number
	 */
	public Rational sub (Rational num) {
		Rational res = new Rational();

		if (num.d == d) {
			res.n = n - num.n;
			res.d = d;
		} else {
			res.d = d * num.d;
			res.n = (n*num.d) - (num.n*d);
		}

		return res.normalizer();
	}

	/**
	 * returns result of multiplication of this and other object of class Rational
	 * @param num a rational number to multiply
	 * @return Rational number product of given numbers
	 */
	public Rational mul (Rational num) {
		Rational res = new Rational();

		res.n = n * num.n;
		res.d = d * num.d;

		return res.normalizer();
	}

	/**
	 * returns result of division of this and other object of class Rational
	 * @param num a rational number to divide
	 * @return Rational number quotient of given numbers
	 */
	public Rational div (Rational num) {
		Rational res = new Rational();

		res.n = n * num.d;
		res.d = d * num.n;

		return res.normalizer();
	}

	public Rational power(int p) {
		Rational res = new Rational(n,d);
		if (p >= 2)
			for(int i=1;i<p;i++) res = res.mul(this);
		else if (p == 0) res = new Rational(1,1);
		return res;
	}

	/**
	 * tests equality of this and other object of class Rational
	 * @param num Rational number to test equality
	 * @return boolean, if equal or not
	 */
	public boolean equals (Rational num) {
		this.normalize();
		num = num.normalizer();
		if (n == num.n && this.d == num.d) return true;
		else return false;
	}
	
	public boolean isFraction() {
		return d==n;
	}

	/**
	 * test to see if the rational number is 0
	 * @return boolean, if 0 or not
	 */
	public boolean isZero() {
		return n==0;
	}

	/**
	 * test for whether number is positive, 0 is considered positive
	 * @return boolean
	 */
	public boolean isPositive() {
		return n>=0;
	}
}

