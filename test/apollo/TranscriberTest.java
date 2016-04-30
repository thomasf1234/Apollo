/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

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
        
        assertEquals(1445, transcription1.pianoEvents.size());
        
        PianoEvent pianoEvent0 = (PianoEvent) transcription1.pianoEvents.get(0);
        assertEquals("SUSTAIN_PEDAL", pianoEvent0.type);
        assertEquals(127, (int) pianoEvent0.data.get("value"));
        assertEquals(2.222460077569574, pianoEvent0.time, delta);
        
        PianoEvent pianoEvent4 = (PianoEvent) transcription1.pianoEvents.get(4);
        assertEquals("NOTE_ON", pianoEvent4.type);
        assertEquals(64, (int) pianoEvent4.data.get("key"));
        assertEquals(13, (int) pianoEvent4.data.get("velocity"));
        assertEquals(2.6576918427602827, pianoEvent4.time, delta);

        
        PianoEvent pianoEventLast = (PianoEvent) transcription1.pianoEvents.get(transcription1.pianoEvents.size() - 1);
        assertEquals("SUSTAIN_PEDAL", pianoEventLast.type);
        assertEquals(0, (int) pianoEventLast.data.get("value"));
        assertEquals(94.2554579147577, pianoEventLast.time, delta);
        
        
        Transcription transcription2 = instance.transcribe("test\\samples\\test2.mid");
        
        assertEquals(3583, transcription2.pianoEvents.size());
        
        PianoEvent pianoEvent2 = (PianoEvent) transcription2.pianoEvents.get(2);
        assertEquals("NOTE_ON", pianoEvent2.type);
        assertEquals(46, (int) pianoEvent2.data.get("key"));
        assertEquals(30, (int) pianoEvent2.data.get("velocity"));
        assertEquals(0.8998046875, pianoEvent2.time, delta);
        
        pianoEventLast = (PianoEvent) transcription2.pianoEvents.get(transcription2.pianoEvents.size() - 1);
        assertEquals("SUSTAIN_PEDAL", pianoEventLast.type);
        assertEquals(0, (int) pianoEventLast.data.get("value"));
        assertEquals(217.88710522849024, pianoEventLast.time, delta);
    }
    
}
