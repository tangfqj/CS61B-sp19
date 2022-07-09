import java.util.*;

import static java.util.Objects.hash;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int initialCapacity;
    private double loadFactor;
    private int threshold;
    private int size = 0;
    private BucketEntity<K, V>[] hashTable;

    private static class BucketEntity<K, V>{
        private K key;
        private V value;
        private BucketEntity<K, V> next;

        private BucketEntity(K k, V v){
            key = k;
            value = v;
            next = null;
        }

        private BucketEntity(K k, V v, BucketEntity<K, V> n){
            key = k;
            value = v;
            next = n;
        }

        private BucketEntity<K, V> find(K key){
            return findHelper(key, this);
        }

        private BucketEntity<K, V> findHelper(K key, BucketEntity<K, V> buck){
            if(buck == null){
                return null;
            }
            if(buck.key.equals(key)){
                return buck;
            }
            return findHelper(key, buck.next);
        }

        /** Return true if key hasn't been added, else return false */
        private boolean put(K key, V value){
            return putHelper(key, value, this);
        }
        private boolean putHelper(K key, V value, BucketEntity<K, V> buck){
            if(buck.key.equals(key)){
                buck.value = value;     // update the corresponding value
                return false;
            }
            if(buck.next == null){
                buck.next = new BucketEntity<>(key, value);
                return true;
            }
            return putHelper(key, value, buck.next);
        }

        /** Add a BucketEntity at end */
        private void add(BucketEntity<K, V> element){
            BucketEntity<K, V> temp = this;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = element;
        }
    }

    /** Constructors */
    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, double loadFactor){
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.threshold = (int) (initialCapacity * loadFactor);
        hashTable = new BucketEntity[initialCapacity];
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        hashTable = new BucketEntity[hashTable.length];
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        int keyHash = hash(key);
        if(hashTable[keyHash] == null){
            return false;
        }
        return hashTable[keyHash].find(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(key == null){
            throw  new IllegalArgumentException();
        }
        int keyHash = hash(key);
        if(hashTable[keyHash] == null){
            return null;
        }
        BucketEntity<K, V> buck = hashTable[keyHash].find(key);
        return buck == null ? null : buck.value;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(value == null){
            remove(key);
        }
        if(size + 1 > threshold){
            resize();
        }

        int keyHash = hash(key);
        if(hashTable[keyHash] == null){
            hashTable[keyHash] = new BucketEntity<>(key, value);
            size++;
        }
        else{
            boolean keyIsNew = hashTable[keyHash].put(key, value);
            if(keyIsNew)
                size++;
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        HashSet<K> keySet = new HashSet<>();
        for(BucketEntity<K, V> buck : hashTable){
            while(buck != null){
                keySet.add(buck.key);
                buck = buck.next;
            }
        }
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        // Not implemented yet
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    private int hash(K key){
        return hash(key, hashTable.length);
    }

    private int hash(K key, int capacity){
        return (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    private void resize(){
        BucketEntity<K, V>[] newHashTable = new BucketEntity[initialCapacity * 2];
        threshold = (int) (newHashTable.length * loadFactor);

        for(BucketEntity<K, V> buck : hashTable){
            if(buck == null){
                continue;
            }
            int newHash = hash(buck.key, newHashTable.length);

            if(newHashTable[newHash] == null){
                newHashTable[newHash] = buck;
            } else{
                newHashTable[newHash].add(buck);
            }
        }
    }

    @Override
    public Iterator<K> iterator(){
        return keySet().iterator();
    }
}
