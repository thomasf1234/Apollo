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
    private final Sequence sequence;
    
    public MidiReader(String midi_path) throws InvalidMidiDataException, IOException {
      this.sequence = MidiSystem.getSequence(new File(midi_path));
    }
    //http://mido.readthedocs.org/en/latest/meta_message_types.html
    public void time() {
        
        //Verify the division type of the sequence (PPQ, SMPT)
        if(this.sequence.getDivisionType() == Sequence.PPQ)
        {
            int     ppq         = this.sequence.getResolution();
            //Do the math to get the time (in miliseconds) each tick takes 
            long    tickTime    = TicksToMiliseconds(BPM,ppq);
            //Returns the number of ticks from the longest track
            int     longestTrackTicks   = LongestTrackTicks(tracks);

            //Each iteration sends a new message to 'receiver'
            for(int tick = 0; tick < maiorTick ; tick++)
            {   
                //Iteration of each track
                for(int trackNumber = 0; trackNumber < tracks.length; trackNumber++)
                {
                    //If the number of ticks from a track isn't already finished
                    //continue
                    if(tick < tracks[trackNumber].size())
                    {
                        MidiEvent ev = tracks[trackNumber].get(tick);
                        rcv.send(ev.getMessage(),-1);
                    }
                }
                Thread.sleep(tickTime);
            }

        }
    }
    
    public MidiEvent[] getEvents(int[] types) {
        List<MidiEvent> midi_events = new ArrayList<MidiEvent>();
        
        for (Track track : this.sequence.getTracks()) {
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    if (Utilities.contains(types, sm.getCommand())) {
                        midi_events.add(event);
                    } 
                }
            }
        }
        
        sortMidiEventsChronologically(midi_events);
       
        return midi_events.toArray(new MidiEvent[midi_events.size()]);
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
}

