package trees;

public class BalancedTree {
    public boolean isBalanced(TreeNode root) {
        if (root==null){ return true;}
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        if(Math.abs(left-right)>1){
            return false;
        }
        boolean leftb = isBalanced(root.left);
        boolean rightb = isBalanced(root.right);
        return leftb&&rightb;


    }

    public int maxHeight(TreeNode root){
        if(root==null) return 0;
        return Math.max(maxHeight(root.left),maxHeight(root.right))+1;
    }
}
