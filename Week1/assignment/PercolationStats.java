import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class PercolationStats {
    private double[] x;
    private int experiments;
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T)
    {
        experiments = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }

        x = new double[experiments];
        for (int t = 0; t < experiments; t++)
        {
            int numOfOpens = 0;
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates())
            {
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
                if (!percolation.isOpen(i, j) && !percolation.isFull(i, j)) {
                    percolation.open(i, j);
                    numOfOpens++;
                }
            }
            x[t] = (double) numOfOpens/(N*N);
        }  
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(x);
    }
    
    // sample standard deviation of percolation threshold    
    public double stddev()
    {
        return StdStats.stddev(x);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo()
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experiments));       
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi()
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experiments));       
    }
    
    public static void main(String[] args)
    {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        
        String confidence = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + confidence);       
    }
}