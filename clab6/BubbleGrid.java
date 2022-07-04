/** case1: The hit one is 0, it can connect nothing to the ceiling.
 *  case2: The hit one is 1, but it cannot connect to the top, then no one can connect to top through it.
 *  case3: The hit one is 1, it can connect to the ceiling, but the near ones can connect to the ceiling by other bubbles too.
 *          For this case, when the hit one is removed, its connected bubbles will not fall.
 *  case4: The hit one is 1, it can connect to the ceiling, and the near ones connect to the ceiling only through it.
 *          For this case, when the hit one is removed, some bubbles will fall.
 */
public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int colNum;
    private int ceiling = 0;
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        /* 0th element of uf will be the ceiling */
        UnionFind uf = new UnionFind(rowNum * colNum + 1);
        /* set the position where dart hits 2 */
        for(int[] dart : darts){
            if(grid[dart[0]][dart[1]] == 1){
                grid[dart[0]][dart[1]] = 2;
            }
        }
        /* Union the rest bubbles which will not be hit. */

        return null;
    }
}
