package dsa.trees;

import dsa.common.TreeNode;

/**
 * LC 297 Serialize and Deserialize Binary Tree. Preorder with "#" for missing children is enough
 * to rebuild the exact shape: read values back in the same order.
 *
 * <p>Both directions O(n).
 */
public class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        write(root, sb);
        return sb.toString();
    }

    private void write(TreeNode node, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(',');
        }
        if (node == null) {
            sb.append('#');
            return;
        }
        sb.append(node.val);
        write(node.left, sb);
        write(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        return read(data.split(","), new int[] {0});
    }

    private TreeNode read(String[] tokens, int[] next) {
        String t = tokens[next[0]++];
        if (t.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(t));
        node.left = read(tokens, next);
        node.right = read(tokens, next);
        return node;
    }
}
