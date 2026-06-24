package tries;


public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean endOfWord = false;
}