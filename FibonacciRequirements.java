package FibonacciRequirements;

/**
 * This class provides implementations of the Fibonacci sequence calculation
 * using both recursive and iterative approaches.
 */

public class FibonacciRequirements {
	  
	//Recursive Implementation
	/**
	   * Calculates the Fibonacci sequence recursively.
	   *
	   * @param n The input value for Fibonacci calculation.
	   * @return The Fibonacci number at position 'n'.
	   */
	
	  public static int fibonacciRecursive(int n) {
	    if (n <= 1)
	      return n;
	    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
	  }
	  
	  // Iterative implementation
	  /**
	   * Calculates the Fibonacci sequence iteratively.
	   *
	   * @param n The input value for Fibonacci calculation.
	   * @return The Fibonacci number at position 'n'.
	   */
	  public static int fibonacciIterative(int n) {
	    if (n <= 1)
	      return n;
	    int fib = 0;
	    int prev1 = 0;
	    int prev2 = 1;
	    for (int i = 2; i <= n; i++) {
	      fib = prev1 + prev2;
	      prev1 = prev2;
	      prev2 = fib;
	    }
	    return fib;
	  }
	  
	  /**
	   * Main method to demonstrate the usage of the Fibonacci calculations.
	   *
	   * @param args Command line arguments (not used).
	   */
	  
	  public static void main(String[] args) {
		// Input value for Fibonacci calculation
		  int n = 40;
		    
		    //Measure the runtime of the recursive implementation
		    long startTimeRecursive = System.nanoTime();
		    int resultRecursive = fibonacciRecursive(n);
		    long endTimeRecursive = System.nanoTime();
		    long durationRecursive = endTimeRecursive - startTimeRecursive;
		    
		    //Measure the runtime of the iterative implementation
		    long startTimeIterative = System.nanoTime();
		    int resultIterative = fibonacciIterative(n);
		    long endTimeIterative = System.nanoTime();
		    long durationIterative = endTimeIterative - startTimeIterative;
		    
		    //Print the results and runtime efficiency
		    System.out.println("Recursive Result: " + resultRecursive);
		    System.out.println("Recursive Runtime: " + durationRecursive + " nanoseconds");
		    
		    System.out.println("Iterative Result: " + resultIterative);
		    System.out.println("Iterative Runtime: " + durationIterative + " nanoseconds");
		}
	  }
	
