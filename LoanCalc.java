// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter = 0;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
		// System.out.println(bruteForceSolver());

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double endBalance = loan;
		for (int i = 0; i < n; i++) {
			endBalance = (endBalance - payment) * (1 + rate / 100);
		}
		return endBalance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double initialPayment = loan / n;
		double Balance = 1;
		double i = 0;
		while (Balance > 0 ) {
			Balance = endBalance(loan, rate, n, initialPayment + i);
			i += epsilon;
			iterationCounter++;
		}

		return initialPayment + i;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
        double low = loan / n;
		double high = loan;
		double mid = (low + high) / 2;

		double lowEndBalance = endBalance(loan, rate, n, low);
		double highEndBalance = endBalance(loan, rate, n, high);
		double midEndBalance = endBalance(loan, rate, n, mid);


		while (lowEndBalance - highEndBalance > epsilon) {
			iterationCounter++;
			if (lowEndBalance * midEndBalance > 0) {
				low = mid;
				lowEndBalance = endBalance(loan, rate, n, low);
			} else if (midEndBalance * highEndBalance > 0) {
				high = mid;
				highEndBalance = endBalance(loan, rate, n, high);
			}
			mid = (low + high) / 2;
			midEndBalance = endBalance(loan, rate, n, mid);
		}


		return mid;
    }
}