package programAssign3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testCompareTo() {
		Point p1 = new Point(1,1);
    	Point p2 = new Point(2,1);
		//fail("Not yet implemented");
    	 assertEquals("10 x 0 must be 0", 1, p1.compareTo(p2));
		
	}

	@Test
	public void testtoBool() {
		//fail("Not yet implemented");
		Point p1 = new Point(1,1);
		assertTrue("return false",p1.toBool() );
	}

}
