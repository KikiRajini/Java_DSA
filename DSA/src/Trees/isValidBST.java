package Trees;

public class isValidBST {
    public boolean isValidBST(TreeNode root) {
        // Start with no boundaries (null means infinity)
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer min, Integer max) {
        // An empty tree/branch is always a valid BST
        if (node == null) return true;

        // The current node's value must sit strictly within its inherited boundaries
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        // Move Left: max boundary becomes the current node's value
        // Move Right: min boundary becomes the current node's value
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}
