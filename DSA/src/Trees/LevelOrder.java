package Trees;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder {
    List<List<Integer>> result = new ArrayList<>();
    int level =-1;
    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root!=null){
            levelOrderArray(level +1,root);

        }
        return result;
    }

    private void levelOrderArray(int level, TreeNode root){
        if(root!=null){
            if(result.size()<=level){result.add(new ArrayList<>());}
            result.get(level).add(root.val);
            levelOrderArray(level+1,root.left);
            levelOrderArray(level+1,root.right);
        }
    }
}
