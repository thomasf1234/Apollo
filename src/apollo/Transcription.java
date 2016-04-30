/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ad
 */
public class Transcription {
    public List<PianoEvent> pianoEvents;
    
    public Transcription(List<PianoEvent> pianoEvents) {
        this.pianoEvents = sortPianoEventsChronologically(pianoEvents);
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
