/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import apollo.Transcription;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<PianoEvent> pianoEvents = new ArrayList<PianoEvent>();
        Map data1 = new HashMap();
        data1.put("key", 66);
        data1.put("velocity", 60);
        pianoEvents.add(new PianoEvent(PianoEvent.NOTE_ON, 2.0, data1));

        Map data2 = new HashMap();
        data2.put("value", 127);
        pianoEvents.add(new PianoEvent(PianoEvent.SUSTAIN_PEDAL, 2.5, data2));

        assertEquals(pianoEvents, new Transcription(pianoEvents).pianoEvents);
    }
    
}
