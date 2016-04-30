/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import apollo.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ad
 */
public class PointTest {
    
    public PointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetX() {
        assertEquals(0, new Point(0,3).getX());
        assertEquals(2, new Point(2,3).getX());
        assertEquals(-3, new Point(-3,3).getX());
    }
    
    @Test
    public void testGetY() {
        assertEquals(2, new Point(0,2).getY());
        assertEquals(0, new Point(2,0).getY());
        assertEquals(-3, new Point(0,-3).getY());
    }
    
}
