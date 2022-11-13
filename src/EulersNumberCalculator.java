import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Calculator of a Euler's Number term, implemented 
 * as a <code>Callable</code>
 * object to run upon a thread able to
 * return the calculated term
 * */
public class EulersNumberCalculator implements Callable<BigDecimal>{
	/** Number to calculate Euler's Number term */
	private int number;
	
	/**
	 * Parameterized constructor
	 * @param number Number to calculate Euler's number term
	 */
	public EulersNumberCalculator(int number) {
		this.number = number;
	}
	
	/**
	 * Task to run upon a thread
	 * @return Euler's Number term of a given number
	 */
	@Override
	public BigDecimal call(){
		return calc(this.number);
	}
	
	/**
	 * Calculates the factorial of a given number
	 * @param number Number to calculate factorial
	 * @return Factorial of the number
	 */
	private BigInteger factorial(int number) {
		BigInteger result = BigInteger.ONE;
		if(number <= 1) {
			return result;
		} else {
			return result.multiply(factorial(number - 1).multiply(BigInteger.valueOf(number)));
		}
	}
	
	/**
	 * Calculates the Euler's Number term of a given number
	 * @param number Number to calculate the Euler's Number term
	 * @return Euler's Number term
	 */
	private BigDecimal calc(int number) {
		BigDecimal term = new BigDecimal(1.0/factorial(number).doubleValue());
		return term;
	}
	
}
