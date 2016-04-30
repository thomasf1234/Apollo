/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author ad
 */
public class RunnablePianist implements Runnable {

    private Thread thread;
    private final String threadName;
    private final Transcription transcription;
    public Pianist pianist;

    RunnablePianist(String name, Transcription transcription) throws MidiUnavailableException {
        this.threadName = name;
        this.transcription = transcription;
        this.pianist = new Pianist();
    }

    @Override
    public void run() {
        this.pianist.play(transcription);
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
