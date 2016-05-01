/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ad
 */
public class Transcription {
    public List<PianoEvent> pianoEvents;
    
    public Transcription(List<PianoEvent> pianoEvents) {
        this.pianoEvents = sortPianoEventsChronologically(pianoEvents);
    }
  
    public List<Note> toNotes() {
        List<Note> notes = new ArrayList<>();

        for (int i = 0; i < this.pianoEvents.size(); i++) {
            if (this.pianoEvents.get(i).type == PianoEvent.NOTE_ON) {
                for (int j = i + 1; j < this.pianoEvents.size(); j++) {
                    int iKey = (int) this.pianoEvents.get(i).data.get("key");
                    int iVelocity = (int) this.pianoEvents.get(i).data.get("velocity");

                    if (this.pianoEvents.get(j).type == PianoEvent.NOTE_OFF) {
                        int jKey = (int) this.pianoEvents.get(j).data.get("key");

                        if (jKey == iKey) {
                            Interval interval = new Interval(this.pianoEvents.get(i).time, this.pianoEvents.get(j).time);
                            Note note = new Note(iKey, iVelocity, interval);
                            notes.add(note);
                            break;
                        }
                    }
                }
            }
        }

        return notes;
    }
    
    private List<PianoEvent> sortPianoEventsChronologically(List<PianoEvent> pianoEvents){
      Collections.sort(pianoEvents, (PianoEvent one, PianoEvent other) -> {
          double difference = one.time - other.time;
          
          if (difference == 0) {
              return 0;
          }
          else {
              return (int) (difference/Math.abs(difference));
          }
      });   
      //return a copy of arrya potenitally
      return pianoEvents;
    }
}
