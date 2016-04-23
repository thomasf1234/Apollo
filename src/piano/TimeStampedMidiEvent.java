/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import javax.sound.midi.MidiEvent;

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
}
