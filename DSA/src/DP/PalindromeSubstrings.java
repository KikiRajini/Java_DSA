package DP;

public class PalindromeSubstrings {
    int count =0;
    public int countSubstrings(String s) {
        if(s.isEmpty()){return 0;}


        for(int i = 0; i<s.length(); i++){
            countPalindrome(s,i,i);
            countPalindrome(s,i,i+1);
        }

        return count;
    }

    private int countPalindrome(String s, int left, int right){
        while(left>=0 && right< s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
            count++;
        }
        return count;
    }
}
