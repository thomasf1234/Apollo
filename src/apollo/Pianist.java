/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author ad
 */
public class Pianist {
    public enum State { NOT_STARTED, PLAYING, FINISHED }  
    public final Piano piano;
    public State state;
    public Transcription currentTranscription;

    public Pianist() throws MidiUnavailableException {
        this.piano = new Piano();
        this.state = State.NOT_STARTED;
    }

    public void play(Transcription transcription) {
        double t0 = System.nanoTime() * Math.pow(10, -9);
        double elapsedTime = 0;
        int index = 0;
        this.currentTranscription = transcription;
        this.state = State.PLAYING;

        PianoEvent pianoEvent = transcription.pianoEvents.get(index);
        //timing appears right, but lose a second over 3minutes most likely because if the miliseconds lost when we go to check 
        //need to optimize, and install pedalling etc.
        //Tet code, and clean up the Transcription
        //count lost seconds to make up
        
        while (isPlaying() && index < transcription.pianoEvents.size()) {
            elapsedTime = (System.nanoTime() * Math.pow(10, -9)) - t0;
            
            while (isPlaying() && elapsedTime >= pianoEvent.time) {
                //System.out.println(pianoEvent.type + " @" + pianoEvent.time + " data: " + pianoEvent.data);

                switch (pianoEvent.type) {
                    case (PianoEvent.NOTE_ON):
                        this.piano.noteOn((int) pianoEvent.data.get("midiNoteNumber"), (int) pianoEvent.data.get("velocity"));
                        break;
                    case (PianoEvent.NOTE_OFF): 
                        this.piano.noteOff((int) pianoEvent.data.get("midiNoteNumber"));
                        break;
                    case (PianoEvent.SUSTAIN_PEDAL):
                        this.piano.sustainPedalUpdate((int) pianoEvent.data.get("value"));
                        break;
                    default: 
                        break;
                }

                if (index == transcription.pianoEvents.size() - 1) {
                    finish();
                    break;
                } else {
                    index++;
                    pianoEvent = transcription.pianoEvents.get(index);
                }
            }
        }
        
        System.out.println("@" + elapsedTime + " NOW FINISHED");
    }
    
    public boolean isPlaying() {
        return this.state == State.PLAYING;
    }
    
    public boolean isFinished() {
        return this.state == State.FINISHED;
    }
    
    public void finish() {
        this.state = State.FINISHED;
    }
}
