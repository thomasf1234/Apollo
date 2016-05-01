/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ad
 */
public class Transcriber {
    public Transcription transcribe(String midiFilePath) throws InvalidMidiDataException, IOException {
        MidiReader midiReader = new MidiReader(midiFilePath);
        int[] types = new int[]{ShortMessage.NOTE_ON, ShortMessage.NOTE_OFF, ShortMessage.CONTROL_CHANGE, MidiReader.SET_TEMPO, MidiReader.END_OF_TRACK};
        TimeStampedMidiEvent[] chronologicalMidiEvents = midiReader.getChronologicalMidiEvents(types); //Add SET_TEMPO in MidiReader
         List<PianoEvent> pianoEvents = new ArrayList<PianoEvent>();

         for (int i = 0; i < chronologicalMidiEvents.length; i++) {
              if (chronologicalMidiEvents[i].isNoteOn()) {
                 ShortMessage ShortMessage = (ShortMessage) chronologicalMidiEvents[i].rawMidiEvent.getMessage();
                 int key = ShortMessage.getData1();
                 int velocity = ShortMessage.getData2();
                 Map data = new HashMap();
                 data.put("midiNoteNumber", key);
                 data.put("velocity", velocity);
                 pianoEvents.add(new PianoEvent(PianoEvent.NOTE_ON, chronologicalMidiEvents[i].time, data));
             } else if (chronologicalMidiEvents[i].isNoteOff()) {
               ShortMessage ShortMessage = (ShortMessage) chronologicalMidiEvents[i].rawMidiEvent.getMessage();
                 int key = ShortMessage.getData1();
                 int velocity = ShortMessage.getData2();
                 Map data = new HashMap();
                 data.put("midiNoteNumber", key);
                 data.put("velocity", velocity);
                 pianoEvents.add(new PianoEvent(PianoEvent.NOTE_OFF, chronologicalMidiEvents[i].time, data));   
             } 
             else if (chronologicalMidiEvents[i].isSustainPedal()) {
                 ShortMessage ShortMessage = (ShortMessage) chronologicalMidiEvents[i].rawMidiEvent.getMessage();
                 int value = ShortMessage.getData2();
                 Map data = new HashMap();
                 data.put("value", value);
                 pianoEvents.add(new PianoEvent(PianoEvent.SUSTAIN_PEDAL, chronologicalMidiEvents[i].time, data)); 
             }
              
        }

        return new Transcription(pianoEvents);
    }
}
