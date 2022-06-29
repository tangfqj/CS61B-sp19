import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void testSizeOf(){
        UnionFind uf = new UnionFind(8);
        uf.union(0, 2);
        uf.union(2, 3);
        uf.union(1, 4);
        uf.union(3, 5);
        int exp1 = 4;
        assertEquals(exp1, uf.sizeOf(5));
        int exp2 = 2;
        assertEquals(exp2, uf.sizeOf(4));
    }

    @Test
    public void testConnected(){
        UnionFind uf = new UnionFind(4);
        uf.union(0, 3);
        uf.union(1, 2);
        assertTrue(uf.connected(1, 2));
        assertFalse(uf.connected(1, 3));
    }

    @Test
    public void testFind(){
        UnionFind uf = new UnionFind(8);
        uf.union(0, 2);
        uf.union(2, 3);
        uf.union(1, 4);
        uf.union(3, 5);
        int exp1 = 2;
        assertEquals(exp1, uf.find(5));
        uf.union(4, 7);
        int exp2 = 4;
        assertEquals(exp2, uf.find(7));
    }
}
