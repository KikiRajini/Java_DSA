package Arrays_And_Hashing;

import java.util.ArrayList;
import java.util.List;

//Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

public class Encode_and_Decode {


        public String encode(List<String> strs) {
            StringBuilder encodedSB = new StringBuilder();
            for(String str: strs){
                encodedSB.append(str.length()).append("#").append(str);
            }
            return encodedSB.toString();
        }

        public List<String> decode(String str) {
            List<String> decodedStrs = new ArrayList<>();
            int index = 0;
            while(index<str.length()){
                int numIndex = index;
                while(str.charAt(numIndex)!='#'){
                    numIndex++;
                }
                int len = Integer.parseInt(str.substring(index, numIndex));
                String word = str.substring(numIndex+1, numIndex+1+len);
                decodedStrs.add(word);

                index = numIndex + 1 + len;
            }
            return decodedStrs;

        }


}

//# to be inclusive of all lengths, they can be 2 digit, three or more.
