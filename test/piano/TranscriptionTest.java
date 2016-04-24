/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<KeyPress> keyPresses = new ArrayList<KeyPress>();
        keyPresses.add(new KeyPress(66, 60, new Interval(2.0, 5.5)));
        keyPresses.add(new KeyPress(67, 60, new Interval(2.0, 5.5)));
        
        assertEquals(keyPresses, new Transcription(keyPresses).actions);
    }
    
}
