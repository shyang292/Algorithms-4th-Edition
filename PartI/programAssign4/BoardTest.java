package programAssign4;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testDimension() {
		fail("Not yet implemented");
	}

	@Test
	public void testHamming() {
		//fail("Not yet implemented");
		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 0;tmp1[0][2] = 1;
		tmp1[1][0] = 1;tmp1[1][1] = 1;tmp1[1][2] = 1;
		tmp1[2][0] = 1;tmp1[2][1] = 1;tmp1[2][2] = 1;
		Board b = new Board(tmp1);
		System.out.println(b.hamming());
	}

	@Test
	public void testManhattan() {
		//fail("Not yet implemented");
		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 2;tmp1[0][2] = 3;
		tmp1[1][0] = 4;tmp1[1][1] = 5;tmp1[1][2] = 6;
		tmp1[2][0] = 7;tmp1[2][1] = 8;tmp1[2][2] = 0;
		Board b = new Board(tmp1);
		System.out.println(b.manhattan());
	}

	@Test
	public void testIsGoal() {
		fail("Not yet implemented");
	}

	@Test
	public void testTwin() {
		//fail("Not yet implemented");
		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 0;tmp1[0][1] = 1;tmp1[0][2] = 7;
		tmp1[1][0] = 6;tmp1[1][1] = 4;tmp1[1][2] = 5;
		tmp1[2][0] = 3;tmp1[2][1] = 2;tmp1[2][2] = 8;
		Board b = new Board(tmp1);
		System.out.println(b.twin().toString());
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testSwap(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testNeighbors() {
		//fail("Not yet implemented");
		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 1;tmp1[0][2] = 1;
		tmp1[1][0] = 1;tmp1[1][1] = 0;tmp1[1][2] = 1;
		tmp1[2][0] = 1;tmp1[2][1] = 1;tmp1[2][2] = 1;
		Board b = new Board(tmp1);
		Stack<Board> s = (Stack<Board>) b.neighbors();
		for(Board tmp : s){
			System.out.println(""+tmp.toString());
		}
	}
	@Test
	public void compareTo(Board o) {
		
	}
	@Test
	public void testToString() {
		//fail("Not yet implemented");
		int [][] tmp1 = new int[3][3];
		tmp1[0][0] = 1;tmp1[0][1] = 1;tmp1[0][2] = 1;
		tmp1[1][0] = 1;tmp1[1][1] = 0;tmp1[1][2] = 1;
		tmp1[2][0] = 1;tmp1[2][1] = 1;tmp1[2][2] = 1;
		Board b = new Board(tmp1);
		//b.toString()
		System.out.println(""+b.toString());
	}

}
