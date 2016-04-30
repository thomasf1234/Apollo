/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author ad
 */
public class Piano {
    private static final int PIANO = 0;
    private static final int SUSTAIN_PEDAL_STATE_INDEX = 126;
    //0-126 is C0-G10; 127 is SustainPedal; state is velocity/value 
    private ThreadSafeArray states;
    
    private final MidiChannel channel;
    
    public Piano() throws MidiUnavailableException {
      this.channel = initMidiChannel(PIANO);  
      this.states = new ThreadSafeArray(127);
    }
    
    public void noteOn(int midiNoteNumber, int velocity) {
        this.channel.noteOn(midiNoteNumber, velocity); 
        this.states.set(midiNoteNumber - 1, velocity);
    }
    
    public void noteOff(int midiNoteNumber) {
        this.channel.noteOff(midiNoteNumber); 
        this.states.set(midiNoteNumber - 1, 0);
    }
    
    public void sustainPedalUpdate(int value) {
        this.channel.controlChange(MidiReader.SUSTAIN_PEDAL, value); 
        this.states.set(SUSTAIN_PEDAL_STATE_INDEX, value);
    }
    
    public int getNoteState(int midiNoteNumber) {
        return this.states.get(midiNoteNumber - 1);
    }
    
    public int getSustainPedalState() {
        return this.states.get(SUSTAIN_PEDAL_STATE_INDEX);
    }
    
    public void setMute(boolean mute) {
       this.channel.setMute(mute);
    }

//    public void silence() {
//        this.channel.allNotesOff();
//    }
    
    private MidiChannel initMidiChannel(int instrument) throws MidiUnavailableException  {
        Synthesizer synth = MidiSystem.getSynthesizer();
        
        synth.open();
        MidiChannel midiChannel = synth.getChannels()[0];
        midiChannel.programChange(instrument - 1);
        midiChannel.setChannelPressure(5);  // optional vibrato
        return midiChannel;
    }
}
