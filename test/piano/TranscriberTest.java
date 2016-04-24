/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
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
public class TranscriberTest {
    
    public TranscriberTest() {
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
     * Test of transcribe method, of class Transcriber.
     */
    @Test
    public void testTranscribe() throws InvalidMidiDataException, IOException {
        System.out.println("transcribe");
        double delta = 0.000001;
        Transcriber instance = new Transcriber();
        
        Transcription transcription1 = instance.transcribe("test\\samples\\test.mid");
        
        assertEquals(394, transcription1.actions.size());
        
        KeyPress keyPress1 = (KeyPress) transcription1.actions.get(0);
        assertEquals(61, keyPress1.key);
        assertEquals(13, keyPress1.velocity);
        assertEquals(2.222460077569574, keyPress1.interval.getStartTime(), delta);
        assertEquals(2.416925334356912, keyPress1.interval.getEndTime(), delta);
        assertEquals(0.19446525678733773, keyPress1.interval.getDuration(), delta);
        
        KeyPress keyPressLast = (KeyPress) transcription1.actions.get(393);
        assertEquals(64, keyPressLast.key);
        assertEquals(6, keyPressLast.velocity);
        assertEquals(87.29174967170661, keyPressLast.interval.getStartTime(), delta);
        assertEquals(88.14369270144161, keyPressLast.interval.getEndTime(), delta);
        assertEquals(0.851943029734997, keyPressLast.interval.getDuration(), delta);
        
        
        Transcription transcription2 = instance.transcribe("test\\samples\\test2.mid");
        
        for (int i = 0; i < transcription1.actions.size(); i++) {
            KeyPress keyPress = (KeyPress) transcription1.actions.get(i);
            System.out.println("NoteName: " + keyPress.getNoteName() + " time: " + keyPress.interval.getStartTime() + " end: " + keyPress.interval.getEndTime() + " duration: " + keyPress.interval.getDuration());
        }
        
        assertEquals(1380, transcription2.actions.size());
        
        keyPress1 = (KeyPress) transcription2.actions.get(0);
        assertEquals(46, keyPress1.key);
        assertEquals(30, keyPress1.velocity);
        assertEquals(0.8998046875, keyPress1.interval.getStartTime(), delta);
        assertEquals(3.3068796703086782, keyPress1.interval.getEndTime(), delta);
        assertEquals(2.407074982808678, keyPress1.interval.getDuration(), delta);
        
        keyPressLast = (KeyPress) transcription2.actions.get(1379);
        assertEquals(41, keyPressLast.key);
        assertEquals(3, keyPressLast.velocity);
        assertEquals(210.42292789162155, keyPressLast.interval.getStartTime(), delta);
        assertEquals(211.92292789162155, keyPressLast.interval.getEndTime(), delta);
        assertEquals(1.5, keyPressLast.interval.getDuration(), delta);
    }
    
}
