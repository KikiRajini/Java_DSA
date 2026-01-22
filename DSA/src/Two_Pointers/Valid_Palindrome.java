package Two_Pointers;

/*Given a string s, return true if it is a palindrome, otherwise return false.

A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "Was it a car or a cat I saw?"

Output: true
Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

Example 2:

Input: s = "tab a cat"

Output: false
Explanation: "tabacat" is not a palindrome.*/

public class Valid_Palindrome {

        public boolean isPalindrome(String s) {

            int left = 0;
            int right = s.length() - 1;

            while(left < right){

                while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                    left++;
                }

                while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                    right--;
                }

                // compare (case-insensitive)
                if(Character.toLowerCase(s.charAt(left)) !=
                        Character.toLowerCase(s.charAt(right))){
                    return false;
                }

                left++;
                right--;
            }
            return true;
        }


}
