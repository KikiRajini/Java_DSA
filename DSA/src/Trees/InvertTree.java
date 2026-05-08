package Trees;

public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root!=null){
            TreeNode right = root.right;
            root.right  = invertTree(root.left);
            root.left = invertTree(right);
        }
        return root;
    }
}
