/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.IOException;
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
    public void testGetEvents() throws InvalidMidiDataException, IOException {
        System.out.println("getEvents");
        int expected_event_type;
        int expected_key;
        int expected_velocity;
        int expected_tick;
        ShortMessage sm;
        
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF};
        MidiReader instance1 = new MidiReader("test\\samples\\test.mid");
        MidiEvent[] result1 = instance1.getEvents(types);

        //should be 788 note on/off events in test.mid
        assertEquals(788, result1.length);
        
        expected_event_type = ShortMessage.NOTE_ON;
        expected_key = 61;
        expected_velocity = 13;
        expected_tick = 480;
        
        sm = (ShortMessage) result1[0].getMessage();
        assertEquals(expected_event_type, sm.getCommand());
        assertEquals(expected_key, sm.getData1());
        assertEquals(expected_velocity, sm.getData2());
        assertEquals(expected_tick, result1[0].getTick());
        
        //2nd test midi file, multiple tracks
        MidiReader instance2 = new MidiReader("test\\samples\\test2.mid");
        MidiEvent[] result2 = instance2.getEvents(types);

        //should be 2760 note on/off events in test2.mid
        assertEquals(2760, result2.length);
        
        expected_event_type = ShortMessage.NOTE_OFF;
        expected_key = 70;
        expected_velocity = 0;
        expected_tick = 1536;
        
        
        // @1024 Channel: 0 Note on, C#4 key=61 velocity: 15
      
        //for (int i = 0; i < 16; i++) {
        //    sm = (ShortMessage) result2[i].getMessage();
        //    System.out.println("@" + result2[i].getTick() + " " + sm.getCommand() + ", key=" + sm.getData1() + " velocity: " + sm.getData2());
        //}
        sm = (ShortMessage) result2[7].getMessage();
        assertEquals(expected_event_type, sm.getCommand());
        assertEquals(expected_key, sm.getData1());
        assertEquals(expected_velocity, sm.getData2());
        assertEquals(expected_tick, result2[7].getTick());
        
    }
    
}
