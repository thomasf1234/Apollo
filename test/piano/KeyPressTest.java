/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

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
public class KeyPressTest {
    
    public KeyPressTest() {
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
    public void testProperties() {
        Interval interval = new Interval(2.0, 5.5);
        KeyPress keyPress = new KeyPress(3, 60, interval);
        double delta = 0.001;
        
        assertEquals(3, keyPress.key);
        assertEquals(60, keyPress.velocity);
        assertEquals(interval, keyPress.interval);
        assertEquals(2.0, keyPress.interval.getStartTime(), delta);
        assertEquals(5.5, keyPress.interval.getEndTime(), delta);
        assertEquals(3.5, keyPress.interval.getDuration(), delta);
    }
    
    @Test
    public void testGetNoteName() {
        Interval interval = new Interval(2.0, 5.5);
        KeyPress keyPress = new KeyPress(66, 60, interval);
        
        assertEquals("F#4", keyPress.getNoteName());
        
        keyPress.key = 60;
        assertEquals("C4", keyPress.getNoteName());
        
        keyPress.key = 21;
        assertEquals("A0", keyPress.getNoteName());
        
        keyPress.key = 96;
        assertEquals("C7", keyPress.getNoteName());
    }
}
