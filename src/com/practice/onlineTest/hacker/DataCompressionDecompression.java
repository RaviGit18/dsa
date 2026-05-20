package com.practice.onlineTest.hacker;

public class DataCompressionDecompression {

    /*Run-Length Encoding (RLE)

It compresses repeated characters by storing:

character
count
Example

Input:

aaabbccccdd

Compressed:

a3b2c4d2


Time Complexity

Compression:
O(N)
Decompression:
O(N)

Space Complexity
O(N)
*/
    public static void main(String[] args) {

        String input = "aaabbccccdd";

        String result = compress(input);

        System.out.println(
                "Compressed String = " + result);

        String compressed = "a3b2c4d2";

        String original =
                decompress(compressed);

        System.out.println(original);
    }

    public static String compress(String input) {


        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder compressed =
                new StringBuilder();

        int count = 1;

        for (int i = 1; i < input.length(); i++) {

            if (input.charAt(i)
                    == input.charAt(i - 1)) {

                count++;
            }
            else {

                compressed.append(input.charAt(i - 1));
                compressed.append(count);

                count = 1;
            }
        }

        // Last character group
        compressed.append(
                input.charAt(input.length() - 1));

        compressed.append(count);

        return compressed.toString();
    }

    public static String decompress(String input) {

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < input.length(); i += 2) {

            char ch = input.charAt(i);

            int count =
                    input.charAt(i + 1) - '0';

            for (int j = 0; j < count; j++) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
