package dsa.trees;

import dsa.common.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/** More tree practice: recursion that returns two things, BST order, construction and tries. */
public final class TreePractice {
    private TreePractice() {}

    /**
     * LC 226 Invert Binary Tree. Swap the children at every node.
     *
     * <p>Time O(n). Space O(height).
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode left = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = left;
        }
        return root;
    }

    /**
     * LC 100 Same Tree. Equal roots and both subtrees equal.
     *
     * <p>Time O(n). Space O(height).
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * LC 543 Diameter of Binary Tree (in edges). The longest path through a node is left height +
     * right height; compute heights and track the best along the way.
     *
     * <p>Time O(n). Space O(height).
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] best = {0};
        height(root, best);
        return best[0];
    }

    private static int height(TreeNode node, int[] best) {
        if (node == null) {
            return 0;
        }
        int l = height(node.left, best);
        int r = height(node.right, best);
        best[0] = Math.max(best[0], l + r);
        return 1 + Math.max(l, r);
    }

    /**
     * LC 110 Balanced Binary Tree. Return a subtree's height, or -1 as soon as any subtree is
     * unbalanced, so each node is visited once.
     *
     * <p>Time O(n). Space O(height).
     */
    public static boolean isBalanced(TreeNode root) {
        return balancedHeight(root) != -1;
    }

    private static int balancedHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = balancedHeight(node.left);
        int r = balancedHeight(node.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    }

    /**
     * LC 572 Subtree of Another Tree. Check isSameTree at every node of root.
     *
     * <p>Time O(n * m). Space O(height).
     */
    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) {
            return sub == null;
        }
        return isSameTree(root, sub) || isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    /**
     * LC 112 Path Sum (root to leaf). Subtract each value on the way down; succeed at a leaf
     * whose value equals what's left.
     *
     * <p>Time O(n). Space O(height).
     */
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    /**
     * LC 235 Lowest Common Ancestor of a BST. Walk down from the root: go left if both values are
     * smaller, right if both are larger, otherwise this node splits them.
     *
     * <p>Time O(height). Space O(1).
     */
    public static TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * LC 199 Binary Tree Right Side View. Level-order traversal; keep the last node of each level.
     *
     * <p>Time O(n). Space O(width).
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (size == 1) {
                    out.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return out;
    }

    /**
     * LC 1448 Count Good Nodes (no larger value on the path from the root). Pass the path
     * maximum down.
     *
     * <p>Time O(n). Space O(height).
     */
    public static int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private static int goodNodes(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }
        int good = node.val >= maxSoFar ? 1 : 0;
        int max = Math.max(maxSoFar, node.val);
        return good + goodNodes(node.left, max) + goodNodes(node.right, max);
    }

    /**
     * LC 230 Kth Smallest Element in a BST. Inorder traversal visits values in sorted order; stop
     * at the kth.
     *
     * <p>Time O(height + k). Space O(height).
     */
    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            node = node.right;
        }
    }

    /**
     * LC 105 Construct Binary Tree from Preorder and Inorder Traversal. The next preorder value
     * is the root; its inorder position splits the left and right subtrees.
     *
     * <p>Time O(n). Space O(n).
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndex.put(inorder[i], i);
        }
        return build(preorder, new int[] {0}, 0, inorder.length - 1, inIndex);
    }

    private static TreeNode build(int[] pre, int[] next, int lo, int hi, Map<Integer, Integer> inIndex) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = new TreeNode(pre[next[0]++]);
        int mid = inIndex.get(root.val);
        root.left = build(pre, next, lo, mid - 1, inIndex);
        root.right = build(pre, next, mid + 1, hi, inIndex);
        return root;
    }

    /**
     * LC 124 Binary Tree Maximum Path Sum. Each call returns the best downward path from that
     * node (ignoring negative branches) and updates the best path that bends at it.
     *
     * <p>Time O(n). Space O(height).
     */
    public static int maxPathSum(TreeNode root) {
        int[] best = {Integer.MIN_VALUE};
        maxDown(root, best);
        return best[0];
    }

    private static int maxDown(TreeNode node, int[] best) {
        if (node == null) {
            return 0;
        }
        int l = Math.max(0, maxDown(node.left, best));
        int r = Math.max(0, maxDown(node.right, best));
        best[0] = Math.max(best[0], node.val + l + r);
        return node.val + Math.max(l, r);
    }

    /**
     * LC 212 Word Search II. Put the words in a trie and DFS from every cell, following the trie
     * so all words are searched at once. Found words are cleared so each is reported once.
     *
     * <p>Time O(cells * 4 * 3^(L - 1)) for word length L. Space O(total word length).
     */
    public static List<String> findWords(char[][] board, String[] words) {
        WordNode root = new WordNode();
        for (String w : words) {
            WordNode node = root;
            for (char c : w.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new WordNode();
                }
                node = node.next[c - 'a'];
            }
            node.word = w;
        }
        List<String> out = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                searchWords(board, r, c, root, out);
            }
        }
        return out;
    }

    private static final class WordNode {
        final WordNode[] next = new WordNode[26];
        String word;
    }

    private static void searchWords(char[][] board, int r, int c, WordNode parent, List<String> out) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == '#') {
            return;
        }
        char ch = board[r][c];
        WordNode node = parent.next[ch - 'a'];
        if (node == null) {
            return;
        }
        if (node.word != null) {
            out.add(node.word);
            node.word = null;
        }
        board[r][c] = '#'; // mark visited
        searchWords(board, r + 1, c, node, out);
        searchWords(board, r - 1, c, node, out);
        searchWords(board, r, c + 1, node, out);
        searchWords(board, r, c - 1, node, out);
        board[r][c] = ch;
    }
}
