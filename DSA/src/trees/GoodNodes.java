package trees;

public class GoodNodes {
    int result =0;

    public int goodNodes(TreeNode root) {
        if(root==null){return 0;}
        dfs(root,root.val);
        return result;
    }

    public void dfs(TreeNode root, int max){
        if(root!=null){
            if(root.val>=max){result++;}
            max = Math.max(root.val,max);
            dfs(root.left,max);
            dfs(root.right,max);
        }
    }
}
