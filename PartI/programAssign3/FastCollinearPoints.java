package programAssign3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class FastCollinearPoints {
	   private ArrayList<LineSegment> list = new ArrayList<LineSegment>();
	   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
	   {
		   double[] slopeArr;
 		   Point[] orginalpoints = points.clone();
		   //int count =0;
		   for(int i=0;i<orginalpoints.length;i++){
			   Arrays.sort(points, orginalpoints[i].slopeOrder());//sort by slope
			   slopeArr = new double[orginalpoints.length];
			   for(int k=0;k<points.length;k++){//calculate the slope of each point
				   slopeArr[k] = points[k].slopeTo(orginalpoints[i]);
			   }
			   //slopeArr[0] = slopeArr[1];// [0] is identical , so is -infinity
			   Stack<Point> stack = new Stack<Point>();
			   for(int j=1;j<points.length-1;j++){//whether there are 4 equal slopes
				   if(slopeArr[j] == slopeArr[j+1]){
					   if(stack.size() == 0){//the first two equal elements
						   stack.push(points[j]);
						   stack.push(points[j+1]);
					   }else{// 
						   stack.push(points[j+1]);
					   }
				   }else{
					   if(stack.size()>=3){
						   stack.push(points[0]);//the 1th element into stack
						   int tmp = stack.size();
						   Point[] tmpArr = new Point[tmp];
						   for(int k=0;k<tmp;k++){
							   tmpArr[k] = stack.pop();
						   }
						   Arrays.sort(tmpArr);
						   LineSegment ls = new LineSegment(tmpArr[0],tmpArr[tmpArr.length-1]);
						   list.add(ls);
					   }else{
						   stack.clear();
					   }
				   }
			   } 
			}
	   }

	   public  int numberOfSegments()        // the number of line segments
	   {
		return list.size();
		   
	   }
	   
	   public LineSegment[] segments()                // the line segments
	   {
		  LineSegment[] ls = (LineSegment[]) list.toArray(new LineSegment[list.size()]);
		  return ls;
	   }
	   private void printArr(Point[] points){
		   for(int i=0;i<points.length;i++){
			   System.out.println("the "+i+" element slope "+points[i].slopeTo(points[0]));
		   }
	   }
}