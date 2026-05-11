package com.practice.onlineTest;

class NumberCodeConverter {

    /*java program for Convert the given Number to a Code and vice-versa

A number can only be in the range of 1 to 190. A number can be transformed to code "xy" such that x is in range [A, S] and y is in range [0,9] and is in increasing order.
For example:

| Number |  Code  |
| 1      |  A0    |
| 2      |  A1    |
| 11     |  B0    |
| 100    |  J9    |
| 190    |  S9    |
Implement function to get Code with given number
f(100) = "J9"

Implement function to get number with given code
f("J9") = 100
*/

    /*Observation

Codes follow this pattern:

Number Range	Code
1–10	A0–A9
11–20	B0–B9
21–30	C0–C9
...	...
181–190	S0–S9

So:

letters A → S represent groups of 10
digits 0 → 9 represent position inside group
Mapping Logic

For a given number:

1 -> A0
2 -> A1
...
10 -> A9
11 -> B0

Formula:

Letter index

group=⌊(number−1)/10⌋

Digit

digit=(number−1) mod 10

Dry Run

For:

100

Group:

(100−1)/10=9

Letter:

A + 9 = J

Digit:

(100−1)mod10=9

Result:

J9

Time Complexity

Both operations:

O(1)
*/
    public static void main(String[] args) {

        // Number -> Code
        System.out.println(
                getCode(1));     // A0

        System.out.println(
                getCode(100));   // J9

        System.out.println(
                getCode(190));   // S9

        // Code -> Number
        System.out.println(
                getNumber("A0")); // 1

        System.out.println(
                getNumber("J9")); // 100

        System.out.println(
                getNumber("S9")); // 190
    }

    // Number -> Code
    public static String getCode(int number) {

        if (number < 1 || number > 190) {
            throw new IllegalArgumentException(
                    "Number out of range");
        }

        int group = (number - 1) / 10;

        int digit = (number - 1) % 10;

        char letter = (char) ('A' + group);

        return "" + letter + digit;
    }

    // Code -> Number
    public static int getNumber(String code) {

        if (code == null || code.length() != 2) {

            throw new IllegalArgumentException(
                    "Invalid code");
        }

        char letter = code.charAt(0);

        int digit =
                code.charAt(1) - '0';

        int group = letter - 'A';

        return group * 10 + digit + 1;
    }


}