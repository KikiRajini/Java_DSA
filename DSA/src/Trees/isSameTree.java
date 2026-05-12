package Trees;

public class isSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){return true;}
        else if((p==null && q!=null)||(q==null && p!=null)){return false;}
        else{
            if(p.val==q.val){
                boolean left = isSameTree(p.left,q.left);
                boolean right = isSameTree(p.right,q.right);
                return left&&right;
            }
            else{ return false;}
        }


    }
}
