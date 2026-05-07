package com.practice.trie;

class InsertAndSearchWordsWithWildcards {
    /*Insert and Search Words with Wildcards
Design and implement a data structure that supports the following operations:

insert(word: str) -> None: Inserts a word into the data structure.
search(word: str) -> bool: Returns true if a word exists in the data structure and false if not. The word may contain wildcards ('.') that can represent any letter.
Example:
Input: [
  insert('band'),
  insert('rat'),
  search('ra.'),
  search('b..'),
  insert('ran'),
  search('.an')
]
Output: [True, False, True]
Explanation:

insert("band") # data structure has: "band"
insert("rat")   # data structure has: "band" and "rat"
search("ra.")   # "ra." matches "rat": return True
search("b..")   # no three-letter word starting with ‘b' in the
               # data structure: return False
insert("ran")  # data structure has: "band", "rat", and "ran"
search(".an")  # ".an" matches "ran": return True
Constraints:
Words will only contain lowercase English letters and '.' characters.
*/

    static void main(String[] args) {
        TrieNode root = new TrieNode();
        insert("band", root);
        insert("rat", root);
        System.out.println(search("ra.", root));
        System.out.println(search("b..", root));
        insert("ran", root);
        System.out.println(search(".an", root));
    }

    private static boolean search(String word, TrieNode root) {
        //return searchHelper(word, 0, root);
        return searchHelper_1(word, 0, root);
    }

    private static boolean searchHelper(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            if (!node.children.containsKey(c)) {
                return false;
            }
            return searchHelper(word, index + 1, node.children.get(c));
        }
    }

    private static boolean searchHelper_1(String word, int index, TrieNode node) {
        for (int i = index; i < word.length(); i++) {

            char c = word.charAt(i);

            if (c == '.') {
                for (TrieNode child : node.children.values()) {
                    if (searchHelper(word, i + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return false;
            }
        }

        return node.isWord;
    }

    private static void insert(String word, TrieNode root) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
    }
}
