import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size){
            this.key = key;
            this.value = val;
            this.size = size;
        }
    }
    private Node root;

    public BSTMap(){}

    /** Removes all the mappings from the map */
    @Override
    public void clear(){
        root = null;
    }

    /** Returns true if this map contains a mapping for the key */
    @Override
    public boolean  containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped,
     *  or null if this map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return getHelper(root, key);
    }

    private V getHelper(Node r, K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(r == null){
            return null;
        }
        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            return getHelper(r.left, key);
        } else if(cmp > 0){
            return getHelper(r.right, key);
        } else{
            return r.value;
        }
    }

    /** Returns the number of key-value mapping in the map */
    @Override
    public int size(){
        return sizeOf(root);
    }

    private int sizeOf(Node r){
        if(r == null){
            return 0;
        } else{
            return r.size;
        }
    }

    /** Connect the value with the key in the map */
    @Override
    public void put(K key, V value){
        if(key == null){
            throw new IllegalArgumentException();
        }
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node r, K key, V value){
        if(r == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            r.left = putHelper(r.left, key, value);
        } else if(cmp > 0){
            r.right = putHelper(r.right, key, value);
        } else{
            r.value = value;
        }
        r.size = 1 + sizeOf(r.left) + sizeOf(r.right);
        return r;
    }

    /** Print the BSTMap pairs in increasing order of key */
    public void printInOrder(){
        for(int i = 0; i < size(); i++){
            System.out.println(choose(i).key + " " + choose(i).value);
        }
    }

    private Node choose(int k){
        if(k < 0 || k >= size()){
            throw new IllegalArgumentException();
        }
        return choose(root, k);
    }

    private Node choose(Node r, int k){
        if(r == null){
            return null;
        }
        int t = sizeOf(r.left);
        if(t > k){
            return choose(r.left, k);
        } else if(t < k){
            return choose(r.right, k - t - 1);
        } else{
            return r;
        }
    }

    /** Returns a Set view of the keys contained in the map */
    @Override
    public Set<K> keySet(){
        Set<K> BSTSet = new HashSet<>();
        for(int i = 0; i < size(); i++){
            BSTSet.add(choose(i).key);
        }
        return BSTSet;
    }

    /** Removes the mapping for the specified dey from this map if present */
    @Override
    public V remove(K key){
        if(!containsKey(key)){
            return null;
        }
        V toRemove = get(key);
        root = removeHelper(root, key);
        return toRemove;
    }

    @Override
    public V remove(K key, V value){
        if(!containsKey(key)){
            return null;
        }
        if(!get(key).equals(value)){
            return null;
        }
        root = removeHelper(root, key);
        return value;
    }

    private Node removeHelper(Node r, K key){
        if(r == null){
            return null;
        }
        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            r.left = removeHelper(r.left, key);
        } else if(cmp > 0){
            r.right = removeHelper(r.right, key);
        } else{
            if(r.left == null)
                return r.right;
            if(r.right == null)
                return r.left;
            Node temp = r;
            r = min(temp.right);
            r.right = deleteMin(temp.right);
            r.left = temp.left;
        }
        r.size = sizeOf(r.left) + sizeOf(r.right) + 1;
        return r;
    }

    private Node min(Node r){
        // the minimum number occurs at the left most leaf
        if(r.left == null){
            return r;
        }
        return min(r.left);
    }

    private Node deleteMin(Node r){
        if(r.left == null){
            // it means that r is the minimum value,
            // so we only need to replace r with r.right
            return r.right;
        }
        r.left = deleteMin(r.left);
        r.size = sizeOf(r.left) + sizeOf(r.right) + 1;
        return r;
    }

    /** Iterator of the BSTMap */
    public Iterator<K> iterator(){
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K>{
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node src){
            while(src != null){
                stack.push(src);
                src = src.left;
            }
        }

        @Override
        public boolean hasNext(){
            return !stack.isEmpty();
        }

        @Override
        public K next(){
            Node curr = stack.pop();

            if(curr.right != null){
                Node temp = curr.right;
                while(temp != null){
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }
}
