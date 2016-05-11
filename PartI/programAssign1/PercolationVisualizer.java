package programAssign1;
/* *****************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size N of the percolation system.
 *    - Creates an N-by-N grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row i, column j) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (1, 1) in the upper left-hand corner.
 *
 ***************************************************************************** */

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class PercolationVisualizer {

    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 100;

    // draw N-by-N percolation system
    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-.05*N, 1.05*N);
        StdDraw.setYscale(-.05*N, 1.05*N);   // leave a border to write text
        StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25*N, -N*.025, opened + " open sites");
        if (perc.percolates()) StdDraw.text(.75*N, -N*.025, "percolates");
        else                   StdDraw.text(.75*N, -N*.025, "does not percolate");

    }

    public static void main(String[] args) throws IOException {
    	StdDraw.show(0);
    	BufferedReader br = new BufferedReader(new FileReader("D:\\Program Files\\Workspace\\Algorithms4thEdition\\src\\programAssign1\\jerry47.txt"));
    	String curLine;
    	int count = 0;
    	int size = 0,i,j;   //size=N,    row i, column j
    	Percolation perc = null;
    	while ((curLine = br.readLine()) != null) {
    		if(count == 0){
    			size = Integer.parseInt(curLine);
    			perc = new Percolation(size);
    			draw(perc, size);
    			StdDraw.show(DELAY);
    			count++;
    			continue;
    		}
			//count++;
    		curLine = curLine.trim();
			String[] strArr = curLine.split("\\D+");
			i = Integer.parseInt(strArr[0]);
			j = Integer.parseInt(strArr[1]);
			perc.open(i, j);
			draw(perc, size);
			StdDraw.show(DELAY);
		}
    	
    	//        In in = new In(args[0]);      // input file
//        int N = in.readInt();         // N-by-N percolation system
//
//        // turn on animation mode
//        StdDraw.show(0);
//
//        // repeatedly read in sites to open and draw resulting system
//        Percolation perc = new Percolation(N);
//        draw(perc, N);
//        StdDraw.show(DELAY);
//        while (!in.isEmpty()) {
//            int i = in.readInt();
//            int j = in.readInt();
//            perc.open(i, j);
//            draw(perc, N);
//            StdDraw.show(DELAY);
//        }
    }
}
