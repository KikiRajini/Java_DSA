package greedy;

public class validParenthesis {
    public boolean checkValidString(String s) {

        // leftMin: The minimum possible open '(' assuming wildcards act as ')'.
        // leftMax: The maximum possible open '(' assuming wildcards act as '('.
        int leftMin = 0, leftMax = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // An explicit open bracket increases both our minimum and maximum bounds.
                leftMin++;
                leftMax++;
            } else if (c == ')') {
                // An explicit close bracket burns down one open bracket across both bounds.
                leftMin--;
                leftMax--;
            } else {
                // This block executes ONLY when c == '*' (Wildcard State)
                // 1. We aggressively assume '*' acts as ')' -> leftMin decreases.
                // 2. We aggressively assume '*' acts as '(' -> leftMax increases.
                // (Note: The state where '*' acts as an empty string "" naturally falls inside this range).
                leftMin--;
                leftMax++;
            }

            // CHECK 1: Have we seen too many closing ')' early on?
            // leftMax assumes every single '*' was an open '('. If it still drops below 0,
            // it means the raw count of ')' completely overwhelms all open possibilities.
            // This catches invalid sequences like "())" or ")(" instantly.
            if (leftMax < 0) {
                return false;
            }

            // CHECK 2: Did our assumption about the wildcards go too far?
            // If leftMin drops below 0, it means we assumed too many '*' turned into ')'.
            // We correct this by setting it to 0, which means those extra wildcards
            // should instead be treated as empty strings "" or open '('.
            if (leftMin < 0) {
                leftMin = 0;
            }
        }

        // If leftMin is exactly 0, it proves there is a valid branch where
        return leftMin == 0;
    }
}
