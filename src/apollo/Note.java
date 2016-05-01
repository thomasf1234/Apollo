/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

/**
 *
 * @author ad
 */
public class Note {
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    public int midiNoteNumber;
    public Interval interval;
    public int velocity;
       
    public Note(int midiNoteNumber, int velocity, Interval interval) {
        this.midiNoteNumber = midiNoteNumber;
        this.velocity = velocity;
        this.interval = interval;
    }
    
    public String getNoteName() {
        return Note.noteNameFor(this.midiNoteNumber);
    }
    
    public int octave() { 
        return Note.octaveFor(this.midiNoteNumber);
    }
    
    public boolean isBlackKey() {
      return NOTE_NAMES[this.midiNoteNumber % 12].length() == 2;
    }
    
    public static String noteNameFor(int midiNoteNumber) {
        return NOTE_NAMES[midiNoteNumber % 12] + octaveFor(midiNoteNumber);  
    }
    
    public static int octaveFor(int midiNoteNumber) {
        return (midiNoteNumber / 12) - 1;   
    }
}