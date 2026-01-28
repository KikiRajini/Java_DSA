package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Parentheses {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                char open = stack.pop();
                if(map.get(open) != c) return false;
            }
        }
        return stack.isEmpty();
    }
}
