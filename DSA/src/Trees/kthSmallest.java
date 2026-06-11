package Trees;

import java.util.Stack;

public class kthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        int count =k;
        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            count--;
            if(count ==0){return root.val;}
            root = root.right;


        }
        return -1;
    }
}
