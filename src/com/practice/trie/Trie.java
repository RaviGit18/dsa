package com.practice.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    /*Design a Trie
Design and implement a trie data structure that supports the following operations:

insert(word: str) -> None: Inserts a word into the trie.
search(word: str) -> bool: Returns true if a word exists in the trie, and false if not.
has_prefix(prefix: str) -> bool: Returns true if the trie contains a word with the given prefix, and false if not.

Example:
Input: [
  insert('top'),
  insert('bye'),
  has_prefix('to'),
  search('to'),
  insert('to'),
  search('to')
]
Output: [True, False, True]
Explanation:

insert("top")    # trie has: "top"
insert("bye")    # trie has: "top" and "bye"
has_prefix("to") # prefix "to" exists in the string "top": return True
search("to")     # trie does not contain the word "to": return False
insert("to")     # trie has: "top", "bye", and "to"
search("to")     # trie contains the word "to": return True
Constraints:
The words and prefixes consist only of lowercase English letters.

The length of each word and prefix is at least one character.
*/

    public static void main(String[] args) {
        TrieNode root = new TrieNode();

        insert("top", root);
        insert("bye", root);
        System.out.println(search("to", root));
        System.out.println(startsWith("to", root));
        System.out.println(search("bye", root));
        System.out.println(startsWith("bye", root));
        insert("to", root);
        System.out.println(search("to", root));
        System.out.println(startsWith("to", root));
    }

    private static void insert(String word, TrieNode root) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
    }

    private static boolean search(String word, TrieNode root) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {

            if (!node.children.containsKey(c)) {
                return  false;
            }

            node = node.children.get(c);
        }

        return  node.isWord;
    }

    private static boolean startsWith(String prefix, TrieNode root) {
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c)) {
                return  false;
            }

            node = node.children.get(c);
        }

        return true;
    }

}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isWord;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}
