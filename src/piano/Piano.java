/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
public class Piano {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            MidiReader midiReader = new MidiReader("C:\\Users\\ad\\Documents\\NetBeansProjects\\Piano\\test\\samples\\test2.mid");
            int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF, MidiReader.SET_TEMPO};
            TimeStampedMidiEvent[] timeStampedMidiEvents = midiReader.getTimeStampedMidiEvents(types);
            for(TimeStampedMidiEvent event : timeStampedMidiEvents) {
                System.out.println("tick: " + event.rawMidiEvent.getTick() + " time: " + event.time + "MessageClass: " + event.rawMidiEvent.getMessage().getClass());
            }
            
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(Piano.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Piano.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
