package programAssign4;

import java.util.Stack;



public class Board implements Comparable<Board>{
	private int[][] blocks;
	private int N = 0;// 
	Board previous = null;//the previous board in pq
	int steps = 0; //  record the number of moves from initial board to the current board
	public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
     // (where blocks[i][j] = block in row i, column j)
	{
		this.blocks = blocks;
		this.N = blocks.length;
	}
	public int dimension()                 // board dimension N
	{
		return N;
	}
	public int hamming()                   // number of blocks out of place
	{	
		int count =0;
		int temp =0;
		int size = blocks.length;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++){
				temp = i*size+j+1;
				if(blocks[i][j]!=0 && blocks[i][j] != temp){
					count++;
				}
			}
		return count;
	}
	public int manhattan()                 // sum of Manhattan distances between blocks and goal
	{
		int sum =0;
		int temp =0;
		int size = blocks.length;
		int[][] idealblocks = new int[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++){
				if(blocks[i][j]!=0){
					//temp = i*size+j+1;
					int tmpi = (blocks[i][j]-1) / size; //row
					int tmpj = (blocks[i][j]-1) % size; //coloum
					temp = Math.abs(tmpi*size+tmpj-i*size-j);
					sum+= temp;
				}
			}
		
		return sum;
	}
	
	boolean isGoal()                // is this board the goal board?
	{
		if(manhattan()==0)
			return true;
		return false;
	}
	public Board twin()                    // a board that is obtained by exchanging any pair of blocks
	{
		int[][] twinblocks = new int[N][N];
		for(int i=0;i<N;i++){
			twinblocks[i] = blocks[i].clone();
		}
		int tmpi=-1,tmpj=-1;
		//swap the first two blocks
		boolean flag = true;
		for(int i=0;i<N && flag;i++)
			for(int j=0;j<N && flag;j++){
				if(twinblocks[i][j]!=0 && tmpi<0){
					tmpi = i;
					tmpj = j;
				}else if(twinblocks[i][j]!=0 && tmpi>=0){//the first block has been found; readdy to swap
					//tmpi tmpj i j
					twinblocks=swap(twinblocks,i,j,tmpi,tmpj);
					flag = false;
				}
			}
		Board b = new Board(twinblocks);
		return b;
	}
	public boolean equals(Object y)        // does this board equal y?
	{
		if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if(this.blocks.length!=that.blocks.length) return false;
        for(int i=0;i<this.blocks.length;i++)
        	for(int j=0;j<this.blocks[i].length;j++){
        		int k = this.blocks[i][j];
        		int m = that.blocks[i][j];
        		while(this.blocks[i][j]!=that.blocks[i][j])
        			return false;
        	}
        return true;
	}
	public Iterable<Board> neighbors()     // all neighboring boards
	{
		Stack<Board> s = new Stack<Board>();
		//this.blocks;
		int tempi=0,tempj=0;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				if(this.blocks[i][j] == 0) {
					tempi = i;
					tempj = j;
					break;
				}		
			}
		int[][] upblocks = new int[N][N];
		int[][] downblocks = new int[N][N];
		int[][] leftblocks = new int[N][N];
		int[][] rightblocks = new int[N][N];
		//use clone() to copy array blocks; clone() only use in one dimentional array;
		//if used in two dimentional array, clone() in each dimention
		for(int i=0;i<blocks.length;i++){
			upblocks[i] = blocks[i].clone();
			downblocks[i] = blocks[i].clone();
			leftblocks[i] = blocks[i].clone();
			rightblocks[i] = blocks[i].clone();
		}
		
		//one move; four direction
		if(tempi-1>=0){//up move
			/*int[][] upblocks = blocks.clone();
			int temp = upblocks[tempi][tempj];
			upblocks[tempi][tempj] = upblocks[tempi-1][tempj];
			upblocks[tempi-1][tempj] = temp;*/
			//upblocks = swap(upblocks, tempi, tempj, tempi-1, tempj);
			//Board b = new Board(upblocks);
			//s.push(b);
			//sysprint(new Board(swap(upblocks, tempi, tempj, tempi-1, tempj)).toString());
			Board tmpboard = new Board(swap(upblocks, tempi, tempj, tempi-1, tempj));
			tmpboard.steps = this.steps+1;
			s.push(tmpboard);
		}
		if(tempi+1<this.blocks.length){//lower move
			/*int[][] downblocks = this.blocks.clone();
			int temp = downblocks[tempi][tempj];
			downblocks[tempi][tempj] = downblocks[tempi-1][tempj];
			downblocks[tempi-1][tempj] = temp;
			//downblocks = swap(downblocks, tempi, tempj, tempi+1, tempj);
			Board b = new Board(downblocks);
			s.push(b);*/
			//sysprint("downblocks  "+new Board(downblocks));
			//sysprint(new Board(swap(downblocks, tempi, tempj, tempi+1, tempj)).toString());
			Board tmpboard = new Board(swap(downblocks, tempi, tempj, tempi+1, tempj));
			tmpboard.steps = this.steps+1;
			s.push(tmpboard);
		}
		if(tempj-1>=0){//left move
		/*	int[][] leftblocks = this.blocks.clone();
			int temp = leftblocks[tempi][tempj];
			leftblocks[tempi][tempj] = leftblocks[tempi-1][tempj];
			leftblocks[tempi-1][tempj] = temp;
			Board b = new Board(leftblocks);
			s.push(b);*/
			//sysprint(new Board(swap(leftblocks, tempi, tempj, tempi, tempj-1)).toString());
			Board tmpboard = new Board(swap(leftblocks, tempi, tempj, tempi, tempj-1));
			tmpboard.steps = this.steps+1;
			s.push(tmpboard);
		}
		if(tempj+1<this.blocks.length){//right move
/*			int[][] rightblocks = this.blocks.clone();
			int temp = rightblocks[tempi][tempj];
			rightblocks[tempi][tempj] = rightblocks[tempi-1][tempj];
			rightblocks[tempi-1][tempj] = temp;
			Board b = new Board(rightblocks);
			s.push(b);*/
			//sysprint(new Board(swap(rightblocks, tempi, tempj, tempi, tempj+1)).toString());
			Board tmpboard = new Board(swap(rightblocks, tempi, tempj, tempi, tempj+1));
			tmpboard.steps = this.steps+1;
			s.push(tmpboard);
		}
		return s;
	}
	private int[][] swap(int[][] arr, int i, int j, int k, int l){
		int temp = arr[i][j];
		arr[i][j] = arr[k][l];
		arr[k][l] = temp;
		return arr;
	}
	private void sysprint(String str){
		System.out.println(str);
	}
	public String toString()               // string representation of this board (in the output format specified below)
	{
	  StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	  for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
	        s.append(String.format("%2d ", this.blocks[i][j]));
	    }
	    s.append("\n");
	  }
	  return s.toString();
	}
	public static void main(String[] args) // unit tests (not graded)
	{
		//int[][] tmp1 = new int[4][10];
		//System.out.println("  "+tmp1[0].length);
/*		int [][] tmp1 = new int[2][2];
		tmp1[0][0] = 1;tmp1[0][1] = 1;tmp1[1][0] = 1;tmp1[1][1] = 1;
		int [][] tmp2 = new int[2][2];
		tmp2[0][0] = 1;tmp2[0][1] = 1;tmp2[1][0] = 1;tmp2[1][1] = 1;
		Board b1 = new Board(tmp1);
		Board b2 = new Board(tmp2);
		if(b1.equals(b2))
			System.out.println(" equal");*/
/*		int[]  a = {1, 2, 3, 4};
		int[] b = a.clone();
		int[] c = a.clone();
		c[0] = 0;
		for(int i : a){
			System.out.println("  "+i);
		}*/
/*		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 1;tmp1[0][2] = 1;
		tmp1[1][0] = 1;tmp1[1][1] = 0;tmp1[1][2] = 1;
		tmp1[2][0] = 1;tmp1[2][1] = 1;tmp1[2][2] = 1;
		Board b1 = new Board(tmp1);
		int[][] tmp2 = tmp1.clone();
		b1.swap(tmp2, 2, 1, 1, 1);
		int[][] tmp3 = tmp1.clone();
		b1.swap(tmp3, 0, 1, 1, 1);
		Board b2 = new Board(tmp2);
		Board b3 = new Board(tmp3);
		System.out.println("b1    "+b1.toString());
		System.out.println("b2    "+b2.toString());
		System.out.println("b3    "+b3.toString());
		int[][] tmp4 = tmp1.clone();
		Board b4 = new Board(tmp4);
		System.out.println("b4    "+b4.toString());*/
/*		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 1;tmp1[0][2] = 1;
		tmp1[1][0] = 1;tmp1[1][1] = 0;tmp1[1][2] = 1;
		tmp1[2][0] = 1;tmp1[2][1] = 1;tmp1[2][2] = 1;
		Board b1 = new Board(tmp1);*/
		int [][] tmp1 = new int[2][2];
		tmp1[0][0] = 1;tmp1[0][1] = 2;
		tmp1[1][0] = 3;tmp1[1][1] = 0;
		Board b = new Board(tmp1);
		System.out.println(b.hamming());
	}
	@Override
	public int compareTo(Board o) {
		// TODO Auto-generated method stub
		int tmp = 0;
		//compare priority
		int priority1 = this.manhattan() + this.steps;
		int priority2 = o.manhattan() + o.steps;
		if(priority1 < priority2)
			tmp = -1;
		else if(priority1 == priority2)
			tmp = 0;
		else if (priority1 > priority2)
			tmp = 1;
		return tmp;
	}
}
