/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(randomAnagram("fox"));

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String processed1 = preProcess(str1);
		String processed2 = preProcess(str2);

		for (int i = 0; i < processed1.length(); i++) {
			//check if processed1[i] = some letter in processed2, if it is, remove from both, otherwise keep
			// if processed1 == "" by the end, then it passed

			for (int j = 0; j < processed2.length(); j++) {
				if (processed1.charAt(i) == processed2.charAt(j)) {
					processed1 = processed1.substring(0, i) + processed1.substring(i + 1, processed1.length());
					processed2 = processed2.substring(0, j) + processed2.substring(j + 1, processed2.length());
					i--;
					j--;
					break;
				}
			}
		}

		if (processed1.length() == 0) {
			return true;
		}

		return false;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
		String accept = "abcdefghijklmnopqrstuvwxyz";
		String processedString = "";
		
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < accept.length(); j++) {
				if (str.charAt(i) == accept.charAt(j)) {
					processedString += str.charAt(i);
				}
			}

		}
		return processedString;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
		char[] chars = str.toCharArray();
		int N = str.length();
		for (int i = 0; i < (N - 1); i++) {
			int r = i + (int) (Math.random() * (N-i));
			char temp = chars[r];
			chars[r] = chars[i];
			chars[i] = temp;

		}
		str = new String(chars);
		return str;
}}
