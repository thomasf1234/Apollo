/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import javax.sound.midi.MidiUnavailableException;
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
public class PianoTest {
    
    public PianoTest() {
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

    /**
     * Test of noteOn method, of class Piano.
     * @throws javax.sound.midi.MidiUnavailableException
     */
    @Test
    public void testNoteOn() throws MidiUnavailableException {
        System.out.println("noteOn");
  
        Piano piano = new Piano();
        //mute piano so as not to actually play sound
        piano.setMute(true);
        
        int midiNoteNumber = 60;
        //note should be off
        assertEquals(0, piano.getNoteState(midiNoteNumber));
        piano.noteOn(midiNoteNumber, 50);
        //note should be on
        assertEquals(50, piano.getNoteState(midiNoteNumber));
    }

    /**
     * Test of noteOff method, of class Piano.
     * @throws javax.sound.midi.MidiUnavailableException
     */
    @Test
    public void testNoteOff() throws MidiUnavailableException {
        System.out.println("noteOff");
  
        Piano piano = new Piano();
        //mute piano so as not to actually play sound
        piano.setMute(true);
        
        int midiNoteNumber = 60;
        //note should be off
        assertEquals(0, piano.getNoteState(midiNoteNumber));
        piano.noteOn(midiNoteNumber, 50);
        //note should be on
        assertEquals(50, piano.getNoteState(midiNoteNumber));
        
        piano.noteOff(midiNoteNumber);
        //note should be off
        assertEquals(0, piano.getNoteState(midiNoteNumber));     
    }

    /**
     * Test of sustainPedalUpdate method, of class Piano.
     */
    @Test
    public void testSustainPedalUpdate() throws MidiUnavailableException {
        System.out.println("sustainPedalUpdate");
        
        Piano piano = new Piano();
        //mute piano so as not to actually play sound
        piano.setMute(true);
       
        //sustain pedal should be off upon initialization
        assertEquals(0, piano.getSustainPedalState());
        
        //hold down sustain pedal
        piano.sustainPedalUpdate(127);
        //sustain pedal should be on
        assertEquals(127, piano.getSustainPedalState());
    
        //release sustain pedal
        piano.sustainPedalUpdate(0);
        //sustain pedal should be off
        assertEquals(0, piano.getSustainPedalState());
    }
    
}
