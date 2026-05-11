package Trees;

public class DiameterOfBinaryTree {
    int maxD =0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){return 0;}
        maxD = Math.max(maxDepth(root.left)+maxDepth(root.right), maxD);
        diameterOfBinaryTree(root.right);
        diameterOfBinaryTree(root.left);
        return maxD;

    }

    public int maxDepth(TreeNode node){
        if(node == null){return 0;}
        return Math.max(maxDepth(node.left)+1,maxDepth(node.right)+1);
    }
}
