/** In the project, I treat the array as circular
 *  Namely, if first pointer is at position 0, and you addFirst
 *  the front pointer should loop back around to the end of the array.
 * */
public class ArrayDeque<T> {
    private T[] array;
    /** size of the deque */
    private int size;
    /** size of the array */
    private int capacity;
    /** front index */
    private  int front;
    /** last index */
    private  int last;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        front = 4;
        last = 4;
    }

    /** decide if the deque is empty
     * @return true if the deque is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private int minusOne(int index){
        if(index == 0){
            return capacity - 1;
        }
        return index - 1;
    }

    /** move index backwards
     * @param module: helping parameter
     */
    private  int plusOne(int index, int module){
        index %= module;
        if(index == module - 1){
            return 0;
        }
        return index + 1;
    }
    private  void grow(){
        T[] newArray = (T[]) new Object[capacity * 2];
        int ptr1 = front;
        int ptr2 = capacity;
        while(ptr1 != last){
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, capacity);
            ptr2 = plusOne(ptr2, capacity*2);
        }
        front = capacity;
        last = ptr2;
        array = newArray;
        capacity *= 2;
    }

    private void shrink(){
        T[] newArray = (T[]) new Object[capacity / 2];
        int ptr1 = front;
        int ptr2 = capacity / 4;
        while(ptr1 != last){
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, capacity);
            ptr2 = plusOne(ptr2, capacity / 2);
        }
        front = capacity / 4;
        last = ptr2;
        array = newArray;
        capacity /= 2;
    }

    /** add one item at the first of the deque */
    public void addFirst(T item){
        if(size == capacity - 1){
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    /** add one item at the end of the deque */
    public void addLast(T item){
        if(size == capacity - 1){
            grow();
        }
        array[last] = item;
        last = plusOne(last, capacity);
        size++;
    }

    /** remove the first item of the deque */
    public T removeFirst(){
        if(capacity >= 16 && capacity / size >= 4){
            shrink();
        }
        if(size == 0){
            return null;
        }
        T ret = array[front];
        front = plusOne(front, capacity);
        size--;
        return ret;
    }

    /** remove the last item of the deque */
    public T removeLast(){
        if(capacity >= 16 && capacity / size >= 4){
            shrink();
        }
        if(size == 0){
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }

    /** return the item at index */
    public T get(int index){
        if(index >= size){
            return null;
        }
        int ptr = front;
        for(int i = 0; i < index; i++){
            ptr = plusOne(ptr, capacity);
        }
        return array[ptr];
    }

    /** print the entire deque from front to end */
    public void printDeque(){
        int ptr = front;
        while(ptr != last){
            System.out.println(array[ptr] + " ");
            ptr = plusOne(ptr, capacity);
        }
    }
}
