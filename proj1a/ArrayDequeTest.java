import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public  void testEmpty(){
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        assertEquals(true, dq.isEmpty());
    }

    @Test
    public void testUtils(){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addLast(3);
        dq.addFirst(4);
        dq.addLast(7);
        /* [4, 3, 7] */
        /** Test size() */
        assertEquals(3, dq.size());
        /** Test printDeque() */
        dq.printDeque();
        /** Test removeFirst */
        int first = dq.removeFirst();
        assertEquals(4, first);
        /** Test removeLast */
        int last = dq.removeLast();
        assertEquals(7, last);
    }
}
