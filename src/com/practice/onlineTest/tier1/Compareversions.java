package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given two versions numbers A and B as a string. Your task is to compare them and find out which one of them is a newer version.
 *
 * Note:
 * There are no leading zeros in any of the strings except in the case of zero itself. Note that, the leading zeroes are not allowed even after a '.' ie: 121.005 is an invalid version, while 121.5 is an valid version.
 * For Example:
 * A = “1.23.45”, B = “1.23.456”
 *
 * The first two parts of both the strings are the same i.e 1 and 23 and the third part of B is greater than the third part of A i.e. 45 < 456, thus string B is the latest version.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= |A|, |B| <= 10^5
 *
 * All the string A and B characters contain digits and dots only and both the strings are started and terminated by a digit.
 * Sample Input 1:
 * 2
 * 1.2.4
 * 1.2.3
 * 10.2.2
 * 10.2.2
 * Sample Output 1:
 * 1
 * 0
 * Explanation for Sample Input 1:
 * For the first test case, the first two parts of both the strings are the same but the third part of the 1st version is bigger than the 2nd version. Hence our answer is 1
 *
 * For the second test case, both the versions are identical here, so the answer will be 0.
 * Sample Input 2:
 * 2
 * 123.45
 * 123
 * 1.0.0
 * 1
 * Sample output 2:
 * 1
 * 0
 */

public class Compareversions {
    public static int compareVersions(String a, String b) {
        String[] versionA = a.split("\\.");
        String[] versionB = b.split("\\.");
        
        int maxLen = Math.max(versionA.length, versionB.length);
        
        for (int i = 0; i < maxLen; i++) {
            int numA = i < versionA.length ? Integer.parseInt(versionA[i]) : 0;
            int numB = i < versionB.length ? Integer.parseInt(versionB[i]) : 0;
            
            if (numA > numB) return 1;
            if (numA < numB) return -1;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        int t = 2;
        String[][] testCases = {
            {"1.2.4", "1.2.3"},
            {"10.2.2", "10.2.2"}
        };
        
        for (int i = 0; i < t; i++) {
            String a = testCases[i][0];
            String b = testCases[i][1];
            System.out.println(compareVersions(a, b));
        }
    }
}
