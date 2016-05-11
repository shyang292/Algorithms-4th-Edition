package programAssign4;

import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

class Stepper{
	Board board;
	MinPQ<Board> pq = new MinPQ<Board>();
	Board deqboard = null;
	Stepper(Board b){
		board = b;
   	pq.insert(board);
	}
	boolean isGoal(){
		if(deqboard == null)
			return false;
		return deqboard.isGoal();
	}
	void nextStep(){
		deqboard = pq.delMin();
   	Stack<Board> boardstack = (Stack<Board>) deqboard.neighbors();// neighbour 
   	for(Board tmp1 : boardstack){
   		tmp1.previous = deqboard;
   		if(!tmp1.equals(deqboard.previous)) 
   			pq.insert(tmp1);
   	}
	}
}