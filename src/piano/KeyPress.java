/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

/**
 *
 * @author ad
 */
public class KeyPress {
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    public int key;
    public Interval interval;
    public int velocity;
       
    public KeyPress(int key, int velocity, Interval interval) {
        this.key = key;
        this.velocity = velocity;
        this.interval = interval;
    }
    
    public String getNoteName() {
        int octave = (this.key / 12) - 1;
        return NOTE_NAMES[key % 12] + octave;
    }
}
