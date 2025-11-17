// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {

		for (int i = 0; i < x2; i++) {
			x1++;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		for (int i = 0; i < x2; i++) {
			x1--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int product = 0;
		for (int i = 0; i < x1; i++) {
			for (int j = 0; j < x2; j++) {
				product++;
				}
		}
		return product;
	}


	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		// Replace the following statement with your code
		int pow = x;
		for (int i = 1; i < n; i++) {
			pow = times(pow, x);
		}
		return pow;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		//assumes x1 is larger than x2
		int result = minus(x1, x2);
		int count = 1;
		while (result >= x2) {
			result = minus(result, x2);
			count++;
		}
		return count;

	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int result = x1;
		while (result >= x2) {
			result = minus(result, x2);
		}
		return result;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int power = 0;
		int result = 0;
	
		while (result < x) {
			result = times(power, power);
			power++;
		}
		return minus(power, 1);
	}	  	  
}