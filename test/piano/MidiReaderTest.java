/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.IOException;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
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
public class MidiReaderTest {
    
    public MidiReaderTest() {
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
     * Test of getEvents method, of class MidiReader.
     */
    @Test
    public void testGetMidiEvents() throws InvalidMidiDataException, IOException {
        System.out.println("getEvents");
        int expected_event_type;
        int expected_key;
        int expected_velocity;
        int expected_tick;
        ShortMessage sm;
        
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF};
        MidiReader instance1 = new MidiReader("test\\samples\\test.mid");
        List<MidiEvent> result1 = instance1.getMidiEvents(types);

        //should be 788 note on/off events in test.mid
        assertEquals(788, result1.size());
        
        expected_event_type = ShortMessage.NOTE_ON;
        expected_key = 61;
        expected_velocity = 13;
        expected_tick = 480;
        
        sm = (ShortMessage) result1.get(0).getMessage();
        assertEquals(expected_event_type, sm.getCommand());
        assertEquals(expected_key, sm.getData1());
        assertEquals(expected_velocity, sm.getData2());
        assertEquals(expected_tick, result1.get(0).getTick());
        
        //2nd test midi file, multiple tracks
        MidiReader instance2 = new MidiReader("test\\samples\\test2.mid");
        List<MidiEvent> result2 = instance2.getMidiEvents(types);

        //should be 2760 note on/off events in test2.mid
        assertEquals(2760, result2.size());
        
        expected_event_type = ShortMessage.NOTE_OFF;
        expected_key = 70;
        expected_velocity = 0;
        expected_tick = 1536;
        
        sm = (ShortMessage) result2.get(7).getMessage();
        assertEquals(expected_event_type, sm.getCommand());
        assertEquals(expected_key, sm.getData1());
        assertEquals(expected_velocity, sm.getData2());
        assertEquals(expected_tick, result2.get(7).getTick());
        
    }

    /**
     * Test of getTimeStampedMidiEvents method, of class MidiReader.
     */
    @Test
    public void testGetChronologicalMidiEvents() throws Exception {
        System.out.println("getTimeStampedMidiEvents");
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF, MidiReader.SET_TEMPO};
        MidiReader instance = new MidiReader("test\\samples\\test2.mid");
        TimeStampedMidiEvent[] timeStampedMidiEvents = instance.getChronologicalMidiEvents(types);
        assertEquals(3124, timeStampedMidiEvents.length);
        
        assertEquals(1024, timeStampedMidiEvents[6].rawMidiEvent.getTick());
        double delta = 0.001;
        assertEquals(1.275, timeStampedMidiEvents[6].time, delta);
       
        ShortMessage sm = (ShortMessage) timeStampedMidiEvents[6].rawMidiEvent.getMessage();
        assertEquals(ShortMessage.NOTE_ON, sm.getCommand());
        assertEquals(73, sm.getData1());
        assertEquals(54, sm.getData2());
    }
}
