package programAssign1;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;
import edu.princeton.cs.algs4.StdIn;
public class PercolationStats {
	   double[] pArr;
	   int size;
	   double u; //mean
	   double c; // deviation
	   double t; // sqrt T
	   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
	   {
		   size = T;
		   pArr= new double[T];
		   for(int i=0;i<T;i++){
			   MCSimulation mcs = new MCSimulation(N);
			   pArr[i] = mcs.simulation();
		   }
		   u = mean();
		   c = stddev();
		   t = Math.sqrt(T);
	   }
	   public double mean()                      // sample mean of percolation threshold
	   {
		   return StdStats.mean(pArr);
	   }
	   public double stddev()                    // sample standard deviation of percolation threshold
	   {
		   return StdStats.mean(pArr);
	   }
	   public double confidenceLo()              // low  endpoint of 95% confidence interval
	   {
		   //double c = Math.sqrt(stddev());
		   //double t = Math.sqrt(size);
		   double confLo = u-(1.96*c/t);
		   return confLo;
		   
	   }
	   public double confidenceHi()              // high endpoint of 95% confidence interval
	   {
		   //double u = mean();
		   //double c = Math.sqrt(stddev());
		   //double t = Math.sqrt(size);
		   double confHi = u+(1.96*c/t);
		   return confHi;
		   
	   }
	   public static void main(String[] args)    // test client (described below)
	   {
//		   PercolationStats ps = new PercolationStats(2, 100000);
//		   ps.mean();
//		   ps.stddev();
//		   System.out.println("mean      "+ps.mean());
//		   System.out.println("stddev    "+ps.stddev());
//		   System.out.println("95% confidence interval=  "+ps.confidenceLo()+
//				   ", "+ps.confidenceHi());
		   int N = StdIn.readInt();
		   int T = StdIn.readInt();
		   PercolationStats ps = new PercolationStats(N, T);
		   ps.mean();
		   ps.stddev();
		   System.out.println("mean      "+ps.mean());
		   System.out.println("stddev    "+ps.stddev());
		   System.out.println("95% confidence interval=  "+ps.confidenceLo()+
				   ", "+ps.confidenceHi()); 
	   }
}