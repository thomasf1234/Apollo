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
public class Note {
    public int key;
    public Interval interval;
    public int velocity;
    
    public Note(int key, Interval interval, int velocity) {
        this.key = key;
        this.interval = interval;
        this.velocity = velocity;
    }
}
