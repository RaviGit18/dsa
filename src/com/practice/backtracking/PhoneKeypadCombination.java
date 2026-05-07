package com.practice.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PhoneKeypadCombination {

    /*Phone Keypad Combinations
You are given a string containing digits from 2 to 9 inclusive. Each digit maps to a set of letters as on a traditional phone keypad:

1
2 abc

3 def

4 ghi

5 jkl

6 mno

7 pqrs

8 tuv

9 wxyz

Return all possible letter combinations the input digits could represent.

Example:
Input: digits = '69'
Output: ['mw', 'mx', 'my', 'mz', 'nw', 'nx', 'ny', 'nz', 'ow', 'ox', 'oy', 'oz']
*/

    static void main(String[] args) {
        String digits = "69";
        System.out.println("Phone keypad combinations: " + letterCombinations(digits));
    }

    private static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        HashMap<Character, String> keypad_map = new HashMap<>();
        keypad_map.put('2', "abc");
        keypad_map.put('3', "def");
        keypad_map.put('4', "ghi");
        keypad_map.put('5', "jkl");
        keypad_map.put('6', "mno");
        keypad_map.put('7', "pqrs");
        keypad_map.put('8', "tuv");
        keypad_map.put('9', "wxyz");

        backtrack(keypad_map, digits, 0, new StringBuilder(), result);
        
        return result;
    }

    private static void backtrack(HashMap<Character, String> keypadMap, String digits, int i, StringBuilder combination, List<String> result) {
        if (combination.length() == digits.length()) {
            result.add(combination.toString());
            return;
        }

        String letters = keypadMap.get(digits.charAt(i));

        for (int j = 0; j < letters.length(); j++) {

            combination.append(letters.charAt(j));

            backtrack(keypadMap, digits, i + 1, combination, result);

            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
