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
    private final double start_time;
    private final double end_time;
    
    public Interval(double start_time, double end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
    
    public double getStartTime() {
        return this.start_time;
    }
    
    public double getEndTime() {
        return this.end_time;
    }
    
    public double getDuration() {
        return (this.end_time - this.start_time);
    }
}
