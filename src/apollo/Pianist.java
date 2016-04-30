/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.util.Date;
import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author ad
 */
public class Pianist {
    private final Piano piano;

    public Pianist() throws MidiUnavailableException {
        this.piano = new Piano();
    }

    public void play(Transcription transcription) {
        double t0 = System.nanoTime() * Math.pow(10, -9);
        double elapsedTime = 0;
        int index = 0;
        boolean finished = false;

        PianoEvent pianoEvent = transcription.pianoEvents.get(index);
        //timing appears right, but lose a second over 3minutes most likely because if the miliseconds lost when we go to check 
        //need to optimize, and install pedalling etc.
        //Tet code, and clean up the Transcription
        //count lost seconds to make up
        
        while (index < transcription.pianoEvents.size() && finished == false) {
            elapsedTime = (System.nanoTime() * Math.pow(10, -9)) - t0;
            
            while (elapsedTime >= pianoEvent.time) {
                System.out.println(pianoEvent.type + " @" + pianoEvent.time + " data: " + pianoEvent.data);

                switch (pianoEvent.type) {
                    case (PianoEvent.NOTE_ON):
                        this.piano.noteOn((int) pianoEvent.data.get("key"), (int) pianoEvent.data.get("velocity"));
                        break;
                    case (PianoEvent.NOTE_OFF): 
                        this.piano.noteOff((int) pianoEvent.data.get("key"));
                        break;
                    case (PianoEvent.SUSTAIN_PEDAL):
                        this.piano.sustainPedalUpdate((int) pianoEvent.data.get("value"));
                        break;
                    default: 
                        break;
                }

                
                if (index == transcription.pianoEvents.size() - 1) {
                    finished = true;
                    break;
                } else {
                    index++;
                    pianoEvent = transcription.pianoEvents.get(index);
                }
            }
        }
        
        System.out.println("FINISHED");
    }
}
