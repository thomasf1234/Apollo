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
public class Interval {
    private final int start_time;
    private final int end_time;
    
    public Interval(int start_time, int end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
    
    public int getStartTime() {
        return this.start_time;
    }
    
    public int getEndTime() {
        return this.end_time;
    }
    
    public int getDuration() {
        return (this.end_time - this.start_time);
    }
}
