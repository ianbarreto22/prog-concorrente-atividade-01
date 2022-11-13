import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrent calculation of Euler's Number, within
 * fixed thread pool.
 *
 * @author Ian Jerônimo Nobre Barreto
 * @author Victor Gabriel Sousa de Castro
 */
public class FixedThreadPool {
	/**
	 * Main method
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		System.out.println("Concurrent calculation of Euler's Number\n");
		if (args.length != 2) {
			System.err.println("Invalid number of terms.");
			System.err.println("The program will be finished.");
			System.exit(1);
		}
		
		int precision = Integer.parseInt(args[0]);
		int num_threads = Integer.parseInt(args[1]);
		
		ExecutorService executor = Executors.newFixedThreadPool(num_threads);
		
		List<Future<BigDecimal>> results = new ArrayList<>();
		
		for (int i=0; i < precision; i++) {
			Callable<BigDecimal> calculator = new EulersNumberCalculator(i);
			Future<BigDecimal> term = executor.submit(calculator);
			results.add(term);
		}
		
		BigDecimal sum = new BigDecimal(0.0);
		try {
			for (Future<BigDecimal> result : results) {
				sum = sum.add(result.get());
			}
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			System.out.println("Result: " + sum);
		}
		
	}
}
