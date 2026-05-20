package com.practice.onlineTest.old;

public class ReverseVowels {

	public static String reverseVowels(String string) {

		String vowelsStr = "aeiouAEIOU";

		int lo = 0;
		int hi = string.length() - 1;
		char[] ch = string.toCharArray();

		while (lo < hi) {

			if (!vowelsStr.contains(String.valueOf(string.charAt(lo)))) {
				lo++;
				continue;
			}

			if (!vowelsStr.contains(String.valueOf(string.charAt(hi)))) {
				hi--;
				continue;
			}

			// swaping variables
			swap(ch, lo, hi);
			lo++;
			hi--;
		}

		return String.valueOf(ch);
	}

	private static void swap(char[] ch, int lo, int hi) {

		char temparray = ch[lo];
		ch[lo] = ch[hi];
		ch[hi] = temparray;

	}

	public static void main (String args[]) {
	        
	         
	 System.out.println("After reversing vowels in a string="+ reverseVowels("InstanceOfjava"));
	        
	         
	}
}
