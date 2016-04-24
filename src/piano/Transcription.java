/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piano;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ad
 */
public class Transcription {
    public List actions;
    
    public Transcription(List<KeyPress> keyPresses) {
        this.actions = new ArrayList();
        this.actions.addAll(keyPresses);
    }
}
