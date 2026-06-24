package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideViewBFS {
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){return result;}
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            int size = bfs.size();
            for(int i =0 ; i<size; i++){
                TreeNode current = bfs.poll();
                if(i==size-1){result.add(current.val);}
                if(current.left!=null)bfs.add(current.left);
                if(current.right!=null)bfs.add(current.right);
            }
        }
        return result;
    }

    List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideViewBFS(TreeNode root) {
        dfs(root,0);
        return result;
    }

    public void dfs(TreeNode node, int depth){
        if(node==null){return;}
        if(result.size() == depth){result.add(node.val);}
        dfs(node.right,depth+1);
        dfs(node.left, depth+1);
    }
}
