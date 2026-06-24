package tries;

public class WordDictionary {

        Trie root;
        public WordDictionary() {
            root = new Trie();;

        }

        public void addWord(String word) {
            Trie curr = root;
            for(char c : word.toCharArray()){
                if(curr.children[c-'a']==null){curr.children[c-'a']= new Trie();}
                curr = curr.children[c - 'a'];
            }
            curr.word=true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int j, Trie root){
            Trie curr = root;
            for (int i = j; i < word.length(); i++) {
                char c = word.charAt(i);
                if(c=='.'){
                    for(Trie child : curr.children){
                        if(child !=null && dfs (word,i+1,child)){
                            return true;
                        }
                    }
                    return false;
                }
                else{
                    if(curr.children[c-'a']==null){return false;}
                    curr = curr.children[c-'a'];
                }
            }
            return curr.word;

        }


    }



class Trie{
    Trie[] children = new Trie[26];
    boolean word = false;
}
