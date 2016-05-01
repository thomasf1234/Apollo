/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author ad
 */
public class RunnablePianist implements Runnable {

    private Thread thread;
    private final String threadName;
    public Pianist pianist;

    RunnablePianist(String name) throws MidiUnavailableException {
        this.threadName = name;
        this.pianist = new Pianist();
    }

    @Override
    public void run() {
        try {
            this.pianist.play();
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(RunnablePianist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this, this.threadName);
            this.thread.start();
        }
    }

    public Thread getThread() {
        return this.thread;
    }

}
