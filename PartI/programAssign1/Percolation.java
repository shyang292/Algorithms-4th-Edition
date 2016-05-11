package programAssign1;
import java.text.DecimalFormat;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	   WeightedQuickUnionUF wqu;
	   private int[] id;//
	   private boolean[][] blockArr;
	   private int size;
		// create N-by-N grid, with all sites blocked
	   public Percolation(int N){
		   size = N;
		   wqu = new WeightedQuickUnionUF(N*N+2);
		   id = new int[N*N+2];
		   blockArr = new boolean[N+1][N+1];
		   for(int i=0;i<N*N+2;i++){
			   id[i]=i;
		   }
		   for(int i=1;i<N+1;i++)
			   for(int j=1;j<N+1;j++){
				   blockArr[i][j]=false;//all sites are blocked
			   }
	   }      
	   
	   // open site (row i, column j) if it is not open already
	   public void open(int i, int j){
		   if(blockArr[i][j]==false)
			   blockArr[i][j]=true;
		   //connect it to all its adjacent sites, 4 calls union
		   int n = (i-1)*size+j;
		   if(i==1){// top sites
			   wqu.union(0, n);
		   }
		   if(i==size){//bottom sites
			   wqu.union(size*size+1, n);
		   }
		   if(j>=2 && blockArr[i][j-1]==true)//left site
			   wqu.union(n,n-1);
		   if(j<=size-1 && blockArr[i][j+1]==true)//right site
		   {
			   wqu.union(n,n+1);
			   //System.out.println("union(n,n+1) "+n+" "+(n+1));
		   }
		   if(i>=2 && blockArr[i-1][j]==true)//upper site
		   {
			   //System.out.println("upper site");
			   wqu.union(n,n-size);
			   //System.out.println("union(n,n-size) "+n+" "+(n-size));
		   }
		   if(i<size && blockArr[i+1][j]==true)//lower site
			   wqu.union(n,n+size);
	   }         
	   
	   // is site (row i, column j) open?
	   public boolean isOpen(int i, int j){
		  return blockArr[i][j];
	   }     
	   
	   // is site (row i, column j) full?
	   public boolean isFull(int i, int j){
		   //connect with virtual site 0
		   if(wqu.connected(0, (i-1)*size+j))
			   return true;
		   return false;
	   }     
	   // does the system percolate?
	   public boolean percolates()  {
		   if(wqu.connected(0, size*size+1))
			   return true;
		   return false;
	   }          
	
	   // test client (optional)
	   public static void main(String[] args){
		   Percolation pc = new Percolation(4);
		   pc.open(1, 1);
		   pc.open(2, 1);
		   if(pc.percolates())
			   System.out.println("2222 is full");
		   pc.open(3, 1);
		   pc.open(4, 1);
		   //pc.open(2, 2);
		   //pc.open(1, 2);
		   if(pc.percolates())
			   System.out.println("444444 111is full");
//		   double num= (double)2/3;   
//		   DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
//		   //String s = df.format(num);//返回的是String类型 	
//		   System.out.println(" "+num);
		   }
}