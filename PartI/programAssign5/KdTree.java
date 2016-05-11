package programAssign5;

import java.util.TreeSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	   private Node root;
	   Queue<Point2D> queue = new Queue<Point2D>();
	   private double mindistance = 0;
	   private Point2D nearpoint; 
	   public KdTree() 
	   {

	   }
	   private static class Node {
		   private Point2D p;      //key the point  
		   private RectHV rect;    //value the axis-aligned rectangle corresponding to this node
		   private Node lb;        // the left/bottom subtree
		   private Node rt;        // the right/top subtree
		   private int N;             // number of nodes in subtree
		   //private int depth; 
		   public Node(Point2D point, int N){
			   p = new Point2D(point.x(), point.y());
			   this.N = N;
		   }
		}
	   public boolean isEmpty() 
	   {
		   return size() == 0;
	   }
	   public int size()    
	   {
		   return size(root);
	   }
	   private int size(Node x) {
	        if (x == null) return 0;
	        else return x.N;
	    }
	   public void insert(Point2D p) 
	   {
		   root = insert(null, root, p, 0,0);
	   }
	   private Node insert(Node previous, Node x, Point2D p, int depth, double comprison){
		   if(x == null && x == root){
			   x = new Node(p, 1);
			   x.rect = new RectHV(0,0,1,1);
			   //draw(p.x(), x.rect.ymin(), p.x(), x.rect.ymax() );
			   //draw(p);
			   return x;
		   }
		   if (x == null){ 
			   Node node = new Node(p, 1);
			   //draw(p);
			   if(depth % 2 == 1 && comprison < 0){//horizontal line
				   node.rect = new RectHV(previous.rect.xmin(), previous.rect.ymin(),
						   previous.p.x(), previous.rect.ymax());
				   //draw(node.rect.xmin(), p.y(), node.rect.xmax(), p.y());
			   }else if(depth % 2 == 1 && comprison >= 0){
				   node.rect = new RectHV(previous.p.x(), previous.rect.ymin(), 
						   previous.rect.xmax(), previous.rect.ymax());
				   //draw(node.rect.xmin(), p.y(), node.rect.xmax(), p.y());
			   }else if(depth % 2 == 0 && comprison < 0){//vertical line
				   node.rect = new RectHV(previous.rect.xmin(), previous.rect.ymin(),
						   previous.rect.xmax(), previous.p.y());
				   //draw(p.x(), node.rect.ymin(), p.x(), node.rect.ymax());
			   }else if(depth % 2 == 0 && comprison >= 0){
				   node.rect = new RectHV(previous.rect.xmin(), previous.p.y(),
						   previous.rect.xmax(), previous.rect.ymax());
				   //draw(p.x(), node.rect.ymin(), p.x(), node.rect.ymax());
			   }
			   return node;
			   }
		   if(depth % 2 == 0){//compare coordinate x
		        double cmp = p.x() - x.p.x();
		        if      (cmp < 0) x.lb  = insert(x, x.lb, p, ++depth, cmp);
		        else if (cmp >= 0) x.rt = insert(x, x.rt, p, ++depth, cmp);
		        x.N = 1 + size(x.lb) + size(x.rt);
		        return x;
		   }else{//compare coordinate y
			    double cmp = p.y() - x.p.y();
		        if      (cmp < 0) x.lb  = insert(x, x.lb, p, ++depth, cmp);
		        else if (cmp >= 0) x.rt = insert(x, x.rt, p, ++depth, cmp);
		        x.N = 1 + size(x.lb) + size(x.rt);
		        return x;
		   }
	   }
	public boolean contains(Point2D p) 
	   {
		   return get(p);
	   }
	    public boolean get(Point2D p) {
	        return get(root, p, 0);
	    }

	    private boolean get(Node x, Point2D p, int depth) {
	    	if(depth % 2 == 0){// compare x coordiante
		        if (x == null) return false;
		        double cmp = (double) (p.x() - x.p.x());
		        //int cmp = p.compareTo(x.p);
		        if      (cmp < 0) return get(x.lb, p, depth++);
		        else if (cmp > 0) return get(x.rt, p, depth++);
		        else              return true;
	    	}else {//compare y coordinate
		        if (x == null) return false;
		        double cmp = (double) (p.y() - x.p.y());
		        //int cmp = p.compareTo(x.p);
		        if      (cmp < 0) return get(x.lb, p, depth++);
		        else if (cmp > 0) return get(x.rt, p, depth++);
		        else              return true;
	    	}
	    }
	    public void draw(){
			//draw a unit square
			   StdDraw.setPenRadius(.01);
			   StdDraw.setPenColor(StdDraw.BLACK);
			   StdDraw.square(0.5, 0.5, 0.5);
			draw(root, 0);
	    }
	    private void draw(Node node, int depth){
	    	if(node != null){ 
	    		draw(node.p);
	    		if(depth % 2 == 0){//vertical line
	    			draw(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
	    		}else{//horizontal line
	    			draw(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
	    		}
	    		int temp = depth + 1;
	    		draw(node.lb, temp);
	    		draw(node.rt, temp);
	    	}
	    }
	    private void draw(Point2D p) 
	   {
		   //draw all points in black
		   //StdDraw.setPenColor(StdDraw.BLACK) and StdDraw.setPenRadius(.01)
		   StdDraw.setPenColor(StdDraw.BLACK);
		   StdDraw.setPenRadius(.02);
		   StdDraw.point(p.x(), p.y());

	   }
	   private void draw(double xmin, double y, double xmax, double y2) {
			// TODO Auto-generated method stub
			   //draw the subdivisions in red (for vertical splits) 
			   //draw blue (for horizontal splits)
			   StdDraw.setPenRadius(.01);
			   if(xmin == xmax){//vertical splits
				   StdDraw.setPenColor(StdDraw.RED);
				   StdDraw.line(xmin, y, xmax, y2);
			   }else if(y == y2){//horizontal splits
				   StdDraw.setPenColor(StdDraw.BLUE);
				   StdDraw.line(xmin, y, xmax, y2);
			   }
		}
	   public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle 
	   {
		   return range(root, rect);

	   }
	   private Iterable<Point2D> range(Node node, RectHV rect){
		   if (node == null) return null;
		   if (!node.rect.intersects(rect)) return null;
		   if(rect.contains(node.p))
			   queue.enqueue(node.p);
		   range(node.lb, rect);
		   range(node.rt, rect);
		   return queue;
	   }
	   public Point2D nearest(Point2D p) 
	   {
		  mindistance = root.p.distanceTo(p);
		  nearpoint = root.p;
		  return nearest(root, p);
	   }
	   private Point2D nearest(Node node, Point2D p){
		   if (node == null) return null;
		   if (node.rect.distanceTo(p) > mindistance) return null;
		   if (node.p.distanceTo(p) < mindistance) {
			   mindistance = node.p.distanceTo(p);
			   nearpoint = node.p;
		   }
		   if (node.lb!=null && node.lb.rect.contains(p)) {
			   nearest(node.lb, p);
			   nearest(node.rt, p);
		   }else{
			   nearest(node.rt, p);
			   nearest(node.lb, p);
		   }
		   return nearpoint;
	   }
	   public static void printint(int b){
		   System.out.println(" "+b);
	   }
	   
	   public static void main(String[] args)  
	   {
/*		   PointSET pset = new PointSET();
		   TreeSet<Point2D> tset = new TreeSet<Point2D>();
		   In in = new In(args[0]);
        int x=0, y=0;
        while(in.hasNextLine()){
     	   Point2D point = new Point2D(in.readDouble() , in.readDouble());
     	   pset.insert(point);
        }
        RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
        tset = (TreeSet<Point2D>) pset.range(rect);
        for(Point2D p : tset){
     	   System.out.println(" range: "+p.toString());
        }
        Point2D point = new Point2D(0.4,0.5);
        System.out.println("NN: "+pset.nearest(point));*/
        
        KdTree kdt = new KdTree();
        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4); 
        Point2D p3 = new Point2D(0.2, 0.3); 
        Point2D p4 = new Point2D(0.4, 0.7); 
        Point2D p5 = new Point2D(0.9, 0.6); 
        Point2D p6 = new Point2D(0.5, 0.5);
        kdt.insert(p1);
        kdt.insert(p2);
        kdt.insert(p3);
        kdt.insert(p4);
        kdt.insert(p5);
        kdt.draw();
/*        Queue<Point2D> queue = (Queue<Point2D>) kdt.range(new RectHV(0.6, 0, 1, 1));
        for(Point2D point : queue)
        	System.out.println(queue.size()+" "+point.toString());*/
        /*System.out.println("size "+kdt.size());
        if(kdt.contains(p2))
        	System.out.println("  contain p1");*/
        /*int a = 0;
		printint(++a);
		System.out.println("a "+a);
		*/
        
		Point2D p7 = kdt.nearest(p6);
        System.out.println(" "+p7.toString());
	   }
}
