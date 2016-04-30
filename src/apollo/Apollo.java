/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
public class Apollo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        new Pianist().play(new Transcriber().transcribe("C:\\Users\\ad\\Documents\\my_songs\\tom_odell_-_.mid"));
    }
}
