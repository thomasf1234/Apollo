/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

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

    /**
     * Test of toNotes method, of class Transcription.
     */
    @Test
    public void testToNotes() {
        System.out.println("toNotes");
        double delta = 0.001;
        
        List<PianoEvent> pianoEvents = new ArrayList<PianoEvent>();
        Map data1 = new HashMap();
        data1.put("key", 66);
        data1.put("velocity", 60);
        pianoEvents.add(new PianoEvent(PianoEvent.NOTE_ON, 2.0, data1));
        
        Map data2 = new HashMap();
        data2.put("key", 66);
        data2.put("velocity", 0);
        pianoEvents.add(new PianoEvent(PianoEvent.NOTE_OFF, 2.3, data2));
        
        Map data3 = new HashMap();
        data3.put("key", 70);
        data3.put("velocity", 30);
        pianoEvents.add(new PianoEvent(PianoEvent.NOTE_ON, 2.2, data3));
        
        Map data4 = new HashMap();
        data4.put("key", 70);
        data4.put("velocity", 0);
        pianoEvents.add(new PianoEvent(PianoEvent.NOTE_OFF, 3.9, data4));
        
        Map data5 = new HashMap();
        data5.put("value", 127);
        pianoEvents.add(new PianoEvent(PianoEvent.SUSTAIN_PEDAL, 2.5, data5));

        //doesn't contain the sustain pedal, only notes
        List<Note> expectedNotes = new ArrayList<Note>();
        expectedNotes.add(new Note(66, 60, new Interval(2.0, 2.3)));
        expectedNotes.add(new Note(70, 30, new Interval(2.2, 3.9)));
  
        List<Note> actualNotes = new Transcription(pianoEvents).toNotes();
        assertEquals(expectedNotes.size(), actualNotes.size());
      
        
        for (int i = 0; i < expectedNotes.size(); i++) {
            assertEquals(expectedNotes.get(i).midiNoteNumber, actualNotes.get(i).midiNoteNumber);
            assertEquals(expectedNotes.get(i).velocity, actualNotes.get(i).velocity);
            assertEquals(expectedNotes.get(i).interval.getStartTime(), actualNotes.get(i).interval.getStartTime(), delta);
            assertEquals(expectedNotes.get(i).interval.getEndTime(), actualNotes.get(i).interval.getEndTime(), delta);
            assertEquals(expectedNotes.get(i).interval.getDuration(), actualNotes.get(i).interval.getDuration(), delta);
        }
    }
    
}
