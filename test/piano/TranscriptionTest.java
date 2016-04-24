/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.util.Arrays;
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
public class TranscriptionTest {
    
    public TranscriptionTest() {
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
        KeyPress[] keyPresses = new KeyPress[2];
        keyPresses[0] = new KeyPress(66, 60, new Interval(2.0, 5.5));
        keyPresses[1] = new KeyPress(67, 60, new Interval(2.0, 5.5));
        
        assertEquals(Arrays.asList(keyPresses), new Transcription(keyPresses).actions);
    }
    
}
