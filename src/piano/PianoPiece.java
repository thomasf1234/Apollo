/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
//http://sites.uci.edu/camp2014/2014/05/19/timing-in-midi-files/
//https://www.youtube.com/watch?v=3f1nMgbthiE
//http://stackoverflow.com/questions/25859950/midi-java-generates-messed-sound
public class PianoPiece {

    public static PianoPiece newFrom(MidiReader midi_reader) {
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF};
        MidiEvent[] midi_events = midi_reader.getEvents(types);
        //get the length of the whole piece in microseconds, then divide by number of ticks
        //http://stackoverflow.com/questions/2038313/midi-ticks-to-actual-playback-seconds-midi-music
        Note[] notes = new 
        return null;
    }
}
