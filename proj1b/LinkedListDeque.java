public class LinkedListDeque<T> implements Deque<T>{
    /** inner class Node */
    public class Node{
        private T item;
        private Node pre;
        private Node next;

        public Node(T n, Node pre_, Node next_){
            item = n;
            pre = pre_;
            next = next_;
        }
        public Node(Node pre_, Node next_){
            pre = pre_;
            next = next_;
        }
    }

    /** sentinel node */
    private Node sentinel;
    /** size of the deque */
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void addFirst(T item){
        Node newList = new Node(item, sentinel, sentinel);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size++;
    }

    @Override
    public void addLast(T item){
        Node newList = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size++;
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T rmv = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return rmv;
    }

    @Override
    public  T removeLast(){
        if(size == 0){
            return null;
        }
        T rmv = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size--;
        return rmv;
    }

    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        Node ptr = sentinel;
        // iterate to the index position
        for(int i = 0; i <= index; i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    private T getRecursiveHelper(Node start, int index){
        if(index == 0) {
            return start.item;
        } else{
            return getRecursiveHelper(start.next, index - 1);
        }
    }
    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    @Override
    public  void printDeque(){
        Node ptr = sentinel.next;
        while(ptr != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
}
