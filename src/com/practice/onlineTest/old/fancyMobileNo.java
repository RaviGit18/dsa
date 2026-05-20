package com.practice.onlineTest.old;

//Java program to check if a given mobile 
//number is fancy or not. https://www.geeksforgeeks.org/check-if-a-given-mobile-number-is-fancy/

/*A 10 digit mobile number is called fancy if it satisfies any of the following three conditions.

    A single number occurs three consecutive times. Like 777.
    Three consecutive digits are either in increasing or decreasing fashion. Like 456 or 987.
    A single digit occurs four or more times in the number. Like 9859009976 � here the digit 9 occurs 4 times.
*/

class fancyMobileNo {

	public static void main(String[] args) {

		String mobileNumber = "9859009976";

		if (isFancy(mobileNumber))
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	public static boolean isFancy(String mobileNumber) {
		int incrementCount = 0;
		int decrementCount = 0;
		int consecutiveCount = 1;
		int[] countArray = new int[10];
		int prevDigit = -1;
		for (int i = 0; i < mobileNumber.length(); i++) {

			int digit = Integer.parseInt(String.valueOf(mobileNumber.charAt(i)));
			countArray[digit] += 1;
			// Checking for Number of occurrences of any digit is greater than 3

			if (countArray[digit] > 3)
				return true;
			// Checking for consecutive digits are same

			if (prevDigit == digit)
				consecutiveCount += 1;

			else if (prevDigit == digit + 1 && prevDigit != -1) {
				incrementCount += 1;
				decrementCount = 0;
				consecutiveCount = 1;
			}

			else if (digit == prevDigit + 1) {
				decrementCount += 1;
				incrementCount = 0;
				consecutiveCount = 1;
			}

			if (consecutiveCount == 3)
				return true;

			if (incrementCount == 2 || decrementCount == 2)
				return true;

			prevDigit = digit;
		}
		return false;
	}
}


