/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
public class Transcriber {
    public Transcription transcribe(String midiFilePath) throws InvalidMidiDataException, IOException {
        MidiReader midiReader = new MidiReader(midiFilePath);
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF, MidiReader.SET_TEMPO, MidiReader.END_OF_TRACK};
        TimeStampedMidiEvent[] chronologicalMidiEvents = midiReader.getChronologicalMidiEvents(types); //Add SET_TEMPO in MidiReader
        List<KeyPress> keyPresses = new ArrayList<>();

         for (int i = 0; i < chronologicalMidiEvents.length; i++) {
//TODO Start of remove
//might need to cater for when there is no NOTE_OFF for note (check test2.mid)
/*
            TimeStampedMidiEvent e = chronologicalMidiEvents[i];
            if (e.isNoteOn() || e.isNoteOff()) {
                ShortMessage sm = (ShortMessage) e.rawMidiEvent.getMessage();
                System.out.println("time: " + e.time + " key: " + sm.getData1() + " velocity: " + sm.getData2());
            }
*/            
//End of remove
            if (chronologicalMidiEvents[i].isNoteOn()) {
                for (int j = i + 1; j < chronologicalMidiEvents.length; j++) {
                    ShortMessage iShortMessage = (ShortMessage) chronologicalMidiEvents[i].rawMidiEvent.getMessage();
                    int iKey = iShortMessage.getData1();
                    int iVelocity = iShortMessage.getData2();

                    if (chronologicalMidiEvents[j].isNoteOff()) {
                        ShortMessage jShortMessage = (ShortMessage) chronologicalMidiEvents[j].rawMidiEvent.getMessage();
                        int jKey = jShortMessage.getData1();

                        if (jKey == iKey) {
                            Interval interval = new Interval(chronologicalMidiEvents[i].time, chronologicalMidiEvents[j].time);
                            KeyPress keyPress = new KeyPress(iKey, iVelocity, interval);
                            keyPresses.add(keyPress);
                            break;
                        }
                    }
                }
            }
        }

        return new Transcription(keyPresses);
    }
}
