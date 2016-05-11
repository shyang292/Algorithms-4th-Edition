

import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class BoggleSolver
{

	private TST<Integer> st = new TST<Integer>();
	//boolean[][] marked;
	//String str ;//a sequence of letter
	private HashSet<String> results;
	private BoggleBoard board;
	private char[][] boardarr;
	private int M, N;
	private boolean[][] marked;
	private ArrayList<String> list = new ArrayList<String>();
	private Bag<Integer>[][] adj;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
    	//generate a TrieSt based on dictionary
    	for(int i=0;i<dictionary.length;i++){
    		if(dictionary[i].length()>=3)
    			st.put(dictionary[i], 0);
    	}
    }
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    @SuppressWarnings("unchecked")
	public Iterable<String> getAllValidWords(BoggleBoard board){
    	//clean all elements in results
    	results = new HashSet<String>();
    	this.board = board;
    	M = board.rows();
    	N = board.cols();
    	marked = new boolean[M][N];
    	boardarr = new char[M][N];
    	adj = (Bag<Integer>[][])new Bag[M][N];
    	this.initializeArr(marked);
    	for(int m = 0; m < M; m++)
    		for(int n = 0; n < N; n++){
    			//this.remark(marked);
    			//st.stack.clear();
    			dfs(m, n, new StringBuilder(""));
    		}
		return results;
    }
    private void initializeArr(boolean[][] marked){
    	int height = marked.length;
    	int width = marked[0].length;
    	for(int m=0;m<height;m++)
    		for(int n=0;n<width;n++){
    			marked[m][n] = false;
    			boardarr[m][n] = board.getLetter(m, n);
    			adj[m][n] = new Bag<Integer>();
    			if(inbound(m-1, n-1, height, width)){//1
    				adj[m][n].add((m-1)*width +n-1);
    			}
    			if(inbound(m-1, n, height, width)){//2
    				adj[m][n].add((m-1)*width +n);
    			}
    			if(inbound(m-1, n+1, height, width)){//3
    				adj[m][n].add((m-1)*width +n+1);
    			}
    			if(inbound(m, n-1, height, width)){//4
    				adj[m][n].add((m)*width +n-1);
    			}
    			if(inbound(m, n+1, height, width)){//5
    				adj[m][n].add((m)*width +n+1);
    			}
    			if(inbound(m+1, n-1, height, width)){//6
    				adj[m][n].add((m+1)*width +n-1);
    			}
    			if(inbound(m+1, n, height, width)){//7
    				adj[m][n].add((m+1)*width +n);
    			}
    			if(inbound(m+1, n+1, height, width)){//8
    				adj[m][n].add((m+1)*width +n+1);
    			}
    		}
    }
    //find path with dfs
    private void dfs(int x, int y, StringBuilder str){
    	marked[x][y] = true;
    	char c = boardarr[x][y];
    	if( c == 'Q')
    		str.append("QU");
    	else 
    		str.append(c);
    	if(!st.getPrefix(String.valueOf(c))){
    		str.deleteCharAt(str.length()-1);
    		marked[x][y]=false;
    		return;
    	}
    	//find str in dictionary
    	if(str.length() >= 3 && st.get(str.toString()) != null)
    		results.add(str.toString());
    	//run dfs
    	for(int ind : adj[x][y]){
    		int indX = ind / N ;
    		int indY = ind % N ;
    		if(!marked[indX][indY])
    			dfs(indX, indY, str);
    	}
    	str.deleteCharAt(str.length()-1);
    	marked[x][y] = false;
    	st.stack.pop();
    }
    //x,y in bound
    private boolean inbound(int x, int y, int M, int N){
    	if(x>=0 &&x<M && y>=0 && y<N) return true;
    	return false;
    }
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	int score = 0;
		if(word.length()<=4)
			score += 1;
		else if(word.length()<=5)
			score +=2;
		else if(word.length()<=6)
			score +=3;
		else if(word.length()<=7)
			score +=5;
		else 
			score +=11;
		return score;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        /*System.out.println("please input the board size: ");
        int size = StdIn.readInt();
        Stopwatch sw = new Stopwatch();
        for(int i=0;i<1000;i++){
        	BoggleBoard board = new BoggleBoard(size, size);
        	System.out.print(i+"begin:  "+sw.elapsedTime());
        	solver.getAllValidWords(board);
        	System.out.println("  "+sw.elapsedTime());
        }*/
    	
    }
}