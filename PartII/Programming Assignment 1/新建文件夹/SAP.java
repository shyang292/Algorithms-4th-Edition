

import java.util.LinkedList;
import java.util.TreeMap;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class SAP {
       private Digraph dg;
       private int length = -1;
       private int ancestor = -1;
       //boolean isCal = false;// whether calculate SAP or not
       /* constructor takes a digraph (not necessarily a DAG) */
       public SAP(final Digraph G) {
          dg = new Digraph(G); //call copy constructor to implement immutable
       }
       /* length of shortest ancestral path between v and w;
        * -1 if no such path */
       public int length(final int v, final int w) {
    	       throwException1(v, w);
               calSAP(v, w);
               return length;
       }
       // a common ancestor of v and w that participates
       //in a shortest ancestral path;
       //-1 if no such path
       public int ancestor(final int v, final int w) {
    	   throwException1(v, w);
           calSAP(v, w);
           return ancestor;
       }
       /*calculate SAP*/
       private void calSAP(final int v, final int w){
           BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(dg, v);
           LinkedList<Integer> list1 = new LinkedList<Integer>();
           for(int i = 0; i < dg.V(); i++)
               if(bfs1.hasPathTo(i))
                    list1.add(i);
           BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(dg, w);
		   LinkedList<Integer> list2 = new LinkedList<Integer>();
	       for(int j = 0; j < dg.V(); j++)
	    	   if(bfs2.hasPathTo(j))
	    		   list2.add(j);
	       boolean flag = true;
	       for(int k : list1)
	    	   for(int l : list2){
	    		   if(k == l){
	    			   int tempdis = bfs1.distTo(k) + bfs2.distTo(l);
	    			   if(flag){//initialize length
	    				   length = tempdis;
	    				   ancestor = k;
	    				   flag = false;
	    			   }else if (tempdis < length){
	    				   length = tempdis;
	    				   ancestor = k;
	    			   }
	    		   }
	    	   }
	   }
	   // length of shortest ancestral path between any vertex in v and any vertex in w;
	   //-1 if no such path
	   public int length(Iterable<Integer> v, Iterable<Integer> w){
		   throwException2(v, w);
		   MinPQ<Integer> pq = new MinPQ<Integer>();
		   if(v == null || w==null) return -1;
		   for(int m : v){
			   for(int n: w){
				   calSAP(m, n);
				   pq.insert(length);
			   }
		   }
		   return pq.min();
	   }
	   // a common ancestor that participates in shortest ancestral path;
	   //-1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		   throwException2(v, w);
		   if(v ==null || w==null) return -1;
		   TreeMap<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
		   MinPQ<Integer> pq = new MinPQ<Integer>();
		   for(int m : v)
			   for(int n: w){
				   calSAP(m, n);
				   map2.put(length, ancestor);
			   }
		   return map2.get(map2.firstKey());  //return ancestor corresponding to the shortest length
	   }
	   private void throwException1(int v, int w){
		   if( v < 0 || v > dg.V()-1 || w < 0 || w > dg.V()-1){
			   throw new java.lang.IndexOutOfBoundsException();
		   }
		   
	   }
	   private void throwException2(Iterable<Integer> v, Iterable<Integer> w){
		   if(v == null || w == null){
			   throw new java.lang.NullPointerException();
		   }
		   for(int i : v){
			   throwException3(i);
		   }
		   for(int j : w){
			   throwException3(j);
		   }
	   }
	   private void throwException3(int v){
		   if( v < 0 || v > dg.V()-1){
			   throw new java.lang.IndexOutOfBoundsException();
		   }
	   }
	   // do unit testing of this class
	   public static void main(String[] args){
		    /*In in = new In(args[0]);
		    Digraph G = new Digraph(in);
		    SAP sap = new SAP(G);
		    while (!StdIn.isEmpty()) {
		        int v = StdIn.readInt();
		        int w = StdIn.readInt();
		        int length   = sap.length(v, w);
		        int ancestor = sap.ancestor(v, w);
		        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		    }*/
		    /*In in = new In("digraph1.txt");
		    Digraph G = new Digraph(in);
		    SAP sap = new SAP(G);
	    	ArrayList<Integer> varr = new ArrayList<Integer>();
	    	ArrayList<Integer> warr = new ArrayList<Integer>();
	    	varr.add(3); varr.add(4);varr.add(8);
	    	warr.add(9);warr.add(11);warr.add(12);
	        int length   = sap.length(varr, warr);
	        int ancestor = sap.ancestor(varr, warr);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);*/
		    In in = new In(args[0]);
		    Digraph G = new Digraph(in);
		    SAP sap = new SAP(G);
		    while (!StdIn.isEmpty()) {
		        int v = StdIn.readInt();
		        int w = StdIn.readInt();
		        int length   = sap.length(v, w);
		        int ancestor = sap.ancestor(v, w);
		        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		    }
	   }
	}