import java.util.Scanner;

public class RecursiveFibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        
        int result = fibonacci(n);
        System.out.println("The " + n + "th term of the Fibonacci sequence is: " + result);
        
        scanner.close();
    }

     /**
     * Calculates the nth term in the Fibonacci sequence.
     *
     * @param n The position of the term in the sequence (1-indexed).
     * @return The nth term in the Fibonacci sequence.
     * @throws IllegalArgumentException If n is not a positive integer.
     */

    public static int fibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid input. n must be a positive integer.");
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}





