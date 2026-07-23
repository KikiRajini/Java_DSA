package backTracking;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        buildParenthesis(new StringBuilder(),n,n,result);
        return result;
    }

    private void buildParenthesis(StringBuilder current, int leftOpen, int rightClose, List<String> result){
        if(leftOpen==0 && rightClose==0){result.add(current.toString()); return;}

        if(leftOpen>0){
            current.append("(");
            buildParenthesis(current,leftOpen-1,rightClose,result);
            current.deleteCharAt(current.length()-1);
        }
        if(rightClose>leftOpen){
            current.append(")");
            buildParenthesis(current,leftOpen,rightClose-1,result);
            current.deleteCharAt(current.length()-1);
        }
    }
}
