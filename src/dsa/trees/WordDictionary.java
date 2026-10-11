package dsa.trees;

/**
 * LC 211 Design Add and Search Words. A trie where '.' in a search matches any letter, so the
 * search branches into every child at a dot.
 *
 * <p>addWord O(L). search O(L) without dots, up to O(26^L) in the worst case with dots.
 */
public class WordDictionary {
    private static final class Node {
        final Node[] next = new Node[26];
        boolean isWord;
    }

    private final Node root = new Node();

    public void addWord(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new Node();
            }
            node = node.next[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int i, Node node) {
        if (i == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(i);
        if (c != '.') {
            Node child = node.next[c - 'a'];
            return child != null && search(word, i + 1, child);
        }
        for (Node child : node.next) {
            if (child != null && search(word, i + 1, child)) {
                return true;
            }
        }
        return false;
    }
}
