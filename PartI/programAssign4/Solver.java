package programAssign4;

import java.util.Iterator;
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private int minsteps = -1;
	private Stack<Board> stack =new Stack<Board>();
	int flag = 0;
	//private int steps = 0;// record the number of moves from initial board to the current board
    public Solver(Board initial)// find a solution to the initial board (using the A* algorithm)
    {
    	Board twinboard = initial.twin();
    	Stepper realStepper = new Stepper(initial);
    	Stepper twinStepper = new Stepper(twinboard);
    	while(!realStepper.isGoal() && !twinStepper.isGoal()){
    		realStepper.nextStep();
    		twinStepper.nextStep();
    		flag++;
    	}
    	//System.out.println(" falg  "+flag);
    	if(realStepper.isGoal())
        	while(realStepper.deqboard!=null){
        		stack.push(realStepper.deqboard);
        		realStepper.deqboard = realStepper.deqboard.previous;
        		minsteps++;
        	}
/*        	while(!stack.empty())
        		System.out.println(stack.pop().toString());
    	}else{
    		//unsolvable
    		System.out.println("this board is unsolable!!");
    	}*/
    	
/*    	MinPQ<Board> pq = new MinPQ<Board>();
    	pq.insert(initial);
    	Board deqboard = null;
    	do{
    		deqboard = pq.delMin();
	    	Stack<Board> boardstack = (Stack<Board>) deqboard.neighbors();// neighbour 
	    	for(Board tmp1 : boardstack){
	    		boolean flag = true;
	    		tmp1.previous = deqboard;
	    		if(!tmp1.equals(deqboard.previous)) 
	    			pq.insert(tmp1);
	    	}
    	}while(!deqboard.isGoal());*/
    	//print sequence of boards in a shortest solution
    	//Stack<Board> stack = new Stack<Board>();

    	//minsteps = stack.size();
    	//System.out.println(" "+stack.size());
    	/*while(!stack.empty())
    		System.out.println(stack.pop().toString());*/
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
		return false;
    	
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
		return minsteps;
    	
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	if(!isSolvable())
    		return null;
    	return stack;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
/*		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 0;tmp1[0][1] = 1;tmp1[0][2] = 3;
		tmp1[1][0] = 4;tmp1[1][1] = 2;tmp1[1][2] = 5;
		tmp1[2][0] = 7;tmp1[2][1] = 8;tmp1[2][2] = 6;
		Board b = new Board(tmp1);
		Solver s = new Solver(b);*/
/*    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			System.out.println("j: "+j);
    			if(j>=1){
    				break;
    			}
    		}
    		System.out.println("i: "+i);
    	}*/
    }
}