/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
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

public class PianistTest {
    
    public PianistTest() {
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
     * Test of play method, of class Pianist.
     * @throws javax.sound.midi.MidiUnavailableException
     * @throws javax.sound.midi.InvalidMidiDataException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * START_____________________________________________________
     * SUSTAIN_PEDAL @0.0 data: {value=0}
     * SUSTAIN_PEDAL @0.0 data: {value=0} NOTE_ON @0.8998046875 data:
     * {velocity=30, key=46}
     *
     * @1.0s ON = 46
     *
     * SUSTAIN_PEDAL @1.2748046875 data: {value=0} 
     * NOTE_ON @1.2748046875 data: {velocity=15, key=61} 
     * NOTE_ON @1.2748046875 data: {velocity=24, key=70}
     * NOTE_ON @1.2748046875 data: {velocity=54, key=73} 
     * SUSTAIN_PEDAL @1.2748046875 data: {value=0} 
     * NOTE_ON @1.2748046875 data: {velocity=9, key=53} 
     * NOTE_ON @1.2748046875 data: {velocity=18, key=65}
     * 
     * @1.5s ON = SUSTAIN(128), 46, 61, 70, 73, 53, 65
     * 
     * SUSTAIN_PEDAL @1.52529296875 data: {value=127}
     *
     * @1.6s ON = SUSTAIN(128), 46, 61, 70, 73, 53, 65
     *
     * NOTE_OFF @1.9249023437500001 data: {velocity=0, key=61}
     * NOTE_OFF @1.9249023437500001 data: {velocity=0, key=70}
     * NOTE_ON @1.9249023437500001 data: {velocity=15, key=61}
     * NOTE_ON @1.9249023437500001 data: {velocity=24, key=70}
     * NOTE_OFF @1.9249023437500001 data: {velocity=0, key=53}
     * NOTE_OFF @1.9249023437500001 data: {velocity=0, key=65}
     * NOTE_ON @1.9249023437500001 data: {velocity=9, key=53}
     * NOTE_ON @1.9249023437500001 data: {velocity=18, key=65}
     * 
     * @2.0s ON = SUSTAIN(128), 46, 61, 70, 73, 53, 65
     */
    @Test
    public void testPlay_test2mid() throws MidiUnavailableException, InvalidMidiDataException, IOException, InterruptedException {
        System.out.println("play");
        RunnablePianist runnablePianist = new RunnablePianist("testPianist", new Transcriber().transcribe("test\\samples\\test2.mid"));
        runnablePianist.pianist.piano.setMute(true);
        assertEquals(Pianist.State.NOT_STARTED, runnablePianist.pianist.state);
        runnablePianist.start();
        
        Thread.sleep(1000);
        //just note 46
        System.out.println("@1.0s states on: " + runnablePianist.pianist.piano.statesOn());
        assertEquals("[46]", runnablePianist.pianist.piano.statesOn().toString());
        assertEquals(30, runnablePianist.pianist.piano.getNoteState(46));
              
        Thread.sleep(500);
        System.out.println("@1.0s states on: " + runnablePianist.pianist.piano.statesOn());
        assertEquals("[46, 53, 61, 65, 70, 73]", runnablePianist.pianist.piano.statesOn().toString());
        
        Thread.sleep(100);
        //sustain pedal now down
        System.out.println("@1.0s states on: " + runnablePianist.pianist.piano.statesOn());
        assertEquals("[46, 53, 61, 65, 70, 73, 128]", runnablePianist.pianist.piano.statesOn().toString());
        
        Thread.sleep(400);
        System.out.println("@1.0s states on: " + runnablePianist.pianist.piano.statesOn());
        assertEquals("[46, 53, 61, 65, 70, 73, 128]", runnablePianist.pianist.piano.statesOn().toString());
        
        //check pianist is still playing
        assertEquals(true, runnablePianist.getThread().isAlive());
        
        //set pianist flag to FINISHED so it ends
        runnablePianist.pianist.finish();
        System.out.println("UPDATED STATE TO FINISHED!!!!!!!!!");
 
        //allow some time for pianist to finish
        Thread.sleep(1000);
        
        //pianist thread should have ended
        assertEquals(true, runnablePianist.pianist.isFinished());
        assertEquals(false, runnablePianist.getThread().isAlive());
    }
}
