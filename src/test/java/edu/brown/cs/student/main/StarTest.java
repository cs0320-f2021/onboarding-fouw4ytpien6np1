package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StarTest {

    @Test
    public void testStar() {
        Star star = new Star("0", "test", 1.0, 2.0, 3.0);
        assertEquals("0", star.returnStarID());
        assertEquals("test", star.returnStarName());
        assertEquals(1.0, star.returnX(), 0.0001);
        assertEquals(2.0, star.returnY(), 0.0001);
        assertEquals(3.0, star.returnZ(), 0.0001);
    }

    @Test
    public void testSetEuclideanDistance() {
        Star star = new Star("0", "test", 10.0, 21.0, 32.0);
        star.setEuclideanDistance(10.0);
        assertEquals(10.0, star.returnEuclideanDis(), 0.01);
    }

}
