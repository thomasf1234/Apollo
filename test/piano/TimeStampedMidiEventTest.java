/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
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
public class TimeStampedMidiEventTest {

    public TimeStampedMidiEventTest() {
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
     * Test of isNoteOn method, of class TimeStampedMidiEvent.
     */
    @Test
    public void testIsNoteOn() throws InvalidMidiDataException, IOException {
        System.out.println("isNoteOn");
        Track[] tracks = MidiSystem.getSequence(new File("test\\samples\\test2.mid")).getTracks();
        TimeStampedMidiEvent noteOnEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(4));
        assertEquals(true, noteOnEvent.isNoteOn());
        
        TimeStampedMidiEvent noteOnZeroVelocityEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(4));
        ShortMessage shortMessage = (ShortMessage) noteOnZeroVelocityEvent.rawMidiEvent.getMessage();
        //Set the velocity on this NOTE_ON to zero;
        shortMessage.setMessage(shortMessage.getCommand(), shortMessage.getChannel(), shortMessage.getData1(), 0x00);
        assertEquals(false, noteOnZeroVelocityEvent.isNoteOn());
        
        TimeStampedMidiEvent noteOffEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(7));
        assertEquals(false, noteOffEvent.isNoteOn());
        
        TimeStampedMidiEvent eventWithMetaMessage = new TimeStampedMidiEvent(0.0, tracks[1].get(0));
        assertEquals(false, eventWithMetaMessage.isNoteOn());     
    }
    
    /**
     * Test of isNoteOff method, of class TimeStampedMidiEvent.
     */
    @Test
    public void testIsNoteOff() throws InvalidMidiDataException, IOException {
        System.out.println("isNoteOff");
        Track[] tracks = MidiSystem.getSequence(new File("test\\samples\\test2.mid")).getTracks();
        TimeStampedMidiEvent noteOnEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(4));
        assertEquals(false, noteOnEvent.isNoteOff());
        
        TimeStampedMidiEvent noteOnZeroVelocityEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(4));
        ShortMessage shortMessage = (ShortMessage) noteOnZeroVelocityEvent.rawMidiEvent.getMessage();
        //Set the velocity on this NOTE_ON to zero;
        shortMessage.setMessage(shortMessage.getCommand(), shortMessage.getChannel(), shortMessage.getData1(), 0x00);
        assertEquals(true, noteOnZeroVelocityEvent.isNoteOff());
        
        TimeStampedMidiEvent noteOffEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(7));
        assertEquals(true, noteOffEvent.isNoteOff());    
        
        TimeStampedMidiEvent eventWithMetaMessage = new TimeStampedMidiEvent(0.0, tracks[1].get(0));
        assertEquals(false, eventWithMetaMessage.isNoteOff());
    }
    
    /**
     * Test of isEndOfTrack method, of class TimeStampedMidiEvent.
     */
    @Test
    public void testIsEndOfTrack() throws InvalidMidiDataException, IOException {
        System.out.println("isEndOfTrack");
        Track[] tracks = MidiSystem.getSequence(new File("test\\samples\\test2.mid")).getTracks();
        TimeStampedMidiEvent noteOnEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(4));
        assertEquals(false, noteOnEvent.isEndOfTrack());
        
        TimeStampedMidiEvent noteOffEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(7));
        assertEquals(false, noteOffEvent.isEndOfTrack());    
        
        TimeStampedMidiEvent setTempoEvent = new TimeStampedMidiEvent(0.0, tracks[0].get(3));
        assertEquals(false, setTempoEvent.isEndOfTrack());
        
        TimeStampedMidiEvent endOfTrackEvent = new TimeStampedMidiEvent(0.0, tracks[1].get(tracks[1].size()-1));
        assertEquals(true, endOfTrackEvent.isEndOfTrack());
    }
}
