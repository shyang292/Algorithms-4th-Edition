package programAssign1;
import java.text.DecimalFormat;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class MCSimulation {
	//private boolean[][] blockArr;
	private int size;
	Percolation pc;
	private int count=0;
	public MCSimulation(int N){
		pc = new Percolation(N);
		size = N;
	}
	public float simulation(){
		float p=0;
		int i;
		int j;
		while(!pc.percolates()){
			//random i, j   [1,N]
			i = StdRandom.uniform(1, size+1);
			j = StdRandom.uniform(1, size+1);
			while(pc.isOpen(i, j)){
				i = StdRandom.uniform(1, size+1);
				j = StdRandom.uniform(1, size+1);
			}
			//System.out.println("i= "+i+"  j= "+j);
			//i,j not open
			//open (i,j)
			pc.open(i, j);
			count++;
			//percolates
		}
		p = (float)count/(size*size);	
	    return p;
	}
	public static void main(String[] args){
		MCSimulation mcs = new MCSimulation(4);
		float p = mcs.simulation();
		System.out.println(" "+p);
	}
}
