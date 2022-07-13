import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B{
    private static class Node{
        boolean isKey;
        Map<Character, Node> map;

        Node(boolean isKey){
            this.isKey = isKey;
            this.map = new HashMap<>();
        }
    }

    private Node root;

    public MyTrieSet(){
        root = new Node(true);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root = new Node(true);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        Node endNode = getEnd(key);
        if(endNode == null){
            return false;
        }
        return endNode.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix){
        if(prefix == null){
            throw new IllegalArgumentException();
        }

        Node startNode = getEnd(prefix);
        List<String> keys = new ArrayList<>();
        if(startNode == null){
            return keys;
        }
        collectAllKeys(prefix, startNode, keys);
        return keys;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(!curr.map.containsKey(c)){
                return key.substring(0, i);
            }
            curr = curr.map.get(c);
        }
        return key;
    }

    /** Return the ending Node of the given String */
    private Node getEnd(String key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(!curr.map.containsKey(c)){
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    private void collectAllKeys(String s, Node n, List<String> keys){
        if(n.isKey){
            keys.add(s);
        }
        for(char c : n.map.keySet()){
            collectAllKeys(s + c, n.map.get(c), keys);
        }
    }
}
