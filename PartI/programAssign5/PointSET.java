package programAssign5;

import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	   TreeSet<Point2D> ts;
	   public PointSET() // construct an empty set of points 
	   {
		 ts  = new TreeSet<Point2D>();
	   }
	   public boolean isEmpty() // is the set empty? 
	   {
		if(ts.isEmpty()) 
			return true;
		return false;
	   }
	   public int size()    // number of points in the set 
	   {
		return ts.size();
	   }
	   public void insert(Point2D p)  // add the point to the set (if it is not already in the set)
	   {
		   if(!contains(p)){//not in the set
			   ts.add(p);
		   }
	   }
	   public boolean contains(Point2D p) // does the set contain point p? 
	   {
		   if(ts.contains(p))
			   return true;
		return false;
		   
	   }
	   public void draw() // draw all points to standard draw 
	   {
		   for(Point2D point : ts)
			   StdDraw.point(point.x(), point.y());
	   }
	   public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle 
	   {
		   TreeSet<Point2D> tset = new TreeSet<Point2D>();
		   for(Point2D point : ts){
			   if(rect.contains(point)){
				   tset.add(point);
			   }
		   }
		   return tset;
	   }
	   public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty 
	   {
		   if(ts.isEmpty())
			   return null;
		   Point2D nn = null;
		   double mindis = 0, temp = 0;
		   boolean flag = true;
		   for(Point2D point : ts){
			   if(flag){
				   mindis = point.distanceTo(p);
				   flag = false;
			   }
			   temp = point.distanceTo(p);
			   if(temp < mindis){
				   mindis = temp;
				   nn = point;
			   }
		   }
		   return nn;
	   }
	   public static void main(String[] args)  // unit testing of the methods (optional) 
	   {
		   PointSET pset = new PointSET();
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
           System.out.println("NN: "+pset.nearest(point));
	   }
}