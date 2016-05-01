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
    public Piano piano;
    public State state;
    public Transcription transcription;
    public double elapsedTime;

    public Pianist() throws MidiUnavailableException {
        this.piano = new Piano();
        this.state = State.NOT_STARTED;
    }

    //plays transcription
    public void play() throws NoSuchFieldException {
        if (this.transcription == null)
            throw new NoSuchFieldException("no transcription to play");
        
        double t0 = System.nanoTime() * Math.pow(10, -9);
        this.elapsedTime = 0;        
        int index = 0;
        this.state = State.PLAYING;
        PianoEvent pianoEvent = this.transcription.pianoEvents.get(index);
        
        while (isPlaying() && index < this.transcription.pianoEvents.size()) {
            this.elapsedTime = (System.nanoTime() * Math.pow(10, -9)) - t0;
            
            while (isPlaying() && this.elapsedTime >= pianoEvent.time) {
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

                if (index == this.transcription.pianoEvents.size() - 1) {
                    finish();
                    break;
                } else {
                    index++;
                    pianoEvent = this.transcription.pianoEvents.get(index);
                }
            }
        }
    }
    
    public void setTranscription(Transcription transcription) {
        this.transcription = transcription;
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
