/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.util.Map;

/**
 *
 * @author ad
 */
public class PianoEvent {

    public static final String NOTE_ON = "NOTE_ON";
    public static final String NOTE_OFF = "NOTE_OFF";
    public static final String SUSTAIN_PEDAL = "SUSTAIN_PEDAL";
    public double time;
    public String type;
    public Map data;

    public PianoEvent(String type, double time, Map data) {
        this.type = type;
        this.time = time;
        this.data = data;
    }
}
