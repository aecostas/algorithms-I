import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private final int trials;
    private static final double cTHRESHOLD = 1.96;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;

        double[] openSites;

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        openSites = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            boolean[] selected = new boolean[n*n];
            int countOpen = 0;

            for (int j = 0; j < n*n; j++) {
                selected[j] = false;
            }

            while (!p.percolates()) {
                int openCellIndex;
                
                if (n*n - countOpen == 1) {
                    openCellIndex = 1;
                } else {
                    openCellIndex = StdRandom.uniform(0, n*n - countOpen);  
                }
                  
                int count = -1;
                int j = 0;

                for (; j < n*n; j++) {
                   
                    if (!selected[j]) {
                        count++;
                    }

                    if (count == openCellIndex) {
                        break;
                    }

                }

                int row = (j / n) + 1;
                int col = (j % n) + 1;
                
                p.open(row, col);
                countOpen++;
                selected[j] = true;

            }
            openSites[i] = (double) p.numberOfOpenSites() / (n*n);

        }

        this.mean = StdStats.mean(openSites);
        this.stddev = StdStats.stddev(openSites);

    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (cTHRESHOLD * this.stddev())/Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (cTHRESHOLD * this.stddev())/Math.sqrt(this.trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats perStats = new PercolationStats(n, trials);

        StdOut.printf("mean\t\t\t\t = %f\n", perStats.mean());
        StdOut.printf("stddev\t\t\t\t = %f\n", perStats.stddev());
        StdOut.printf("95%% confidence interval\t\t = [%f, %f]\n", perStats.confidenceLo(), perStats.confidenceHi());

   }

}