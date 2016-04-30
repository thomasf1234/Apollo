/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
public class TimeStampedMidiEvent {
    public double time;
    public MidiEvent rawMidiEvent;
    
    public TimeStampedMidiEvent(double time, MidiEvent rawMidiEvent) {
        this.time = time;
        this.rawMidiEvent = rawMidiEvent;
    }
    
    public boolean isNoteOn() {
        MidiMessage midiMessage = this.rawMidiEvent.getMessage();
        if (midiMessage instanceof ShortMessage) {
            ShortMessage shortMessage = (ShortMessage) midiMessage;
            int command = shortMessage.getCommand();
            int velocity = shortMessage.getData2();
            
            if (command == ShortMessage.NOTE_ON && velocity > 0) {
                return true;
            } 
        }
        
        return false;
    }
    
    public boolean isNoteOff() {
        MidiMessage midiMessage = this.rawMidiEvent.getMessage();
        if (midiMessage instanceof ShortMessage) {
            ShortMessage shortMessage = (ShortMessage) midiMessage;
            int command = shortMessage.getCommand();
            int velocity = shortMessage.getData2();
            
            if (command == ShortMessage.NOTE_OFF || (command == ShortMessage.NOTE_ON && velocity == 0)) {
                return true;
            } 
        }
        
        return false;
    }
    
    public boolean isSustainPedal() {
        MidiMessage midiMessage = this.rawMidiEvent.getMessage();
        if (midiMessage instanceof ShortMessage) {
            ShortMessage shortMessage = (ShortMessage) midiMessage;
            int command = shortMessage.getCommand();
            int type = shortMessage.getData1();
            
            if (command == ShortMessage.CONTROL_CHANGE && type == MidiReader.SUSTAIN_PEDAL) {
                return true;
            } 
        }
        
        return false;
    }
    
    public boolean isEndOfTrack() {
        MidiMessage midiMessage = this.rawMidiEvent.getMessage();
        if (midiMessage instanceof MetaMessage) {
            MetaMessage metaMessage = (MetaMessage) midiMessage;
            if (metaMessage.getType() == MidiReader.END_OF_TRACK) {
                return true;
            }
        }

        return false;
    }
}
