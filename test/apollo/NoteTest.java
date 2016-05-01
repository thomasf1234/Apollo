/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

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
public class NoteTest {
    
    public NoteTest() {
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
        Note note = new Note(3, 60, interval);
        double delta = 0.001;
        
        assertEquals(3, note.midiNoteNumber);
        assertEquals(60, note.velocity);
        assertEquals(interval, note.interval);
        assertEquals(2.0, note.interval.getStartTime(), delta);
        assertEquals(5.5, note.interval.getEndTime(), delta);
        assertEquals(3.5, note.interval.getDuration(), delta);
    }
    
    @Test
    public void testOctave() {
        Interval interval = new Interval(2.0, 5.5);
        Note note = new Note(66, 60, interval);
        
        assertEquals(4, note.octave());
        
        note.midiNoteNumber = 60;
        assertEquals(4, note.octave());
        
        note.midiNoteNumber = 21;
        assertEquals(0, note.octave());
        
        note.midiNoteNumber = 96;
        assertEquals(7, note.octave());
    }
    
    @Test
    public void testGetNoteName() {
        Interval interval = new Interval(2.0, 5.5);
        Note note = new Note(66, 60, interval);
        
        assertEquals("F#4", note.getNoteName());
        
        note.midiNoteNumber = 60;
        assertEquals("C4", note.getNoteName());
        
        note.midiNoteNumber = 21;
        assertEquals("A0", note.getNoteName());
        
        note.midiNoteNumber = 96;
        assertEquals("C7", note.getNoteName());
    }
}