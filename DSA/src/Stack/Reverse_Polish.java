package Stack;

import Two_Pointers.Three_Sum;

import java.util.*;

public class Reverse_Polish {


        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new ArrayDeque<>();

            for(String s : tokens){
                if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                    int b = stack.pop();
                    int a = stack.pop();

                    if(s.equals("+")) stack.push(a+b);
                    else if(s.equals("-")) stack.push(a-b);
                    else if(s.equals("*")) stack.push(a*b);
                    else stack.push(a/b);

                } else {
                    stack.push(Integer.parseInt(s));
                }
            }
            return stack.pop();
        }


    public static void main(String[] args) {
        Reverse_Polish sol = new Reverse_Polish();

        String[] tokens= {"2","1","+","3","*"};

        int result = sol.evalRPN(tokens);

        System.out.println(result);
    }

}
