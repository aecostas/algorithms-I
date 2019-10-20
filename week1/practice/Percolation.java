import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] openComponents;
    private final int n;
    private final int virtualTop;
    private final int virtualBottom;
    private int countOpen;
    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.virtualTop = n*n;
        this.virtualBottom = n*n + 1;
        this.countOpen = 0;

        uf = new WeightedQuickUnionUF(n*n + 2);

        openComponents = new boolean[n*n];

        for (int i = 0; i < n*n; i++) {
            openComponents[i] = false;
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkCoordinates(row, col);

        boolean isOnTop = row == 1;
        boolean isOnBottom = row == this.n;
        boolean isRight = col == this.n;
        boolean isLeft = col == 1;

        int currentComponent = this.getIndexFromCoords(row, col);

        if (!this.openComponents[currentComponent]) {
            this.countOpen++;
        }

        this.openComponents[currentComponent] = true;

        if (!isOnTop) {
            int neighbour = this.getIndexFromCoords(row - 1, col);

            if (this.openComponents[neighbour]) {
                this.uf.union(currentComponent, neighbour);
                this.openComponents[currentComponent] = true;
            }

        } else {
            this.uf.union(currentComponent, this.virtualTop);
        }

        if (!isOnBottom) {
            int neighbour = this.getIndexFromCoords(row + 1, col);

            if (this.openComponents[neighbour]) {
                this.uf.union(currentComponent, neighbour);
                this.openComponents[currentComponent] = true;
            }


        } else {
            this.uf.union(currentComponent, this.virtualBottom);

        }

        if (!isRight) {
            int neighbour = this.getIndexFromCoords(row, col + 1);

            if (this.openComponents[neighbour]) {
                this.uf.union(currentComponent, neighbour);
                this.openComponents[currentComponent] = true;
            }
        }

        if (!isLeft) {
            int neighbour = this.getIndexFromCoords(row, col - 1);

            if (this.openComponents[neighbour]) {
                this.uf.union(currentComponent, neighbour);
                this.openComponents[currentComponent] = true;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkCoordinates(row, col);

        int currentComponent = this.getIndexFromCoords(row, col);

        return this.openComponents[currentComponent];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkCoordinates(row, col);

        int currentComponent = this.getIndexFromCoords(row, col);

        return this.uf.connected(currentComponent, this.virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.uf.connected(this.virtualTop, this.virtualBottom);
    }

    private int getIndexFromCoords(int row, int col) {
        return (row - 1)*this.n + col - 1;
    }

    private void checkCoordinates(int row, int col) {
        if (row > this.n || row < 1 || col > this.n || col < 1) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);

  

        System.out.println(" ==== First iteration (1,1)");
        p.open(1, 1);
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
        

        System.out.println(" ==== Third iteration (3,1)");
        p.open(3, 1);
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());


        System.out.println(" ==== Second iteration (2,1)");
        p.open(2, 1);
        System.out.println(p.percolates());
        System.out.println(p.isFull(2,1));
        System.out.println(p.numberOfOpenSites());


    }
}