package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * One day ninja is thinking of a competition between two strings wherein each round two strings take part and there are some rules of that competition if the strings are able to tell that the minimum numbers of characters they need to change to satisfy at least one of the rules they will be declared as the winner.
 *
 * Rules for the competition are as follows :
 *
 * The character of every string of ‘STR1’ is strictly less than every character of every string of ‘STR2’ and vice versa.
 * ‘STR1’ and ‘STR2’ only differ in one distinct character.
 * So your task is to find the minimum numbers of characters we need to change to satisfy and of the given rule.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 2
 * bab cbb
 * badabb cba
 * Sample Output 1 :
 * 2
 * 3
 * Explanation of Sample Input 1 :
 * Test Case 1 :
 * For the first test case, given ‘STR1 = bab’ and ‘STR2 = cbb’
 * We can change ‘STR2 = ccc’ by changing both ‘b’ in ‘STR2’ to ‘c’ in two steps.
 * We can change ‘STR1 = aaa’ and ‘STR2 = bbb’ in three steps.
 * We can change ‘STR1 = bbb’ and ‘STR2 = bbb’ in two steps.
 * So we return ‘2’ as it is the minimum number of steps to satisfy one of the given rules.
 *
 * Test Case 2 :
 * For this test case, given ‘STR1 = badabb’ and ‘STR2 = cba’
 * We can change ‘STR2 = eee’ and satisfy the first rule so the minimum number of steps required is ‘3’.
 * Sample Input 2 :
 * 1
 * ab bb
 * Sample Output 2 :
 * 1
 * Explanation of Sample Input 2 :
 * Test Case 1 :
 * For this test case, we can change ‘STR1 = aa’ so we return ‘1’ as the answer.
 */
public class MinCharReplacement {
    public int minCharReplacement(String str1, String str2) {
        int rule1 = calculateRule1(str1, str2);
        int rule2 = calculateRule2(str1, str2);
        return Math.min(rule1, rule2);
    }
    
    private int calculateRule1(String str1, String str2) {
        int max1 = 0, max2 = 0;
        for (char c : str1.toCharArray()) {
            max1 = Math.max(max1, c);
        }
        for (char c : str2.toCharArray()) {
            max2 = Math.max(max2, c);
        }
        
        int changes1 = 0, changes2 = 0;
        for (char c : str1.toCharArray()) {
            if (c >= max2) changes1++;
        }
        for (char c : str2.toCharArray()) {
            if (c <= max1) changes2++;
        }
        
        return Math.min(changes1, changes2);
    }
    
    private int calculateRule2(String str1, String str2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for (char c : str1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : str2.toCharArray()) {
            freq2[c - 'a']++;
        }
        
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            diff += Math.abs(freq1[i] - freq2[i]);
        }
        
        return diff / 2;
    }

    public static void main(String[] args) {
        MinCharReplacement mcr = new MinCharReplacement();
        
        // Test case 1: "bab", "cbb"
        System.out.println("Test case 1: " + mcr.minCharReplacement("bab", "cbb"));
        
        // Test case 2: "badabb", "cba"
        System.out.println("Test case 2: " + mcr.minCharReplacement("badabb", "cba"));
        
        // Test case 3: "ab", "bb"
        System.out.println("Test case 3: " + mcr.minCharReplacement("ab", "bb"));
    }
}
