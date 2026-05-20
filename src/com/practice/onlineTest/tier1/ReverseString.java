package com.practice.onlineTest.tier1;

public class ReverseString {

    static void main(String[] args) {
        String str = "Welcome   to Coding Ninjas ";
        System.out.println(reverseString(str));
        System.out.println(reverseStringOptimized(str));
    }

    private static String reverseString(String str)
    {
        str = str.trim();
		String[] words = str.split("\\s+");

		StringBuilder reversed = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--) {
			reversed.append(words[i]);
			if (i > 0) {
				reversed.append(" ");
			}
		}

		return reversed.toString();
    }
    private static String reverseStringOptimized(String str)
    {

        str = str.trim();
        char[] chars = str.toCharArray();

        reverse(chars, 0, chars.length - 1);

        int start = 0;
        for (int end = 0; end < chars.length; end++) {
            if (chars[end] == ' ') {
                reverse(chars, start, end - 1);
                start = end + 1;
            }
        }

        reverse(chars, start, chars.length - 1);

        return cleanSpace(chars);
    }

    private static String cleanSpace(char[] chars) {
        int n = chars.length, i = 0, j = 0;

        while(j < n) {

            while (j < n && chars[j] == ' ') {
                j++;
            }

            while (j < n && chars[j] != ' ') {
                chars[i++] = chars[j++];
            }

            // Skip spaces between words, leave one space
            while (j < n && chars[j] == ' ') {
                j++;
            }

            if (j < n) {
                chars[i++] = ' ';
            }
        }

        return new String(chars, 0, i);

    }

    private static void reverse (char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}
