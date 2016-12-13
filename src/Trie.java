/**
 * Created by lenovo on 2016/11/24.
 */
/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    public static final int  SIZE=26;
    public Character value;
    public boolean isEnd;
    public TrieNode[] childNodeSet;

    public TrieNode(){
        this.value=null;
        this.isEnd=true;
        this.childNodeSet=null;
    }

}

public class Trie {
    private TrieNode root;


    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {

        if(word==null||word=="") return;
        char[] letters=word.toCharArray();
        TrieNode node=root;
        for(int i=0;i<letters.length;i++){
            int index=letters[i]-'a';
            if(node.childNodeSet==null) node.childNodeSet=new TrieNode[TrieNode.SIZE];
            if(node.childNodeSet[index]==null) node.childNodeSet[index]=new TrieNode();
            node.childNodeSet[index].value=letters[i];

            node=node.childNodeSet[index];
        }
        node.isEnd=true;

    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(word==null||word=="") return false;
        char[] letters=word.toCharArray();
        TrieNode node=root;
        for(int i=0;i<letters.length;i++){
            int index=letters[i]-'a';
            if(node.childNodeSet==null) return false;
            if(node.childNodeSet[index]==null) return false;
            if(node.childNodeSet[index].value!=letters[i]) return false;
            node=node.childNodeSet[index];
        }
        return node.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix==null||prefix=="") return false;
        char[] letters=prefix.toCharArray();
        TrieNode node=root;
        for(int i=0;i<letters.length;i++){
            int index=letters[i]-'a';
            if(node.childNodeSet==null) return false;
            if(node.childNodeSet[index]==null) return false;
            if(node.childNodeSet[index].value!=letters[i]) return false;
            node=node.childNodeSet[index];
        }
        return true;
    }
}
