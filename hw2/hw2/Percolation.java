package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[][] grid;
    private int open_num = 0;

    private WeightedQuickUnionUF uf;
    private int uf_bottom;
    private WeightedQuickUnionUF ufWithoutBottom;

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf_bottom = N * N + 1;
        ufWithoutBottom = new WeightedQuickUnionUF((N * N + 1));
    }

    /** judge whether the index is valid */
    private void validate(int row, int col){
        if(row < 0 || row >= N || col < 0 || col >= N){
            throw new IndexOutOfBoundsException();
        }
    }

    /** return the ufIndex of site(row, col) */
    private int ufIndex(int row, int col){
        return row * N + col + 1;
    }

    /** connect the newly opened box with its neighbors */
    private  void unionAround(int row, int col){
        for(int[] dir : directions){
            int x = row + dir[0];
            int y = col + dir[1];
            if(x >= 0 && x < N && y >= 0 && y < N && isOpen(x, y)){
                uf.union(ufIndex(row, col), ufIndex(x, y));
                ufWithoutBottom.union(ufIndex(row, col), ufIndex(x, y));
            }
        }
    }

    /** open the site(row, col) if it is not open already */
    public void open(int row, int col){
        validate(row, col);
        if(!isOpen(row, col)){
            grid[row][col] = 1;
            unionAround(row, col);
            open_num++;
        }
    }

    /** is the site(row, col) open? */
    public boolean isOpen(int row, int col){
        validate(row, col);
        return grid[row][col] == 1;
    }

    /** is the site(row, col) full? */
    public boolean isFull(int row, int col){
        validate(row, col);
        return ufWithoutBottom.connected(0, ufIndex(row, col));
    }

    /** number of open sites */
    public int numberOfOpenSites(){
        return open_num;
    }

    /** does the system percolate? */
    public boolean percolates(){
        return uf.connected(0, uf_bottom);
    }

    /** use for unit testing */
    public static void main(String[] args){

    }
}
