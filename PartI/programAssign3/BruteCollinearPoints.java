package programAssign3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class BruteCollinearPoints {
	   private int count =0;
	   private LineSegment[] lsArr = new LineSegment[10];
	   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
	   {
		   int i=0,j=0,k=0,m=0;
		   //pick 4 points
		   for(i=0;i<points.length;i++){
			   for(j=0;j<points.length;j++){
				   if(i == j) continue;
				   for(k=0;k<points.length;k++){
					   if((i == k) || (j == k)) continue;
					   for(m=0;m<points.length;m++){
						   if( i == m || j == m || k == m) continue;
						   //check three slope are equal    point i,j,k,m
						   double slope1 = points[i].slopeTo(points[j]);
						   double slope2 = points[i].slopeTo(points[k]);
						   double slope3 = points[i].slopeTo(points[m]);
						   if(slope1 != Double.POSITIVE_INFINITY && 
								   slope1 != Double.NEGATIVE_INFINITY &&
								   slope1 == slope2 &&
								   slope2 == slope3){
							   //generate a new array with four points
							   Point[] newArr = {points[i],points[j],points[k],points[m]};
							   Arrays.sort(newArr);
							   LineSegment ls = new LineSegment(newArr[0],newArr[3]);
							   lsArr[count++]=ls;
							   if(count == lsArr.length) resize(2*lsArr.length);
						   }  
					   }
				   }
			   }
		   }


		   
		   
	   }
	   public int numberOfSegments()  // the number of line segments
	   {
		return count;
		   
	   }
	   public LineSegment[] segments()   // the line segments
	   {
		int j=0;//
		for(int i=0;i<lsArr.length;i++){
			if(lsArr[i]!=null){
				j++;
			}
		}
		LineSegment[] temp1 = new LineSegment[j];
		for(int k=0;k<j;k++){
			temp1[k]=lsArr[k];
		}
		return temp1;
	   }
	    private void resize(int capacity) {
	        LineSegment[] temp = new LineSegment[capacity];
	        for (int i = 0; i < count; i++) {
	            temp[i] = lsArr[i];
	        }
	        lsArr = temp;
	    }
	    public static void main(String[] args) throws FileNotFoundException {
	    	
	    }
	    
}