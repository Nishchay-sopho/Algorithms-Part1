
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private boolean[][] opened;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF unionFind2;
    private int len;
	private int numberOfOpenSites=0;
    
    public Percolation(int N)
    {
        if (N <= 0) {
            throw new IllegalArgumentException("Given N <= 0");
        }
        opened = new boolean[N][N];
        unionFind     = new WeightedQuickUnionUF(N*N + 2);
        unionFind2    = new WeightedQuickUnionUF(N*N + 1); 
        top    = N*N;
        bottom = N*N + 1;
        len    = N;
    }
    
    public void open(int i, int j)
    {
        if (checkIndex(i, j)) {
			numberOfOpenSites++;
            opened[i-1][j-1] = true;
            if (i == 1) {
                unionFind.union(j-1, top); 
                unionFind2.union(j-1, top);
            }
            if (i == len) unionFind.union((i-1)*len+j-1, bottom);
            if (i > 1   && isOpen(i-1, j)) {
                unionFind.union((i-1)*len+j-1, (i-2)*len+j-1);
                unionFind2.union((i-1)*len+j-1, (i-2)*len+j-1);
            }
            if (i < len && isOpen(i+1, j)) {
                unionFind.union((i-1)*len+j-1, i*len+j-1);
                unionFind2.union((i-1)*len+j-1, i*len+j-1);
            }
            if (j > 1   && isOpen(i, j-1)) {
                unionFind.union((i-1)*len+j-1, (i-1)*len+j-2);
                unionFind2.union((i-1)*len+j-1, (i-1)*len+j-2);
            }
            if (j < len && isOpen(i, j+1)) {
                unionFind.union((i-1)*len+j-1, (i-1)*len+j);
                unionFind2.union((i-1)*len+j-1, (i-1)*len+j);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public boolean isOpen(int i, int j)
    {
        if (checkIndex(i, j)) {
            return opened[i-1][j-1];
        }
        throw new IndexOutOfBoundsException();
    }
    
    public boolean isFull(int i, int j)
    {
        if (checkIndex(i, j)) {
            return unionFind2.connected((i-1)*len+j-1, top);
        }
        throw new IndexOutOfBoundsException();
    }
    
    public boolean percolates()
    {
        return unionFind.connected(top, bottom);
    }
    
    private boolean checkIndex(int i, int j)
    {
        if (i < 1 || i > len || j < 1 || j > len) return false;
        return true;
    }
	
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
}