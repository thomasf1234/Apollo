/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 *
 * @author ad
 */
public class MidiReader {
    public static final int SET_TEMPO = 0x51;
    public static final int DEFAULT_TEMPO = 500000;
    private final Sequence sequence;
    
    public MidiReader(String midi_path) throws InvalidMidiDataException, IOException {
      this.sequence = MidiSystem.getSequence(new File(midi_path));
    }
    
    public TimeStampedMidiEvent[] getTimeStampedMidiEvents(int[] types) throws InvalidMidiDataException {       
        List<MidiEvent> midiEvents = getMidiEvents(types);
        sortMidiEventsChronologically(midiEvents);
        
        TimeStampedMidiEvent[] timeStampedMidiEvents = new TimeStampedMidiEvent[midiEvents.size()];
        
        int currentTempo = DEFAULT_TEMPO;
        double currentTime = 0;
        long currentTick = 0;
        for (int i = 0; i < midiEvents.size(); i++) {
            MidiEvent midiEvent = midiEvents.get(i);
            MidiMessage midiMessage = midiEvent.getMessage();
            if (midiMessage instanceof MetaMessage) {
                MetaMessage metaMessage = (MetaMessage) midiMessage;
                if (metaMessage.getType() == SET_TEMPO) {
                    byte[] data = metaMessage.getData();
                    currentTempo = (data[0] & 0xFF) << 16 | (data[1] & 0xFF) << 8 | (data[2] & 0xFF);
                    //System.out.println("SET_TEMPO: " + currentTempo);
                }
            }
            //System.out.println("tick diff: " + (midiEvent.getTick() - currentTick) + " tempo: " + currentTempo);
            currentTime += ((midiEvent.getTick() - currentTick) / ticksPerSecond(currentTempo));
            currentTick = midiEvent.getTick();
            timeStampedMidiEvents[i] = new TimeStampedMidiEvent(currentTime, midiEvent);
        }

        return timeStampedMidiEvents;
    }
    
    public List<MidiEvent> getMidiEvents(int[] types) {
        List<MidiEvent> midiEvents = new ArrayList<MidiEvent>();
        
        for (Track track : this.sequence.getTracks()) {
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    if (Utilities.contains(types, sm.getCommand())) {
                        midiEvents.add(event);
                    } 
                } else if (message instanceof MetaMessage) {
                    MetaMessage mm = (MetaMessage) message;
                    if (Utilities.contains(types, mm.getType())) {
                        midiEvents.add(event);
                    } 
                }
            }
        }
        
        sortMidiEventsChronologically(midiEvents);
        return midiEvents;
    }
    
    private void sortMidiEventsChronologically(List<MidiEvent> midi_events){
      Collections.sort(midi_events, new Comparator<MidiEvent>() {
            public int compare(MidiEvent one, MidiEvent other) {
                long difference = one.getTick() - other.getTick();
                
                if (difference == 0) {
                    return 0;
                }
                else {
                  return (int) (difference/Math.abs(difference));    
                }           
            }
        });     
    }
    
    private double ticksPerSecond(int tempo) throws InvalidMidiDataException {
        float fDivisionType = sequence.getDivisionType();
        double beatsPerMinute = 60000000.0f / tempo;
        
        if (fDivisionType == Sequence.PPQ) {
            return sequence.getResolution() * (beatsPerMinute / 60.0);
        } else if (fDivisionType == Sequence.SMPTE_24) {
            return sequence.getResolution() * 24.0;
        } else if (fDivisionType == Sequence.SMPTE_25) {
            return sequence.getResolution() * 25.0;
        } else if (fDivisionType == Sequence.SMPTE_30DROP) {
            return sequence.getResolution() * 29.97;
        } else if (fDivisionType == Sequence.SMPTE_30) {
            return sequence.getResolution() * 30;
        } else {
            throw new InvalidMidiDataException("Unknown division type: " + fDivisionType);
        }
    }
}

